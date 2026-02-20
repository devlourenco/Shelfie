package dev.GSL.Shelfie.service;

import dev.GSL.Shelfie.dto.LeituraDTO;
import dev.GSL.Shelfie.exception.RecursoNaoEncontrado;
import dev.GSL.Shelfie.exception.RegraDeNegocio;
import dev.GSL.Shelfie.mapper.LeituraMapper;
import dev.GSL.Shelfie.model.LeituraModel;
import dev.GSL.Shelfie.repository.LeituraRepository;

import java.util.List;
import java.util.Optional;

public class LeituraService {

    private final LeituraRepository repository;
    private final LeituraMapper mapper;

    public LeituraService(LeituraRepository repository, LeituraMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public LeituraDTO criarLeitura(LeituraDTO dto) {

        LeituraModel leitura = mapper.toModel(dto);

        validarDatas(leitura);
        validarStatus(leitura);

        return mapper.toDto(repository.save(leitura));
    }

    private void validarDatas(LeituraModel leitura) {

        if (leitura.getDataFim() != null &&
                leitura.getDataFim().isBefore(leitura.getDataInicio())) {

            throw new RegraDeNegocio(
                    "A data de conclusão não pode ser anterior à data de início.");
        }
    }

    private void validarStatus(LeituraModel leitura) {

        switch (leitura.getStatus()) {

            case NAO_INICIADO:
                leitura.setPaginaAtual(0);
                leitura.setDataFim(null);
                break;

            case EM_ANDAMENTO:
                validarEmAndamento(leitura);
                break;

            case FINALIZADO:
                validarFinalizado(leitura);
                break;
        }
    }

    private void validarEmAndamento(LeituraModel leitura) {

        if (leitura.getPaginaAtual() <= 0) {
            throw new RegraDeNegocio(
                    "Por estar lendo, a página atual deve ser maior que 0.");
        }

        if (leitura.getPaginaAtual() > leitura.getPaginasTotais()) {
            throw new RegraDeNegocio(
                    "A página atual não pode ultrapassar o total de páginas.");
        }

        leitura.setDataFim(null);
    }
    private void validarFinalizado(LeituraModel leitura) {

        if (leitura.getDataFim() == null) {
            throw new RegraDeNegocio(
                    "Por estar finalizado, a data de fim é obrigatória.");
        }

        if (leitura.getPaginaAtual() != (leitura.getPaginasTotais())) {
            throw new RegraDeNegocio(
                    "Para finalizar, a página atual deve ser igual ao total de páginas.");
        }
    }


    public List<LeituraDTO> listarLeituras() {
        List<LeituraModel> leituras = repository.findAll();
        return leituras.stream()
                .map(mapper::toDto)
                .toList();
    }

    public LeituraDTO listarLeituraporId(Long id) {
        Optional<LeituraModel> leituraPorId = repository.findById(id);
        return leituraPorId.map(mapper::toDto).orElseThrow(() -> new RecursoNaoEncontrado("Leitura não encontrada."));
    }

    public LeituraDTO atualizarLeitura(Long id, LeituraDTO leituraDTO) {

        LeituraModel leitura = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Leitura não encontrada"));

        validarDatas(leitura);
        validarStatus(leitura);

        leitura.setDataInicio(leituraDTO.getDataInicio());
        leitura.setDataFim(leituraDTO.getDataFim());
        leitura.setStatus(leituraDTO.getStatus());
        leitura.setPaginaAtual(leituraDTO.getPaginaAtual());
        leitura.setPaginasTotais(leituraDTO.getPaginasTotais());
        leitura.setAvaliacao(leituraDTO.getAvaliacao());
        leitura.setComentarioPessoal(leituraDTO.getComentarioPessoal());
        leitura.setLivro(leituraDTO.getLivro());

        return mapper.toDto(repository.save(leitura));
    }

    public void deletarLeitura(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontrado("Leitura não encontrada para exclusão.");
        }

        repository.deleteById(id);
    }

}

