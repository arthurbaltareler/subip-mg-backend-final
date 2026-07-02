package com.cefet.subip_mg_backend.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.cefet.subip_mg_backend.dto.CredencialResponseDTO;
import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public CredencialResponseDTO autenticar(String login, String senha) {
        Usuario entity = usuarioRepository.findByLoginAndSenha(login, senha);

        if (entity == null) {
			throw new ResourceNotFoundException("Login ou senha inválidos.");
		}

        return new CredencialResponseDTO(entity);
    }
}
