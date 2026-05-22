package trabalhoindividual.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import trabalhoindividual.domain.Pessoa;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

//verificar se existe cpf ou email igual para outra pessoa

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);

}