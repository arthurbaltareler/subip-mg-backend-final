package com.cefet.subip_mg_backend.dto;

import com.cefet.subip_mg_backend.enums.PerfilUsuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {

	@NotBlank(message = "O login e obrigatorio.")
	@Size(max = 100, message = "Limite de caracteres atingido.")
	private String login;

	@NotBlank(message = "A senha e obrigatoria.")
	@Size(max = 100, message = "Limite de caracteres atingido.")
	private String senha;

	@NotNull(message = "O perfil e obrigatorio.")
	private PerfilUsuario perfil;

	@NotNull(message = "O pessoaId e obrigatorio.")
	private Long pessoaId;

	public UsuarioRequestDTO() {
	}

	public UsuarioRequestDTO(String login, String senha, PerfilUsuario perfil, Long pessoaId) {
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.pessoaId = pessoaId;
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

}
