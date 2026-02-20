package dev.GSL.Shelfie.controller;


import dev.GSL.Shelfie.dto.LivroDTO;
import dev.GSL.Shelfie.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<LivroDTO> listarLivro(@PathVariable Long id) {
        LivroDTO livro = service.buscarPorId(id);

        return ResponseEntity.ok(livro);
    }

    @GetMapping("/livros")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        List<LivroDTO> livro = service.listarLivros();
        return ResponseEntity.ok(livro);
    }

    @PostMapping("/livros")
    public ResponseEntity<LivroDTO> criarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        LivroDTO livroNovo = service.criarLivro(livroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO> atualizarLivro(@PathVariable Long id, @Valid @RequestBody LivroDTO livroDTO) {
        LivroDTO livroAtualizado = service.atualizarLivro(livroDTO, id);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        service.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}

