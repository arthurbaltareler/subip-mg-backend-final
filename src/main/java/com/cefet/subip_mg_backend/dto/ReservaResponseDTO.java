package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;

import com.cefet.subip_mg_backend.entities.Reserva;
import com.cefet.subip_mg_backend.enums.SituacaoReserva;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "situacao", "dataRegistro", "tituloLivro", "tombo", "biblioteca", "livroId",
		"exemplarId", "bibliotecaId", "pessoaId", "pessoaNome" })
public class ReservaResponseDTO {

	private Long id;
	private SituacaoReserva situacao;
	private LocalDate dataRegistro;
	private String tituloLivro;
	private String tombo;
	private String biblioteca;
	private Long livroId;
	private Long exemplarId;
	private Long bibliotecaId;
	private Long pessoaId;
	private String pessoaNome;

	public ReservaResponseDTO() {
	}

	public ReservaResponseDTO(Reserva entity) {
		this.id = entity.getId();
		this.situacao = entity.getSituacao();
		this.dataRegistro = entity.getDataRegistro();
		this.tituloLivro = entity.getExemplar().getLivro().getTitulo();
		this.tombo = entity.getExemplar().getTombo();
		this.biblioteca = entity.getExemplar().getBiblioteca().getNome();
		this.livroId = entity.getExemplar().getLivro().getId();
		this.exemplarId = entity.getExemplar().getId();
		this.bibliotecaId = entity.getExemplar().getBiblioteca().getId();
		this.pessoaId = entity.getPessoa().getId();
		this.pessoaNome = entity.getPessoa().getNome();
	}

	public Long getId() {
		return id;
	}

	public SituacaoReserva getSituacao() {
		return situacao;
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public String getTituloLivro() {
		return tituloLivro;
	}

	public String getTombo() {
		return tombo;
	}

	public String getBiblioteca() {
		return biblioteca;
	}

	public Long getLivroId() {
		return livroId;
	}

	public Long getExemplarId() {
		return exemplarId;
	}

	public Long getBibliotecaId() {
		return bibliotecaId;
	}

	public Long getPessoaId() {
		return pessoaId;
	}

	public String getPessoaNome() {
		return pessoaNome;
	}
}
