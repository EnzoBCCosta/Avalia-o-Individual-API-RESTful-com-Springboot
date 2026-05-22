package trabalhoindividual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trabalhoindividual.domain.InteresseAdocao;

@Repository
public interface InteresseAdocaoRepository extends JpaRepository<InteresseAdocao, Long> {

    List<InteresseAdocao> findByPessoaId(Long pessoaId);
    List<InteresseAdocao> findByAnimalId(Long animalId);
}