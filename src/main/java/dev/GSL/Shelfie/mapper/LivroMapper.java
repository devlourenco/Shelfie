package dev.GSL.Shelfie.mapper;

import dev.GSL.Shelfie.dto.LivroDTO;
import dev.GSL.Shelfie.model.LivroModel;
import org.springframework.stereotype.Component;


@Component
public class LivroMapper {

    public LivroModel toModel(LivroDTO livroDTO) {
        LivroModel livroModel = new LivroModel();
        livroModel.setId(livroDTO.getId());
        livroModel.setTitulo(livroDTO.getTitulo());
        livroModel.setAutor(livroDTO.getAutor());
        livroModel.setTotalPaginas(livroDTO.getTotalPaginas());
        livroModel.setCategoria(livroDTO.getCategoria());
        livroModel.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livroModel.setObservacoes(livroDTO.getObservacoes());

        return livroModel;
    }

    public LivroDTO toDto(LivroModel livroModel) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livroModel.getId());
        livroDTO.setTitulo(livroModel.getTitulo());
        livroDTO.setAutor(livroModel.getAutor());
        livroDTO.setTotalPaginas(livroModel.getTotalPaginas());
        livroDTO.setCategoria(livroModel.getCategoria());
        livroDTO.setAnoPublicacao(livroModel.getAnoPublicacao());
        livroDTO.setObservacoes(livroModel.getObservacoes());

        return livroDTO;
    }
}
