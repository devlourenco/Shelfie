package dev.GSL.Shelfie.model;

import dev.GSL.Shelfie.enums.StatusLeitura;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_leitura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeituraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusLeitura status;
    private int paginaAtual;
    private int paginasTotais;
    private int avaliacao;
    private String comentarioPessoal;

    @ManyToOne()
    @JoinColumn(name = "livro_id")
    private LivroModel livro;
}
