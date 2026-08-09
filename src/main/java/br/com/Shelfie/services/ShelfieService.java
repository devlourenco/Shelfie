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
import java.util.Optional;

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
        Optional<ShelfieModel> livroEncontrado = repository.findByTitulo(shelfieDTO.getTitulo());
        if (livroEncontrado.isPresent()) {
            throw new LivroDuplicadoException();
        }
        ShelfieModel novoLivro = mapper.toModel(shelfieDTO);
        ShelfieModel resposta = repository.save(novoLivro);
        return mapper.toDto(resposta);
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
    public ShelfieDTO listarPorAutor(String autor) {
        ShelfieModel listarPorAutor = repository.findByAutor(autor).orElseThrow(() -> new LivroNaoEncontradoException(
                "Livro com o autor '" + autor + "' não encontrado."
        ));
        return mapper.toDto(listarPorAutor);
    }

    //ListarPorGenero
    public ShelfieDTO listarPorGenero(String genero) {
        ShelfieModel listarPorGenero = repository.findByGenero(genero).orElseThrow(() -> new LivroNaoEncontradoException(
                "Livro com o gênero '" + genero + "' não encontrado."
        ));
        return mapper.toDto(listarPorGenero);
    }

    //ListarPorStatus
    public ShelfieDTO listarPorStatusDeLeitura(StatusDeLeitura statusDeLeitura) {
        ShelfieModel listarPorStatusDeLeitura = repository.findByStatusDeLeitura(statusDeLeitura).orElseThrow(() -> new LivroNaoEncontradoException(
                "Livro com o status '" + statusDeLeitura + "' não encontrado."
        ));
        return mapper.toDto(listarPorStatusDeLeitura);
    }

    //ListarPorAvalicao
    public ShelfieDTO listarPorAvaliacao(Avaliacao avaliacao) {
        ShelfieModel listarPorAvaliacao = repository.findByAvaliacao(avaliacao).orElseThrow(() -> new LivroNaoEncontradoException(
                "Livro com a avaliação '" + avaliacao + "' não encontrado."
        ));
        return mapper.toDto(listarPorAvaliacao);
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
