package br.com.Shelfie.controller;

import br.com.Shelfie.dto.ShelfieDTO;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import br.com.Shelfie.services.ShelfieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shelfie")
public class ShelfieController {
    private ShelfieService service;

    public ShelfieController(ShelfieService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ShelfieDTO> cadastrarLivro(@RequestBody ShelfieDTO shelfieDTO) {
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
    public ResponseEntity<ShelfieDTO> listarLivroPorAutor(@RequestParam String autor) {
        ShelfieDTO listarLivroPorAutor = service.listarPorAutor(autor);
        return ResponseEntity.ok(listarLivroPorAutor);
    }

    @GetMapping("/genero")
    public ResponseEntity<ShelfieDTO> listarLivroPorGenero(@RequestParam String genero) {
        ShelfieDTO listarLivroPorGenero = service.listarPorGenero(genero);
        return ResponseEntity.ok(listarLivroPorGenero);
    }

    @GetMapping("/status")
    public ResponseEntity<ShelfieDTO> listarLivroPorStatus(@RequestParam StatusDeLeitura statusDeLeitura) {
        ShelfieDTO listarLivroPorStatus = service.listarPorStatusDeLeitura(statusDeLeitura);
        return ResponseEntity.ok(listarLivroPorStatus);
    }

    @GetMapping("/avaliacao")
    public ResponseEntity<ShelfieDTO> listarLivroPorAvaliacao(@RequestParam Avaliacao avaliacao) {
        ShelfieDTO listarLivroPorAvaliacao = service.listarPorAvaliacao(avaliacao);
        return ResponseEntity.ok(listarLivroPorAvaliacao);
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
