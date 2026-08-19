package br.com.Shelfie.controller;

import br.com.Shelfie.dto.ShelfieDTO;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import br.com.Shelfie.services.ShelfieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/shelfie")
public class ShelfieController {
    private ShelfieService service;

    public ShelfieController(ShelfieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShelfieDTO> cadastrarLivro(@Valid @RequestBody ShelfieDTO shelfieDTO) {
        ShelfieDTO livroCriado = service.cadastrarLivro(shelfieDTO);
        return ResponseEntity
                .status(201)
                .body(livroCriado);
    }

    @GetMapping
    public ResponseEntity<List<ShelfieDTO>> listarLivros() {
        List<ShelfieDTO> listarLivros = service.listarLivros();
        return ResponseEntity.ok(listarLivros);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelfieDTO> listarLivroPorId(@PathVariable Long id) {
        ShelfieDTO listarLivroPorId = service.listarPorId(id);
        return ResponseEntity.ok(listarLivroPorId);
    }

    @GetMapping("/titulo")
    public ResponseEntity<ShelfieDTO> listarLivroPorTitulo(@RequestParam String titulo) {
        ShelfieDTO listarLivroPorTitulo = service.listarPorTitulo(titulo);
        return ResponseEntity.ok(listarLivroPorTitulo);
    }

    @GetMapping("/autor")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorAutor(@RequestParam String autor) {
        List<ShelfieDTO> livros = service.listarPorAutor(autor);

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/genero")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorGenero(@RequestParam String genero) {
        List<ShelfieDTO> livros = service.listarPorGenero(genero);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorStatus(@RequestParam StatusDeLeitura statusDeLeitura) {
        List<ShelfieDTO> livros = service.listarPorStatusDeLeitura(statusDeLeitura);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/avaliacao")
    public ResponseEntity<List<ShelfieDTO>> listarLivroPorAvaliacao(@RequestParam Avaliacao avaliacao) {
        List<ShelfieDTO> livros = service.listarPorAvaliacao(avaliacao);
        return ResponseEntity.ok(livros);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ShelfieDTO> atualizarLivro(@PathVariable Long id, @RequestBody ShelfieDTO livroNovo) {
        ShelfieDTO atualizarLivro = service.atualizarPorId(id, livroNovo);
        return ResponseEntity.ok(atualizarLivro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }


}
