package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
	boolean existsByTombo(String tombo);
	boolean existsByLivroId(Long livroId);
	boolean existsByBibliotecaId(Long bibliotecaId);
	List<Exemplar> findByLivroId(Long livroId);
	List<Exemplar> findByBibliotecaId(Long bibliotecaId);

	// Traz livro e biblioteca juntos para entregar uma visao pronta ao frontend.
	@Query("""
			SELECT e
			FROM Exemplar e
			JOIN FETCH e.livro l
			JOIN FETCH l.genero g
			JOIN FETCH e.biblioteca b
			WHERE (:filtrarTitulo = false OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
			AND (:filtrarGenero = false OR g.id = :generoId)
			AND (:filtrarBiblioteca = false OR b.id = :bibliotecaId)
			AND (:filtrarSituacao = false OR e.situacao = :situacao)
			""")
	List<Exemplar> buscarAcervo(
			@Param("titulo") String titulo,
			@Param("filtrarTitulo") boolean filtrarTitulo,
			@Param("generoId") Long generoId,
			@Param("filtrarGenero") boolean filtrarGenero,
			@Param("bibliotecaId") Long bibliotecaId,
			@Param("filtrarBiblioteca") boolean filtrarBiblioteca,
			@Param("situacao") SituacaoExemplar situacao,
			@Param("filtrarSituacao") boolean filtrarSituacao);
}
