package trabalhoindividual.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trabalhoindividual.domain.InteresseAdocao;

@Repository
public interface InteresseAdocaoRepository extends JpaRepository<InteresseAdocao, Long> {

    //verificar se existe interesse de adoção para um animal ou pessoa específica
    List<InteresseAdocao> findByPessoaId(Long pessoaId);
    List<InteresseAdocao> findByAnimalId(Long animalId);

    //deletar interesses de adoção relacionados a um animal específico
    void deleteByAnimalId(Long animalId);
}