package fiap.sd.udp.simplechatudp.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import javax.xml.crypto.Data;

import sun.awt.windows.ThemeReader;
import fiap.sd.udp.simplechatudp.beans.Servidor;
import fiap.sd.udp.simplechatudp.beans.Usuario;
import fiap.sd.udp.simplechatudp.bo.ServerInstance;
import fiap.sd.udp.simplechatudp.sender.Sender;

/**
 * Implementa o lado "Ouvidor" de nosso chat UDP simples
 * @author fm
 *
 */
public class Receiver {
	
	// PARA PESQUISAR: Qual o tamanho maximo do buffer?
	private static int BUFSIZE = 4096;
	private DatagramSocket listenSocket;
	
	public DatagramSocket getListenSocket() {
		return listenSocket;
	}

	public void setListenSocket(DatagramSocket listenSocket) {
		this.listenSocket = listenSocket;
	}
	
	public Receiver(int port) {
		try {
			listenSocket = new DatagramSocket(port);
			System.out.println("** Ouvindo mensagens em "+listenSocket.getLocalSocketAddress());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public DatagramPacket run(Servidor sInstance) {
		byte[] buffer = new byte[BUFSIZE];
		DatagramPacket msg = null;
		try {
			Arrays.fill(buffer, (byte) ' ');
			DatagramPacket packet = new DatagramPacket(buffer,BUFSIZE);
			listenSocket.receive(packet);
			msg = packet;
		} catch (IOException e) {
			e.printStackTrace();
		} /*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return msg;
	}
}
