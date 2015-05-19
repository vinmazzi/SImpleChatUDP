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
		Usuario usuarioTmp = null;
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
			String nomeSala = null;
			String nomeUsuario = null;
			if(tmp.length > 1){
				//System.out.println("#############\n\nEsse é o valor de tmp[1]: " + tmp[1]);
					data = tmp[1];
			}
			
			System.out.println("Este é o código: " + code);
			switch (code){

			case "Connect123456CodeConnection":
				System.out.println("Estou no connect!");
				s.sendMessage("Connect123456CodeConnection-Closed" + splitter);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s = new Sender(ipOrig,3322);
				//s.sendMessage("Bem Vindo ao: " + serverInstance.getNome());
				s.sendMessage("1234UsernameQuest4321" + splitter);
				break;
			case "1234UsernameAsw4321":
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Cadastrando usuário no servidor");
				usuarioTmp = this.registrarUsuario(data, ipOrig);
				s = new Sender(ipOrig,3322);
				s.sendMessage("1234MenuSelect4321" + splitter + "\nInforme o que quer fazer:\n1 - Entrar em uma sala disponivel\n2 - Mandar mensagem privada para um usuário ONLINE.");
				break;
			case "1234MenuSelectAsw4321":
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(Integer.parseInt(data) == 1){
					List<Sala> sala = this.listarSalas(serverInstance);
					s = new Sender(ipOrig,3322);
					if(sala.size() == 0){
						s.sendMessage("1234SalaIndisponivel4321" + splitter + "\nNão existem salas disponiveis, deseja criar uma sala ?");
					}else{
						s.sendMessage("1234InicioListaSalas4321" + splitter + "\nEstas são as salas disponiveis: ");
						for(int i=0;i<sala.size();i++){
							Sala salaTmp = sala.get(i);
							s.sendMessage("1234Sala4321"+ splitter + salaTmp.getNome());
						}
						s.sendMessage("1234FimListaDeSalas4321" + splitter + "");
					}
				}
				break;
			case "1234SalaIndisponivelAsw4321":
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s = new Sender(ipOrig,3322);
				if(data.toLowerCase().equals("s")){
					s.sendMessage("1234CriaNovaSalaNome4321" + splitter + "");
				}else{
					
				}
				break;
			case "1234CriaNovaSalaNomeAsw4321":
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				s = new Sender(ipOrig,3322);
				Sala salaTmp = this.criarSala(data,serverInstance, usuarioTmp);
				this.entrarSala(salaTmp, usuarioTmp);
				s.sendMessage("1234Message4321" + splitter + "Sala Foi criada!");
				s.sendMessage("1234InSala4321" + splitter + salaTmp.getNome());
				
			/*	List<Sala> salalist = this.listarSalas(serverInstance);
				for(int i=0;i<salalist.size();i++){
					Sala tmp2 = salalist.get(i);
					List<Usuario> u2 = tmp2.getUsuarios();
					for(int j=0;j<u2.size();j++){
						Usuario utmp = u2.get(j);
						System.out.println(utmp.getNickName());
					}

				}*/
				break;
			case "1234InSalaAsw4321":
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				nomeSala = tmp[2];
				nomeUsuario = tmp[3];
				data = tmp[4];
				List<Sala> salaMsgList = serverInstance.getSalas();
				Sala salaMsg = null;
				List<Usuario> usrMsgList = serverInstance.getUsuarios();
				Usuario usrMsg = null;
				for(int i=0;i<salaMsgList.size();i++){
					salaMsg = salaMsgList.get(i);
					if(salaMsg.getNome().equals(nomeSala)){
						break;
					}
				}
				for(int i=0;i<usrMsgList.size();i++){
					usrMsg = usrMsgList.get(i);
					if(usrMsg.getNickName().equals(nomeUsuario)){
						break;
					}
				}
				this.enviarMsg(salaMsg, data, usrMsg);
				break;
			default:
				break;
			}
			Thread.yield();
		}
	}
	
	@Override
	public Usuario registrarUsuario(String user, String ip) {

		Usuario u = new Usuario();
		u.setNickName(user);
		u.setIp(ip);
		u.setOnline(true);
		serverInstance.setUsuarios(u);
		return u;

	}

	@Override
	public List<Sala> listarSalas(Servidor servidor) {
		
		List<Sala> sala = serverInstance.getSalas();
		return sala;
	}

	@Override
	public List<Usuario> listarUsuarios(Sala sala) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sala criarSala(String nome, Servidor servidor, Usuario owner) {
		System.out.println("\n\n----To no Criar Sala.");
		Sala sala = new Sala();
		sala.setNome(nome);
		sala.setOwner(owner);
		servidor.addSalas(sala);
		System.out.println("\n\n----Terminei o criar sala antes do return.");

		return sala;
		
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
		sala.setUsuarios(usuario);
	}

	@Override
	public void sairSala(Sala sala, Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarMsg(Sala sala, String msg, Usuario usuario) {
		
		List<Usuario> usrLst = sala.getUsuarios();
		Usuario u = null; 
		String nickName = null;
		String ipDest = null;
		String splitter = "%%%Cod3%%%";
		String msgCode = "1234InSalaMsg4321" + splitter;
		String sMsg = null;
		Sender s = null;
		
		
		for(int i=0;i<usrLst.size();i++){
			u = usrLst.get(i);
			nickName = u.getNickName();
			ipDest = u.getIp();
			sMsg = msgCode + nickName + splitter + msg;
			s = new Sender(ipDest,3322);
			s.sendMessage(sMsg);
		}
		
	}

	@Override
	public void enviarMsgPrivada(Usuario usuarioOrig, Usuario usuarioDest,
			String msg) {
		// TODO Auto-generated method stub
		
	}

}
