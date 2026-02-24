package dev.GSL.Shelfie.controller;

import dev.GSL.Shelfie.dto.LeituraDTO;
import dev.GSL.Shelfie.service.LeituraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leituras")
public class LeituraController {

    private final LeituraService service;


    public LeituraController(LeituraService service) {
        this.service = service;
    }

    @GetMapping("/leituras/{id}")
    public ResponseEntity<LeituraDTO> listarLeituraPorId(@PathVariable Long id) {
        LeituraDTO leituraDTO = service.listarLeituraporId(id);
        return ResponseEntity.ok(leituraDTO);
    }


    @GetMapping("/leituras")
    public ResponseEntity<List<LeituraDTO>> listarLeituras() {
        List<LeituraDTO> livros = service.listarLeituras();
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/criarLeitura")
    public ResponseEntity<LeituraDTO> criarLeitura(@Valid @RequestBody LeituraDTO leituraDTO) {
        LeituraDTO leitura = service.criarLeitura(leituraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(leitura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeituraDTO> atualizarLeitura(@PathVariable Long id, @Valid @RequestBody LeituraDTO leituraDTO) {
        LeituraDTO leituraAtualizada = service.atualizarLeitura(id, leituraDTO);
        return ResponseEntity.ok(leituraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLeitura(@PathVariable Long id) {
        service.deletarLeitura(id);
        return ResponseEntity.noContent().build();
    }

}
