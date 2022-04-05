package modelos;

import java.io.Serializable;

public class ModeloLogin implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private String nome;
	private Long id;
	private String email;
	private String login;
	private String senha;
	
	public boolean isNOVO() {
		if(this.id == null) {
			return true; /*Inserir novo usuário*/
		}else if(this.id != null && this.id>0){
			
			return false; /*Atualizar usuário*/
		}
		return id == null;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
