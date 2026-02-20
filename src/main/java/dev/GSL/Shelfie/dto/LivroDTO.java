package dev.GSL.Shelfie.dto;

import dev.GSL.Shelfie.model.LeituraModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Size(max = 150, message = "Título pode ter no máximo 150 caracteres")
    private String titulo;

    @NotBlank(message = "Autor é obrigatório")
    @Size(max = 100, message = "Autor pode ter no máximo 100 caracteres")
    private String autor;

    @Positive(message = "Total de páginas deve ser maior que zero")
    private Integer totalPaginas;

    @Positive(message = "Ano de publicação deve ser maior que zero")
    private Integer anoPublicacao;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    @Size(max = 500, message = "Observações podem ter no máximo 500 caracteres")
    private String observacoes;

    private List<LeituraModel> leitura;
}
