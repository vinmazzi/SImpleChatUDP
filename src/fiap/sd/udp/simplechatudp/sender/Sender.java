package fiap.sd.udp.simplechatudp.sender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Implementa o lado "Falador" de nosso chat UDP simples
 * @author fm
 *
 */
public class Sender {
	private DatagramSocket speakSocket;
	private InetAddress destAddress;
	private int destPort;
	private static final BufferedReader console = new BufferedReader(new InputStreamReader(
			System.in));
	private String teste;

	public Sender(String destAddr, int destPort) {
		try {
			this.teste = destAddr;
			this.speakSocket = new DatagramSocket();
			this.destAddress = InetAddress.getByName(destAddr);
			System.out.println(this.destAddress.toString());
			this.destPort = destPort;
			System.out.println("** Preparando para enviar mensagens para "
					+ this.destAddress.toString() + ":" + this.destPort);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void run() {
		String message = null;
		//while (speakSocket != null) {
			try {
				message = readMessage();
			} catch (IOException e) {
				System.out.println("Erro na entrada do teclado.");
				e.printStackTrace();
				System.exit(-1);
			}
			sendMessage(message);
		}
	//}

	private String readMessage() throws IOException {
		System.out.print("Mensagem a ser enviada > ");
		return console.readLine();
	}

	public void sendMessage(String command) {
		System.out.println("SendMessage\nIp: "+this.destAddress+"port: "+this.destPort+"ip antes da conversão: "+this.teste);
		DatagramPacket packet = new DatagramPacket(command.getBytes(),
				command.length(), this.destAddress, this.destPort);
		try {
			System.out.println(command);
			this.speakSocket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
