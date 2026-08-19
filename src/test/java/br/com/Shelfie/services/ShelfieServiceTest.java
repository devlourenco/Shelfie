package br.com.Shelfie.services;

import br.com.Shelfie.dto.ShelfieDTO;
import br.com.Shelfie.entity.ShelfieModel;
import br.com.Shelfie.exception.LivroDuplicadoException;
import br.com.Shelfie.exception.LivroNaoEncontradoException;
import br.com.Shelfie.mapper.ShelfieMapper;
import br.com.Shelfie.repository.ShelfieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShelfieServiceTest {

    @Mock
    private ShelfieRepository repository;

    @Mock
    private ShelfieMapper mapper;

    private ShelfieService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        service = new ShelfieService(
                repository,
                mapper
        );
    }

    @Test
    void deveCadastrarLivroQuandoNaoExistirDuplicidade() {

        ShelfieDTO livro = new ShelfieDTO();
        livro.setTitulo("O Hobbit");
        livro.setAutor("J. R. R. Tolkien");

        ShelfieModel model = mock(ShelfieModel.class);
        ShelfieModel modelSalvo = mock(ShelfieModel.class);

        ShelfieDTO resultadoEsperado = new ShelfieDTO();
        resultadoEsperado.setTitulo("O Hobbit");
        resultadoEsperado.setAutor("J. R. R. Tolkien");

        when(repository.existsByTituloIgnoreCaseAndAutorIgnoreCase(
                "O Hobbit",
                "J. R. R. Tolkien"
        )).thenReturn(false);

        when(mapper.toModel(livro))
                .thenReturn(model);

        when(repository.save(model))
                .thenReturn(modelSalvo);

        when(mapper.toDto(modelSalvo))
                .thenReturn(resultadoEsperado);

        ShelfieDTO resultado =
                service.cadastrarLivro(livro);

        assertSame(resultadoEsperado, resultado);

        verify(repository).save(model);
    }

    @Test
    void deveLancarExceptionQuandoLivroJaExistir() {

        ShelfieDTO livro = new ShelfieDTO();
        livro.setTitulo("O Hobbit");
        livro.setAutor("J. R. R. Tolkien");

        when(repository.existsByTituloIgnoreCaseAndAutorIgnoreCase(
                "O Hobbit",
                "J. R. R. Tolkien"
        )).thenReturn(true);

        assertThrows(
                LivroDuplicadoException.class,
                () -> service.cadastrarLivro(livro)
        );

        verify(repository, never())
                .save(any(ShelfieModel.class));
    }

    @Test
    void deveRetornarLivroQuandoIdExistir() {

        ShelfieModel model = mock(ShelfieModel.class);

        ShelfieDTO dto = new ShelfieDTO();
        dto.setTitulo("Duna");

        when(repository.findById(1L))
                .thenReturn(Optional.of(model));

        when(mapper.toDto(model))
                .thenReturn(dto);

        ShelfieDTO resultado =
                service.listarPorId(1L);

        assertSame(dto, resultado);
    }

    @Test
    void deveLancarExceptionQuandoIdNaoExistir() {

        when(repository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                LivroNaoEncontradoException.class,
                () -> service.listarPorId(999L)
        );
    }

    @Test
    void deveRetornarVariosLivrosDoMesmoAutor() {

        ShelfieModel model1 = mock(ShelfieModel.class);
        ShelfieModel model2 = mock(ShelfieModel.class);

        ShelfieDTO dto1 = new ShelfieDTO();
        dto1.setTitulo("O Hobbit");

        ShelfieDTO dto2 = new ShelfieDTO();
        dto2.setTitulo("O Silmarillion");

        when(repository.findByAutor("J. R. R. Tolkien"))
                .thenReturn(List.of(model1, model2));

        when(mapper.toDto(model1))
                .thenReturn(dto1);

        when(mapper.toDto(model2))
                .thenReturn(dto2);

        List<ShelfieDTO> resultado =
                service.listarPorAutor("J. R. R. Tolkien");

        assertEquals(2, resultado.size());

        assertSame(dto1, resultado.get(0));
        assertSame(dto2, resultado.get(1));
    }

    @Test
    void deveRetornarListaVaziaQuandoFiltroNaoEncontrarLivros() {

        when(repository.findByGenero("Terror"))
                .thenReturn(List.of());

        List<ShelfieDTO> resultado =
                service.listarPorGenero("Terror");

        assertTrue(resultado.isEmpty());
    }
}