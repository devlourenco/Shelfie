package dev.GSL.Shelfie.service;

import dev.GSL.Shelfie.dto.LivroDTO;
import dev.GSL.Shelfie.exception.RecursoNaoEncontrado;
import dev.GSL.Shelfie.exception.RegraDeNegocio;
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
        if (repository.existsByTituloIgnoreCase(livroDTO.getTitulo())) {
            throw new RegraDeNegocio("Já existe um livro com esse título.");
        }
        LivroModel livro = mapper.toModel(livroDTO);
        return mapper.toDto((repository.save(livro)));
    }

    public List<LivroDTO> listarLivros() {
        List<LivroModel> livro = repository.findAll();
        return livro.stream()
                .map(mapper::toDto)
                .toList();
    }

    public LivroDTO buscarPorId(Long id) {
        Optional<LivroModel> livroPorId = repository.findById(id);
        return livroPorId.map(mapper::toDto).orElseThrow(() -> new RecursoNaoEncontrado("Livro não encontrado"));
    }

    public LivroDTO atualizarLivro(LivroDTO livroDTO, Long id) {
        LivroModel livroExistente = repository.findById(id)
                .orElseThrow(() ->
                        new RecursoNaoEncontrado("Livro não encontrado."));

        if (!livroExistente.getTitulo().equalsIgnoreCase(livroDTO.getTitulo()) &&
                repository.existsByTituloIgnoreCase(livroDTO.getTitulo())) {

            throw new RegraDeNegocio("Já existe um livro com esse título.");
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


