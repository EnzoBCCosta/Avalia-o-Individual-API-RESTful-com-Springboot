package trabalhoindividual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trabalhoindividual.domain.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}
