package dev.GSL.Shelfie.Livro;

import dev.GSL.Shelfie.Leitura.LeituraModel;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_livro")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String titulo;
    String autor;
    int totalPaginas;
    String categoria;
    int anoPublicacao;
    String observacoes;

    @OneToMany(mappedBy = "livros")
    private LeituraModel leitura;

}
