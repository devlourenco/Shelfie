package dev.GSL.Shelfie.repository;

import dev.GSL.Shelfie.model.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroModel, Long> {

}
