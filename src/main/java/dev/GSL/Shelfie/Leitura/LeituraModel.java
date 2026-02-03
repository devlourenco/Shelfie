package dev.GSL.Shelfie.Leitura;


import dev.GSL.Shelfie.Livro.LivroModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_leitura")
public class LeituraModel {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusLeitura status;
    private int paginaAtual;
    private int avaliacao;
    private String comentarioPessoal;

    @ManyToOne
    @JoinColumn(name = "livros_id")
    private LivroModel livro;
}
