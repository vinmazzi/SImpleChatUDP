package fiap.sd.udp.simplechatudp.bo;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.List;

import fiap.sd.udp.simplechatudp.beans.Sala;
import fiap.sd.udp.simplechatudp.beans.Servidor;
import fiap.sd.udp.simplechatudp.beans.Usuario;
import fiap.sd.udp.simplechatudp.interfaces.ServerInterface;
import fiap.sd.udp.simplechatudp.receiver.Receiver;
import fiap.sd.udp.simplechatudp.receiver.RunReceiver;
import fiap.sd.udp.simplechatudp.sender.Sender;

public class ServerInstance implements ServerInterface {

	Servidor serverInstance = new Servidor();

	public ServerInstance(){
		int serverPort = 3333;
		DatagramSocket listenSocket;
		serverInstance.setNome("Servidor do Vinicius");
		serverInstance.setIp("127.0.0.1");
		Receiver r = new Receiver(serverPort);
		listenSocket = r.getListenSocket();
		while(listenSocket != null){
			DatagramPacket packet = r.run(serverInstance);
			String ipOrig = packet.getAddress().toString().replace("/", "").trim();
			String msg = new String(packet.getData()).trim();
			int port = 3321;
			Sender s = new Sender(ipOrig,port);
			System.out.println(msg);
			String splitter = "%%%Cod3%%%";
			String tmp[] = msg.split(splitter);
			String code = tmp[0];
			String data = null;
			if(tmp.length > 1){
				data = tmp[1];
			}
			
			
			switch (code){
			case "Connect123456CodeConnection":
				s.sendMessage("Connect123456CodeConnection-Closed"+ splitter);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s = new Sender(ipOrig,3322);
				s.sendMessage("Bem Vindo ao: " + serverInstance.getNome());
				s.sendMessage("1234UsernameQuest4321" + splitter);
				break;
			case "1234UsernameAsw4321":
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Cadastrando usuário no servidor");
				this.registrarUsuario(data, ipOrig);
				s = new Sender(ipOrig,3322);
				s.sendMessage("1234MenuSelect4321" + splitter + "\nInforme o que quer fazer:\n1- Entrar em uma sala disponivel\n2 - Mandar mensagem privada para um usuário ONLINE.");
				break;
			case "1234MenuSelectAsw4321":
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			default:
				break;
			}
			Thread.yield();
		}
	}
	
	@Override
	public void registrarUsuario(String user, String ip) {

		Usuario u = new Usuario();
		u.setNickName(user);
		u.setIp(ip);
		u.setOnline(true);
		serverInstance.setUsuarios(u);

	}

	@Override
	public List<Sala> listarSalas(Servidor servidor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarUsuarios(Sala sala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void criarSala(Sala sala, Servidor servidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizarSala(Sala sala, Servidor servidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSala(Sala sala, Servidor servidor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entrarSala(Sala sala, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sairSala(Sala sala, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarMsg(Sala sala, String msg, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarMsgPrivada(Usuario usuarioOrig, Usuario usuarioDest,
			String msg) {
		// TODO Auto-generated method stub
		
	}

}
