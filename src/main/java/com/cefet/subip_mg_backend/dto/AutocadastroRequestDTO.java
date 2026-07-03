package com.cefet.subip_mg_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AutocadastroRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome deve possuir no máximo 255 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Size(max = 14, message = "O CPF deve possuir no máximo 14 caracteres.")
    private String cpf;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail é inválido.")
    @Size(max = 254, message = "O e-mail deve possuir no máximo 254 caracteres.")
    private String email;

    @NotBlank(message = "O login é obrigatório.")
    @Size(max = 50, message = "O login deve possuir no máximo 50 caracteres.")
    private String login;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 6, max = 255, message = "A senha deve possuir entre 6 e 255 caracteres.")
    private String senha;

    public AutocadastroRequestDTO() {
    }

    public AutocadastroRequestDTO(String nome, String cpf, String email, String login, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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