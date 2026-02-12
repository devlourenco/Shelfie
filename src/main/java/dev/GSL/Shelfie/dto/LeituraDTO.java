package dev.GSL.Shelfie.dto;

import dev.GSL.Shelfie.enums.StatusLeitura;
import dev.GSL.Shelfie.model.LivroModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraDTO {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusLeitura status;
    private int paginaAtual;
    private int paginasTotais;
    private int avaliacao;
    private String comentarioPessoal;
    private LivroModel livro;
}
