package br.com.Shelfie.services;

import br.com.Shelfie.dto.ShelfieDTO;
import br.com.Shelfie.entity.ShelfieModel;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import br.com.Shelfie.exception.LivroDuplicadoException;
import br.com.Shelfie.exception.LivroNaoEncontradoException;
import br.com.Shelfie.mapper.ShelfieMapper;
import br.com.Shelfie.repository.ShelfieRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShelfieService {
    private final ShelfieRepository repository;
    private final ShelfieMapper mapper;

    public ShelfieService(ShelfieRepository repository, ShelfieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    //Criar Livro
    public ShelfieDTO cadastrarLivro(ShelfieDTO shelfieDTO) {
        boolean livroJaExiste =
                repository.existsByTituloIgnoreCaseAndAutorIgnoreCase(
                        shelfieDTO.getTitulo(),
                        shelfieDTO.getAutor()
                );
        if (livroJaExiste) {
            throw new LivroDuplicadoException();
        }
        ShelfieModel novoLivro = mapper.toModel(shelfieDTO);
        ShelfieModel livroSalvo = repository.save(novoLivro);
        return mapper.toDto(livroSalvo);
    }
    //Listar todos os livros

    public List<ShelfieDTO> listarLivros() {
        List<ShelfieModel> listarLivros = repository.findAll();
        return listarLivros.stream()
                .map(mapper::toDto)
                .toList();
    }

    //ListarPorId
    public ShelfieDTO listarPorId(Long id) {
        ShelfieModel listarPorId = repository.findById(id).orElseThrow(() ->
                new LivroNaoEncontradoException(
                        "Livro com o id '" + id + "' não encontrado."
                )
        );

        return mapper.toDto(listarPorId);
    }

    //ListarPorTitulo
    public ShelfieDTO listarPorTitulo(String titulo) {
        ShelfieModel listarPorTitulo = repository.findByTitulo(titulo).orElseThrow(() ->
                new LivroNaoEncontradoException(
                        "Livro com o título '" + titulo + "' não encontrado."
                )
        );
        return mapper.toDto(listarPorTitulo);
    }

    //ListarPorAutor
    public List<ShelfieDTO> listarPorAutor(String autor) {
        List<ShelfieModel> livros = repository.findByAutor(autor);

        return livros.stream()
                .map(mapper::toDto)
                .toList();
    }

    //ListarPorGenero
    public List<ShelfieDTO> listarPorGenero(String genero) {
        List<ShelfieModel> livros = repository.findByGenero(genero);

        return livros.stream()
                .map(mapper::toDto)
                .toList();
    }

    //ListarPorStatus
    public List<ShelfieDTO> listarPorStatusDeLeitura(StatusDeLeitura statusDeLeitura) {
        List<ShelfieModel> livros = repository.findByStatusDeLeitura(statusDeLeitura);

        return livros.stream()
                .map(mapper::toDto)
                .toList();
    }

    //ListarPorAvalicao
    public List<ShelfieDTO> listarPorAvaliacao(Avaliacao avaliacao) {
        List<ShelfieModel> livros = repository.findByAvaliacao(avaliacao);
        return livros.stream()
                .map(mapper::toDto)
                .toList();
    }


    //AtualizarPorID, também, não perdendo os valores já preenchidos no banco de dados.
    public ShelfieDTO atualizarPorId(Long id, ShelfieDTO livroNovo) {
        ShelfieModel model = repository.findById(id).orElseThrow(LivroNaoEncontradoException::new);
        ShelfieModel livroAtualizado = ShelfieModel.builder()
                .id(model.getId())
                .titulo(livroNovo.getTitulo() != null ? livroNovo.getTitulo() : model.getTitulo())
                .autor(livroNovo.getAutor() != null ? livroNovo.getAutor() : model.getAutor())
                .genero(livroNovo.getGenero() != null ? livroNovo.getGenero() : model.getGenero())
                .numPaginas(livroNovo.getNumPaginas() != null ? livroNovo.getNumPaginas() : model.getNumPaginas())
                .statusDeLeitura(livroNovo.getStatusDeLeitura() != null ? livroNovo.getStatusDeLeitura() : model.getStatusDeLeitura())
                .avaliacao(livroNovo.getAvaliacao() != null ? livroNovo.getAvaliacao() : model.getAvaliacao())
                .build();
        ShelfieModel livroSalvo = repository.save(livroAtualizado);
        return mapper.toDto(livroSalvo);
    }


    //Deletar livro
    public void deletarLivro(Long id) {
        ShelfieModel livro = repository.findById(id).orElseThrow(LivroNaoEncontradoException::new);
        repository.delete(livro);
    }

}
