package com.cefet.subip_mg_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cefet.subip_mg_backend.dto.AutocadastroRequestDTO;
import com.cefet.subip_mg_backend.dto.CredencialRequestDTO;
import com.cefet.subip_mg_backend.dto.CredencialResponseDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.services.AutenticacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Autenticações")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário")
    public ResponseEntity<CredencialResponseDTO> autenticar(@Valid @RequestBody CredencialRequestDTO dto) {
        CredencialResponseDTO response = autenticacaoService.autenticar(dto.getLogin(), dto.getSenha());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/autocadastro")
    @Operation(summary = "Cadastrar leitor")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@Valid @RequestBody AutocadastroRequestDTO dto) {

        UsuarioResponseDTO response = autenticacaoService.cadastrarLeitor(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
