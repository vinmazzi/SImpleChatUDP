package fiap.sd.udp.simplechatudp.beans;

import java.util.ArrayList;
import java.util.List;

public class Sala {

	Usuario owner;
	String nome;
	String descSala;
	List<Usuario> usuarios = new ArrayList<Usuario>();

	public String getDescSala() {
		return descSala;
	}
	public void setDescSala(String descSala) {
		this.descSala = descSala;
	}	
	public Usuario getOwner() {
		return owner;
	}
	public void setOwner(Usuario owner) {
		this.owner = owner;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuario u) {
		usuarios.add(u);
	}
	
	
}