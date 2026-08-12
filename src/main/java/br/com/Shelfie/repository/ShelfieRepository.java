package br.com.Shelfie.repository;

import br.com.Shelfie.entity.ShelfieModel;
import br.com.Shelfie.enums.Avaliacao;
import br.com.Shelfie.enums.StatusDeLeitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShelfieRepository extends JpaRepository<ShelfieModel, Long> {

    Optional<ShelfieModel> findByTitulo(String titulo);

    Optional<ShelfieModel> findByAutor(String autor);

    Optional<ShelfieModel> findByGenero(String genero);

    Optional<ShelfieModel> findByStatusDeLeitura(StatusDeLeitura statusDeLeitura);

    Optional<ShelfieModel> findByAvaliacao(Avaliacao avaliacao);

    boolean existsByTituloIgnoreCaseAndAutorIgnoreCase(
            String titulo,
            String autor
    );
}
