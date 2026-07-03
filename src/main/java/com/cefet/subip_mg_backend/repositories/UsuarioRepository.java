package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.entities.Usuario;
import com.cefet.subip_mg_backend.enums.PerfilUsuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	boolean existsByLogin(String login);
	boolean existsByPessoaId(Long pessoaId);
	List<Usuario> findByPessoa(Pessoa pessoa);
	Usuario findByPessoaAndPerfil(Pessoa pessoa, PerfilUsuario perfil);
	Usuario findByLoginAndSenha(String login, String senha);
}
