package br.com.Shelfie.repository;
import java.util.List;

import br.com.Shelfie.entity.ShelfieModel;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelfieRepository extends JpaRepository<ShelfieModel, Long> {

    Optional<ShelfieModel> findByTitulo(String titulo);

    List<ShelfieModel> findByAutor(String autor);

    List<ShelfieModel> findByGenero(String genero);

    List<ShelfieModel> findByStatusDeLeitura(StatusDeLeitura statusDeLeitura);

    List<ShelfieModel> findByAvaliacao(Avaliacao avaliacao);

    boolean existsByTituloIgnoreCaseAndAutorIgnoreCase(
            String titulo,
            String autor
    );
}
