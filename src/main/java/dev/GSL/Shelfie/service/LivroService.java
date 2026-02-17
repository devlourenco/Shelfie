package dev.GSL.Shelfie.service;

import dev.GSL.Shelfie.dto.LivroDTO;
import dev.GSL.Shelfie.exception.RecursoNaoEncontrado;
import dev.GSL.Shelfie.mapper.LivroMapper;
import dev.GSL.Shelfie.model.LivroModel;
import dev.GSL.Shelfie.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository repository;
    private final LivroMapper mapper;

    public LivroService(LivroRepository repository, LivroMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public LivroDTO criarLivro(LivroDTO livroDTO) {
        LivroModel livro = mapper.toModel(livroDTO);
        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty()) {
            throw new RecursoNaoEncontrado("Titulo do livro é obrigatório.");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new RecursoNaoEncontrado("Autor do livro é obrigatório.");
        }
        if (livro.getTotalPaginas() <= 0) {
            throw new RecursoNaoEncontrado("O número de páginas deve ser maior que zero.");
        }
        if (livro.getAnoPublicacao() <= 0 || livro.getAnoPublicacao() > LocalDateTime.now().getYear()) {
            throw new RecursoNaoEncontrado("Ano inválido, deve ser maior que zero e menor ou igual ano atual.");
        }
        if (livro.getCategoria() == null) {
            throw new RecursoNaoEncontrado("A categoria não pode estar vazia, organize seus livros!.");
        }

        LivroModel livroSalvo = repository.save(livro);
        return mapper.toDto(livroSalvo);
    }

    public List<LivroDTO> listarLivros() {
        List<LivroModel> livro = repository.findAll();
        return livro.stream()
                .map(mapper::toDto)
                .toList();
    }

    public LivroDTO buscarPorId(Long id) {
        Optional<LivroModel> livroPorId = repository.findById(id);
        return livroPorId.map(mapper::toDto).orElseThrow();
    }

    public LivroDTO atualizarLivro(LivroDTO livroDTO, Long id) {
        LivroModel livroExistente = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontrado("Livro não encontrado."));

        if (livroDTO.getTitulo() == null || livroDTO.getTitulo().trim().isEmpty()) {
            throw new RecursoNaoEncontrado("Titulo do livro é obrigatório.");
        }
        if (livroDTO.getAutor() == null || livroDTO.getAutor().trim().isEmpty()) {
            throw new RecursoNaoEncontrado("Autor do livro é obrigatório.");
        }
        if (livroDTO.getTotalPaginas() <= 0) {
            throw new RecursoNaoEncontrado("O número de páginas deve ser maior que zero.");
        }
        if (livroDTO.getAnoPublicacao() <= 0 || livroDTO.getAnoPublicacao() > LocalDateTime.now().getYear()) {
            throw new RecursoNaoEncontrado("Ano inválido, deve ser maior que zero e menor ou igual ano atual.");
        }
        if (livroDTO.getCategoria() == null) {
            throw new RecursoNaoEncontrado("A categoria não pode estar vazia, organize seus livros!.");
        }
        livroDTO.setTitulo(livroDTO.getTitulo());
        livroDTO.setAutor(livroDTO.getAutor());
        livroDTO.setTotalPaginas(livroDTO.getTotalPaginas());
        livroDTO.setCategoria(livroDTO.getCategoria());
        livroDTO.setAnoPublicacao(livroDTO.getAnoPublicacao());
        livroDTO.setObservacoes(livroDTO.getObservacoes());

        LivroModel livroSalvo = repository.save(livroExistente);
        return mapper.toDto(livroSalvo);
    }


    public void deletarLivro(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontrado("Livro não encontrado para exclusão.");
        }

        repository.deleteById(id);
    }
}


