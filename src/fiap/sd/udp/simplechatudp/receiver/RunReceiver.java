package fiap.sd.udp.simplechatudp.receiver;

import com.sun.corba.se.spi.activation.Server;

import fiap.sd.udp.simplechatudp.beans.Servidor;
import fiap.sd.udp.simplechatudp.util.Console;

/**
 * Coloca no ar o lado "Ouvidor" do chat UDP simples
 * @author fm
 *
 */
public class RunReceiver {
	
	static Servidor sInstance;
	
	public void RunReceiver(Servidor s){
		sInstance = s;
	}
	
	public static void main(String[] args) 
	{
		Console console = Console.getConsole();
		console.println("RECEIVER");
		//int port = Integer.parseInt(console.readLine("Em qual porta deseja ouvir? > "));
		int port = 3333;
		Receiver rec = new Receiver(port);
		rec.run(sInstance);
	}
	


}
