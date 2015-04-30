package fiap.sd.udp.simplechatudp.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

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
	
	public Receiver(int port) {
		try {
			listenSocket = new DatagramSocket(port);
			System.out.println("** Ouvindo mensagens em "+listenSocket.getLocalSocketAddress());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void run() {
		byte[] buffer = new byte[BUFSIZE];
		while(listenSocket!=null) {
			try {
				Arrays.fill(buffer, (byte) ' ');
				DatagramPacket packet = new DatagramPacket(buffer,BUFSIZE);
				listenSocket.receive(packet);
				String ipOrig = packet.getAddress().toString();
				String data = new String(packet.getData()).trim();
				Sender s = new Sender(ipOrig,3321);
				System.out.println(data);
				switch (data){
				case "Connect123456CodeConnection":
						s.sendMessage("Informe seu nome de usuário: ");
					break;
					

				default:
					break;
				}
				//System.out.println(new String(packet.getData()));
				Thread.yield();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
