package dev.GSL.Shelfie.Livro;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroModel criarLivro(LivroModel livro) {
        return repository.save(livro);
    }

    public List<LivroModel> listarLivros() {
        return repository.findAll();
    }

    public void buscarPorId() {
    }

    public void atualizarLivro() {
    }

    public void deletarLivro(Long id) {
        repository.deleteById(id);
    }
}


