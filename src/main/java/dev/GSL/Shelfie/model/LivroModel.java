package dev.GSL.Shelfie.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "livro")
    private List<LeituraModel> leitura;

}
