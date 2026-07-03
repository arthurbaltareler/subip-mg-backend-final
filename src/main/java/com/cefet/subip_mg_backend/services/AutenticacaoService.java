package com.cefet.subip_mg_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.subip_mg_backend.dto.AutocadastroRequestDTO;
import com.cefet.subip_mg_backend.dto.CredencialResponseDTO;
import com.cefet.subip_mg_backend.dto.UsuarioResponseDTO;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.enums.PerfilUsuario;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;
import com.cefet.subip_mg_backend.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class AutenticacaoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public CredencialResponseDTO autenticar(String login, String senha) {
        Usuario entity = usuarioRepository.findByLoginAndSenha(login, senha);

        if (entity == null) {
            throw new ResourceNotFoundException("Login ou senha inválidos.");
        }

        return new CredencialResponseDTO(entity);
    }

    @Transactional
    public UsuarioResponseDTO cadastrarLeitor(AutocadastroRequestDTO dto) {

        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new DatabaseException("Login ja cadastrado.");
        }

        Pessoa pessoa = pessoaRepository.findByCpf(dto.getCpf());

        if (pessoa == null) {

            if (pessoaRepository.existsByEmail(dto.getEmail())) {
                throw new DatabaseException("Email ja cadastrado.");
            }

            pessoa = new Pessoa();
            pessoa.setNome(dto.getNome());
            pessoa.setCpf(dto.getCpf());
            pessoa.setEmail(dto.getEmail());

            pessoa = pessoaRepository.save(pessoa);

        } else {

            if (pessoa.getEmail() == null || !pessoa.getEmail().equals(dto.getEmail())) {
                throw new DatabaseException(
                        "O email informado nao corresponde ao CPF.");
            }

            Usuario usuarioExistente = usuarioRepository.findByPessoaAndPerfil(
                    pessoa,
                    PerfilUsuario.LEITOR);

            if (usuarioExistente != null) {
                throw new DatabaseException(
                        "Ja existe uma conta de leitor para esse CPF.");
            }
        }

        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(PerfilUsuario.LEITOR);
        usuario.setPessoa(pessoa);

        usuario = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuario);
    }

}
