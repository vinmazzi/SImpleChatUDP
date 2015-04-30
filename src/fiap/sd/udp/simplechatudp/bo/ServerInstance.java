package fiap.sd.udp.simplechatudp.bo;
import java.util.List;

import fiap.sd.udp.simplechatudp.beans.Sala;
import fiap.sd.udp.simplechatudp.beans.Servidor;
import fiap.sd.udp.simplechatudp.beans.Usuario;
import fiap.sd.udp.simplechatudp.interfaces.ServerInterface;
import fiap.sd.udp.simplechatudp.receiver.Receiver;

public class ServerInstance implements ServerInterface {

	public ServerInstance(){
		int port = 3333;
		Receiver rec = new Receiver(port);
		rec.run();
		
		
		
		
	}
	
	@Override
	public void registrarUsuario(Usuario user, String ip) {
		// TODO Auto-generated method stub
		
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
