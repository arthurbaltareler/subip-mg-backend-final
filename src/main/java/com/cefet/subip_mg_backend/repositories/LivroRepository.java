package com.cefet.subip_mg_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cefet.subip_mg_backend.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	boolean existsByIsbn(String isbn);
	boolean existsByGeneroId(Long generoId);

	// Mantem a listagem e a busca de catalogo no mesmo endpoint.
	@Query("""
			SELECT l
			FROM Livro l
			WHERE (:filtrarTitulo = false OR LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%')))
			AND (:filtrarIsbn = false OR l.isbn = :isbn)
			""")
	List<Livro> buscarPorFiltros(
			@Param("titulo") String titulo,
			@Param("filtrarTitulo") boolean filtrarTitulo,
			@Param("isbn") String isbn,
			@Param("filtrarIsbn") boolean filtrarIsbn);
}
