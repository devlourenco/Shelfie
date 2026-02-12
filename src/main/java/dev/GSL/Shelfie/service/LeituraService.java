package dev.GSL.Shelfie.service;

import dev.GSL.Shelfie.dto.LeituraDTO;
import dev.GSL.Shelfie.enums.StatusLeitura;
import dev.GSL.Shelfie.mapper.LeituraMapper;
import dev.GSL.Shelfie.model.LeituraModel;
import dev.GSL.Shelfie.repository.LeituraRepository;

import java.util.List;

public class LeituraService {

    private final LeituraRepository repository;
    private final LeituraMapper mapper;

    public LeituraService(LeituraRepository repository, LeituraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LeituraDTO criarLeitura(LeituraDTO leituraDTO) {
        LeituraModel leitura = mapper.toModel(leituraDTO);

        if (leitura.getLivro() != null) {
            if (leitura.getDataInicio() == null) {
                throw new RuntimeException("Informe uma data de ínicio da leitura");
            }
            if (leitura.getDataFim() == null && leitura.getDataFim().isBefore(leitura.getDataInicio())) {
                throw new RuntimeException("A data de conclusão da leitura não pode ser anterior a data de começo da leitura.");
            }
            if (leitura.getStatus() == StatusLeitura.FINALIZADO) {
                if (leitura.getDataFim() == null) {
                    throw new RuntimeException("Por estar finalizado, a data de fim é obrigatória.");
                }
            }
            if (leitura.getStatus() == StatusLeitura.EM_ANDAMENTO) {
                if (leitura.getDataFim() != null) {
                    leitura.setDataFim(null);
                }
            }
            if (leitura.getPaginaAtual() >= 0) {
                if (leitura.getStatus() == StatusLeitura.NAO_INICIADO) {
                    leitura.setPaginaAtual(0);
                }
                if (leitura.getStatus() == StatusLeitura.EM_ANDAMENTO) {
                    if (leitura.getPaginaAtual() <= 0) {
                        throw new RuntimeException("Por estar lendo, a página atual deve ser maior que 0.");
                    }
                    if (leitura.getPaginaAtual() > leitura.getPaginasTotais()) {
                        throw new RuntimeException("A página atual deve ser menor que o número de páginas totais.");
                    }
                    if (leitura.getStatus() == StatusLeitura.FINALIZADO) {
                        if (leitura.getPaginaAtual() != leitura.getPaginasTotais()) {
                            throw new RuntimeException("Para o livro ser finalizado, a página atual deve ser a mesma do número total de páginas do livro.");
                        }
                    }
                }
            } else {
                throw new RuntimeException("A página atual deve ser maior que 0.");
            }
            if (leitura.getAvaliacao() < 0 || leitura.getAvaliacao() > 5) {
                throw new RuntimeException("A avaliação deve estar entre 0 a 5, sendo 0 péssimo e 5 excelente.");
            }
        } else {
            throw new RuntimeException("O livro deve existir na lista, crie um livro para iniciar os registros.");
        }
        LeituraModel leituraSalva = repository.save(leitura);
        return mapper.toDto(leituraSalva);
    }

    public List<LeituraDTO> listarLeituras() {
        List<LeituraModel> leituras = repository.findAll();
        return leituras.stream()
                .map(mapper::toDto)
                .toList();
    }
}
