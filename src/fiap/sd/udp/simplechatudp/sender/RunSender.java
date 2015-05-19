package fiap.sd.udp.simplechatudp.sender;

import fiap.sd.udp.simplechatudp.util.Console;

/**
 * Coloca no ar o lado "Falador" do chat UDP simples
 * @author fm
 *
 */
public class RunSender {
	
	public static void main(String[] args) {
		Console console = Console.getConsole();
		console.println("SENDER");
		String host = console.readLine("Qual o endereco do host com quem vai falar? > ");
		int port = Integer.parseInt(console.readLine("Em qual porta em que ele esta' ouvindo? > "));
		Sender sender = new Sender(host,port);
		sender.run();
		
		
		
		
	}

}
