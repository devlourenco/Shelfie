package dev.GSL.Shelfie.dto;

import dev.GSL.Shelfie.model.LeituraModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    Long id;
    String titulo;
    String autor;
    int totalPaginas;
    String categoria;
    int anoPublicacao;
    String observacoes;
    private List<LeituraModel> leitura;
}
