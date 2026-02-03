package dev.GSL.Shelfie.Livro;


import dev.GSL.Shelfie.Leitura.LeituraModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_livros")
public class LivroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private int totalPaginas;
    private String categoria;
    private int anoPublicacao;
    private String observacoes;

    @OneToMany(mappedBy = "livro")
    private List<LeituraModel> leituras;




}
