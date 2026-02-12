package dev.GSL.Shelfie.Leitura;

import ch.qos.logback.core.model.NamedModel;
import dev.GSL.Shelfie.Livro.LivroModel;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_leitura")
public class LeituraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate dataInicio;
    LocalDate dataFim;
    Status status;
    int paginaAtual;
    int avaliacao;
    String comentarioPessoal;

    @ManyToOne()
    @JoinColumn(name = "livros_id")
    private List<LivroModel> livro;
}
