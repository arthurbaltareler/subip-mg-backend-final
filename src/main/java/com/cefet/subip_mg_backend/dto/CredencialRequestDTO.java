package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CredencialRequestDTO {

    @NotBlank(message = "O login e obrigatorio.")
	@Size(max = 100, message = "Limite de caracteres atingido.")
	private String login;

    @NotBlank(message = "A senha e obrigatoria.")
	@Size(max = 100, message = "Limite de caracteres atingido.")
	private String senha;

    public CredencialRequestDTO() {
	}

    public CredencialRequestDTO(String login, String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
