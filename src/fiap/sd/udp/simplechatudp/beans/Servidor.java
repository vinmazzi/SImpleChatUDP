package fiap.sd.udp.simplechatudp.beans;

import java.util.ArrayList;
import java.util.List;

public class Servidor {

	String nome;
	String ip;
	List<Sala> salas = new ArrayList<Sala>();
	List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<Sala> getSalas() {
		return salas;
	}
	public void addSalas(Sala s) {
		salas.add(s);
	}
	public void delSalas(Sala s) {
		salas.remove(s);
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuario u) {
		usuarios.add(u);
	}

	
}
