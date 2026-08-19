package br.com.Shelfie.controller;

import br.com.Shelfie.dto.ShelfieDTO;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import br.com.Shelfie.services.ShelfieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/shelfie")
@Tag(
        name = "Shelfie",
        description = "Endpoints para gerenciamento dos livros da biblioteca."
)
public class ShelfieController {
    private ShelfieService service;

    public ShelfieController(ShelfieService service) {
        this.service = service;
    }

    @Operation(
            summary = "Cadastrar um livro",
            description = "Cadastra um novo livro na biblioteca."
    )
    @PostMapping
    public ResponseEntity<ShelfieDTO> cadastrarLivro(@Valid @RequestBody ShelfieDTO shelfieDTO) {
        ShelfieDTO livroCriado = service.cadastrarLivro(shelfieDTO);
        return ResponseEntity
                .status(201)
                .body(livroCriado);
    }

    @Operation(
            summary = "Listar todos os livros",
            description = "Retorna todos os livros cadastrados na biblioteca."
    )
    @GetMapping
    public ResponseEntity<List<ShelfieDTO>> listarLivros() {
        List<ShelfieDTO> listarLivros = service.listarLivros();
        return ResponseEntity.ok(listarLivros);

    }

    @Operation(
            summary = "Buscar livro por ID",
            description = "Retorna o livro correspondente ao ID informado."
    )

    @GetMapping("/{id}")
    public ResponseEntity<ShelfieDTO> listarLivroPorId(@PathVariable Long id) {
        ShelfieDTO listarLivroPorId = service.listarPorId(id);
        return ResponseEntity.ok(listarLivroPorId);
    }

    @Operation(
            summary = "Buscar livro por título",
            description = "Retorna o livro correspondente ao título informado."
    )
    @GetMapping("/titulo")
    public ResponseEntity<ShelfieDTO> listarLivroPorTitulo(@RequestParam String titulo) {
        ShelfieDTO listarLivroPorTitulo = service.listarPorTitulo(titulo);
        return ResponseEntity.ok(listarLivroPorTitulo);
    }

    @Operation(
            summary = "Buscar livros por autor",
            description = "Retorna os livros cadastrados para o autor informado."
    )
    @GetMapping("/autor")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorAutor(@RequestParam String autor) {
        List<ShelfieDTO> livros = service.listarPorAutor(autor);

        return ResponseEntity.ok(livros);
    }

    @Operation(
            summary = "Buscar livros por gênero",
            description = "Retorna os livros cadastrados para o gênero informado."
    )
    @GetMapping("/genero")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorGenero(@RequestParam String genero) {
        List<ShelfieDTO> livros = service.listarPorGenero(genero);
        return ResponseEntity.ok(livros);
    }

    @Operation(
            summary = "Buscar livros por status de leitura",
            description = "Retorna os livros que possuem o status de leitura informado."
    )
    @GetMapping("/status")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorStatus(@RequestParam StatusDeLeitura statusDeLeitura) {
        List<ShelfieDTO> livros = service.listarPorStatusDeLeitura(statusDeLeitura);
        return ResponseEntity.ok(livros);
    }

    @Operation(
            summary = "Buscar livros por avaliação",
            description = "Retorna os livros que possuem a avaliação informada."
    )
    @GetMapping("/avaliacao")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorAvaliacao(@RequestParam Avaliacao avaliacao) {
        List<ShelfieDTO> livros = service.listarPorAvaliacao(avaliacao);
        return ResponseEntity.ok(livros);
    }

    @Operation(
            summary = "Atualizar parcialmente um livro",
            description = "Atualiza os campos informados de um livro existente."
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ShelfieDTO> atualizarLivro(@PathVariable Long id, @RequestBody ShelfieDTO livroNovo) {
        ShelfieDTO atualizarLivro = service.atualizarPorId(id, livroNovo);
        return ResponseEntity.ok(atualizarLivro);
    }

    @Operation(
            summary = "Excluir um livro",
            description = "Remove da biblioteca o livro correspondente ao ID informado."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }


}
