package dev.GSL.Shelfie.dto;

import dev.GSL.Shelfie.enums.StatusLeitura;
import dev.GSL.Shelfie.model.LivroModel;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraDTO {

    @NotNull(message = "Livro é obrigatório")
    private Long id;

    @NotNull(message = "A data de ínicio da leitura é obrigatória")
    private LocalDate dataInicio;


    private LocalDate dataFim;

    @NotNull(message = "O status da leitura é obrigatória")
    private StatusLeitura status;

    @PositiveOrZero(message = "Página atual deve ser maior ou igual a 0")
    private int paginaAtual;

    @NotNull(message = "As páginas totais deve ser maior que 0")
    private int paginasTotais;

    @Min(value = 0, message = "Avaliação mínima é 0")
    @Max(value = 5, message = "Avaliação máxima é 5")
    private int avaliacao;

    private String comentarioPessoal;

    private LivroModel livro;

}
