package dev.GSL.Shelfie.service;

import dev.GSL.Shelfie.dto.LivroDTO;
import dev.GSL.Shelfie.mapper.LivroMapper;
import dev.GSL.Shelfie.model.LivroModel;
import dev.GSL.Shelfie.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
            throw new RuntimeException("Titulo do livro é obrigatório.");
        }
        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty()) {
            throw new RuntimeException("Autor do livro é obrigatório.");
        }
        if (livro.getTotalPaginas() <= 0) {
            throw new RuntimeException("O número de páginas deve ser maior que zero.");
        }
        if (livro.getAnoPublicacao() <= 0 || livro.getAnoPublicacao() > LocalDateTime.now().getYear()) {
            throw new RuntimeException("Ano inválido, deve ser maior que zero e menor ou igual ano atual.");
        }
        if (livro.getCategoria() == null) {
            throw new RuntimeException("A categoria não pode estar vazia, organize seus livros!.");
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

    public void buscarPorId() {
    }

    public void atualizarLivro() {
    }

    public void deletarLivro(Long id) {
        repository.deleteById(id);
    }
}


