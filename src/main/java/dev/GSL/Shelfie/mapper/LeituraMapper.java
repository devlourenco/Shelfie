package dev.GSL.Shelfie.mapper;

import dev.GSL.Shelfie.dto.LeituraDTO;
import dev.GSL.Shelfie.model.LeituraModel;
import org.springframework.stereotype.Component;


@Component
public class LeituraMapper {

    public LeituraModel toModel(LeituraDTO leituraDTO) {
        LeituraModel leituraModel = new LeituraModel();
        leituraModel.setId(leituraDTO.getId());
        leituraModel.setDataInicio(leituraDTO.getDataInicio());
        leituraModel.setDataFim(leituraDTO.getDataFim());
        leituraModel.setStatus(leituraDTO.getStatus());
        leituraModel.setPaginaAtual(leituraDTO.getPaginaAtual());
        leituraModel.setPaginasTotais(leituraDTO.getPaginasTotais());
        leituraModel.setAvaliacao(leituraDTO.getAvaliacao());
        leituraModel.setComentarioPessoal(leituraDTO.getComentarioPessoal());
        leituraModel.setLivro(leituraDTO.getLivro());

        return leituraModel;
    }

    public LeituraDTO toDto(LeituraModel leituraModel) {
        LeituraDTO leituraDTO = new LeituraDTO();
        leituraDTO.setId(leituraModel.getId());
        leituraDTO.setDataInicio(leituraModel.getDataInicio());
        leituraDTO.setDataFim(leituraModel.getDataFim());
        leituraDTO.setStatus(leituraModel.getStatus());
        leituraDTO.setPaginaAtual(leituraModel.getPaginaAtual());
        leituraDTO.setPaginasTotais(leituraModel.getPaginasTotais());
        leituraDTO.setAvaliacao(leituraModel.getAvaliacao());
        leituraDTO.setComentarioPessoal(leituraModel.getComentarioPessoal());
        leituraDTO.setLivro(leituraModel.getLivro());

        return leituraDTO;
    }
}
