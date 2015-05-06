package fiap.sd.udp.simplechatudp.interfaces;

import java.util.List;

import fiap.sd.udp.simplechatudp.beans.Sala;
import fiap.sd.udp.simplechatudp.beans.Usuario;
import fiap.sd.udp.simplechatudp.beans.Servidor;

public interface ServerInterface {

	Usuario registrarUsuario(String user, String ip);
	List<Sala> listarSalas(Servidor servidor);
	List<Usuario> listarUsuarios(Sala sala);
	Sala criarSala(String nome, Servidor servidor, Usuario owner);
	void atualizarSala(Sala sala, Servidor servidor);
	void encerrarSala(Sala sala, Servidor servidor);
	void entrarSala(Sala sala, Usuario usuario);
	void sairSala(Sala sala, Usuario usuario);
	void enviarMsg(Sala sala,String msg,Usuario usuario);
	void enviarMsgPrivada(Usuario usuarioOrig, Usuario usuarioDest,String msg);
	
	
}
