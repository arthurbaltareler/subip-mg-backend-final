package com.cefet.subip_mg_backend.dto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.cefet.subip_mg_backend.entities.Emprestimo;
import com.cefet.subip_mg_backend.enums.SituacaoEmprestimo;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "tituloLivro", "tombo", "biblioteca", "situacao", "dataRetirada",
		"dataDevolucaoPrevista", "dataDevolucao", "livroId", "exemplarId", "bibliotecaId", "pessoaId", "pessoaNome",
		"diasAtraso" })
public class EmprestimoResponseDTO {

	private Long id;
	private String tituloLivro;
	private String tombo;
	private String biblioteca;
	private SituacaoEmprestimo situacao;
	private LocalDate dataRetirada;
	private LocalDate dataDevolucaoPrevista;
	private LocalDate dataDevolucao;
	private Long livroId;
	private Long exemplarId;
	private Long bibliotecaId;
	private Long pessoaId;
	private String pessoaNome;
	private Long diasAtraso;

	public EmprestimoResponseDTO() {
	}

	public EmprestimoResponseDTO(Emprestimo entity) {
		this.id = entity.getId();
		this.tituloLivro = entity.getExemplar().getLivro().getTitulo();
		this.tombo = entity.getExemplar().getTombo();
		this.biblioteca = entity.getExemplar().getBiblioteca().getNome();
		this.situacao = entity.getSituacao();
		this.dataRetirada = entity.getDataRetirada();
		this.dataDevolucaoPrevista = entity.getDataDevolucaoPrevista();
		this.dataDevolucao = entity.getDataDevolucao();
		this.livroId = entity.getExemplar().getLivro().getId();
		this.exemplarId = entity.getExemplar().getId();
		this.bibliotecaId = entity.getExemplar().getBiblioteca().getId();
		this.pessoaId = entity.getPessoa().getId();
		this.pessoaNome = entity.getPessoa().getNome();
		this.diasAtraso = calcularDiasAtraso(entity);
	}

	public Long getId() {
		return id;
	}

	public SituacaoEmprestimo getSituacao() {
		return situacao;
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

	public LocalDate getDataRetirada() {
		return dataRetirada;
	}

	public LocalDate getDataDevolucaoPrevista() {
		return dataDevolucaoPrevista;
	}

	public LocalDate getDataDevolucao() {
		return dataDevolucao;
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

	public Long getDiasAtraso() {
		return diasAtraso;
	}

	private Long calcularDiasAtraso(Emprestimo entity) {
		if (entity.getDataDevolucaoPrevista() == null) {
			return 0L;
		}

		LocalDate dataReferencia = entity.getDataDevolucao() != null ? entity.getDataDevolucao() : LocalDate.now();
		long dias = ChronoUnit.DAYS.between(entity.getDataDevolucaoPrevista(), dataReferencia);

		return Math.max(dias, 0L);
	}
}
