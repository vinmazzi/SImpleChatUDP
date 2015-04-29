package fiap.sd.udp.simplechatudp.receiver;

import fiap.sd.udp.simplechatudp.util.Console;

/**
 * Coloca no ar o lado "Ouvidor" do chat UDP simples
 * @author fm
 *
 */
public class RunReceiver {
	
	public static void main(String[] args) 
	{
		Console console = Console.getConsole();
		console.println("RECEIVER");
		int port = Integer.parseInt(console.readLine("Em qual porta deseja ouvir? > "));
		Receiver rec = new Receiver(port);
		rec.run();
	}
	


}
