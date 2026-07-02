package com.cefet.subip_mg_backend.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cefet.subip_mg_backend.dto.EmprestimoDevolucaoRequestDTO;
import com.cefet.subip_mg_backend.dto.EmprestimoRequestDTO;
import com.cefet.subip_mg_backend.dto.EmprestimoRenovacaoRequestDTO;
import com.cefet.subip_mg_backend.dto.EmprestimoResponseDTO;
import com.cefet.subip_mg_backend.entities.Emprestimo;
import com.cefet.subip_mg_backend.entities.Exemplar;
import com.cefet.subip_mg_backend.entities.Pessoa;
import com.cefet.subip_mg_backend.entities.Reserva;
import com.cefet.subip_mg_backend.enums.SituacaoEmprestimo;
import com.cefet.subip_mg_backend.enums.SituacaoExemplar;
import com.cefet.subip_mg_backend.enums.SituacaoReserva;
import com.cefet.subip_mg_backend.exceptions.DatabaseException;
import com.cefet.subip_mg_backend.exceptions.ResourceNotFoundException;
import com.cefet.subip_mg_backend.repositories.EmprestimoRepository;
import com.cefet.subip_mg_backend.repositories.ExemplarRepository;
import com.cefet.subip_mg_backend.repositories.PessoaRepository;
import com.cefet.subip_mg_backend.repositories.ReservaRepository;

@Service
public class EmprestimoService {

	private static final long PRAZO_PADRAO_DIAS = 15L;

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@Autowired
	private ExemplarRepository exemplarRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private ReservaRepository reservaRepository;

	@Transactional(readOnly = true)
	public List<EmprestimoResponseDTO> listar() {
		List<Emprestimo> lista = emprestimoRepository.findAll();
		return lista.stream().map(EmprestimoResponseDTO::new).toList();
	}

	@Transactional(readOnly = true)
	public EmprestimoResponseDTO buscarPorId(Long id) {
		Emprestimo entity = emprestimoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Emprestimo nao encontrado. Id: " + id));

		return new EmprestimoResponseDTO(entity);
	}

	@Transactional(readOnly = true)
	public List<EmprestimoResponseDTO> listarPorPessoa(Long pessoaId) {
		if (!pessoaRepository.existsById(pessoaId)) {
			throw new ResourceNotFoundException("Pessoa nao encontrada. Id: " + pessoaId);
		}

		List<Emprestimo> lista = emprestimoRepository.findByPessoaId(pessoaId);
		return lista.stream().map(EmprestimoResponseDTO::new).toList();
	}

	@Transactional
	public EmprestimoResponseDTO devolver(Long id, EmprestimoDevolucaoRequestDTO dto) {
		Emprestimo entity = buscarEntidadePorId(id);

		if (!emprestimoAberto(entity)) {
			throw new DatabaseException("Somente emprestimos abertos podem ser devolvidos.");
		}

		if (dto.getDataDevolucao() != null && dto.getDataDevolucao().isBefore(entity.getDataRetirada())) {
			throw new DatabaseException("A data de devolucao nao pode ser anterior a data de retirada.");
		}

		entity.setDataDevolucao(dto.getDataDevolucao());
		entity.setSituacao(SituacaoEmprestimo.DEVOLVIDO);
		entity.getExemplar().setSituacao(situacaoAposDevolucao(entity.getExemplar()));

		return new EmprestimoResponseDTO(entity);
	}

	@Transactional
	public EmprestimoResponseDTO registrar(EmprestimoRequestDTO dto) {
		LocalDate dataDevolucaoPrevista = calcularDataDevolucaoPrevista(dto.getDataRetirada());

		Exemplar exemplar = exemplarRepository.findById(dto.getExemplarId())
				.orElseThrow(() -> new ResourceNotFoundException("Exemplar nao encontrado. Id: " + dto.getExemplarId()));

		Pessoa pessoa = pessoaRepository.findById(dto.getPessoaId())
				.orElseThrow(() -> new ResourceNotFoundException("Pessoa nao encontrada. Id: " + dto.getPessoaId()));

		Reserva reservaAtiva = validarExemplarParaEmprestimo(exemplar, pessoa);

		Emprestimo entity = new Emprestimo();
		entity.setSituacao(SituacaoEmprestimo.EM_ANDAMENTO);
		entity.setDataRetirada(dto.getDataRetirada());
		entity.setDataDevolucaoPrevista(dataDevolucaoPrevista);
		entity.setDataDevolucao(null);
		entity.setExemplar(exemplar);
		entity.setPessoa(pessoa);

		exemplar.setSituacao(SituacaoExemplar.EMPRESTADO);
		if (reservaAtiva != null) {
			reservaAtiva.setSituacao(SituacaoReserva.ATENDIDA);
		}
		entity = emprestimoRepository.save(entity);

		return new EmprestimoResponseDTO(entity);
	}

	@Transactional
	public EmprestimoResponseDTO renovar(Long id, EmprestimoRenovacaoRequestDTO dto) {
		Emprestimo entity = buscarEntidadePorId(id);

		if (!emprestimoAberto(entity)) {
			throw new DatabaseException("Somente emprestimos abertos podem ser renovados.");
		}

		if (dto.getDataDevolucaoPrevista() != null
				&& !dto.getDataDevolucaoPrevista().isAfter(entity.getDataDevolucaoPrevista())) {
			throw new DatabaseException("A nova data de devolucao prevista deve ser posterior a data atual prevista.");
		}

		entity.setDataDevolucaoPrevista(dto.getDataDevolucaoPrevista());
		entity.setSituacao(SituacaoEmprestimo.RENOVADO);

		return new EmprestimoResponseDTO(entity);
	}

	private LocalDate calcularDataDevolucaoPrevista(LocalDate dataRetirada) {
		if (dataRetirada == null) {
			throw new DatabaseException("A data de retirada e obrigatoria para registrar o emprestimo.");
		}

		return dataRetirada.plusDays(PRAZO_PADRAO_DIAS);
	}

	private SituacaoExemplar situacaoAposDevolucao(Exemplar exemplar) {
		if (reservaRepository.existsByExemplarIdAndSituacao(exemplar.getId(), SituacaoReserva.ATIVA)) {
			return SituacaoExemplar.RESERVADO;
		}

		return SituacaoExemplar.DISPONIVEL;
	}

	private Reserva validarExemplarParaEmprestimo(Exemplar exemplar, Pessoa pessoa) {
		if (exemplar.getSituacao() == SituacaoExemplar.DISPONIVEL) {
			return null;
		}

		if (exemplar.getSituacao() == SituacaoExemplar.RESERVADO) {
			Reserva reservaAtiva = reservaRepository
					.findFirstByExemplarIdAndSituacaoOrderByDataRegistroAscIdAsc(exemplar.getId(), SituacaoReserva.ATIVA)
					.orElseThrow(() -> new DatabaseException("Exemplar reservado sem reserva ativa."));

			if (!reservaAtiva.getPessoa().getId().equals(pessoa.getId())) {
				throw new DatabaseException("Exemplar reservado para outra pessoa.");
			}

			return reservaAtiva;
		}

		throw new DatabaseException("Esse exemplar nao esta disponivel no momento.");
	}

	private Emprestimo buscarEntidadePorId(Long id) {
		return emprestimoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Emprestimo nao encontrado. Id: " + id));
	}

	private boolean emprestimoAberto(Emprestimo entity) {
		return entity.getDataDevolucao() == null && entity.getSituacao() != SituacaoEmprestimo.DEVOLVIDO;
	}
}
