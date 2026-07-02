package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.enums.PerfilUsuario;

public class CredencialResponseDTO {

    private Long id;
	private String login;
	private PerfilUsuario perfil;
	private Long pessoaId;
	private String pessoaNome;

  public CredencialResponseDTO() {
	}

	public CredencialResponseDTO(Usuario entity) {
		this.id = entity.getId();
		this.login = entity.getLogin();
		this.perfil = entity.getPerfil();
		this.pessoaId = entity.getPessoa().getId();
		this.pessoaNome = entity.getPessoa().getNome();
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public PerfilUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}

	public void setPessoaNome(String pessoaNome) {
		this.pessoaNome = pessoaNome;
	}  

    

}
