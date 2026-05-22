package trabalhoindividual.service;

import java.util.List;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import trabalhoindividual.domain.Animal;
import trabalhoindividual.exception.ResourceNotFoundException;
import trabalhoindividual.repository.AnimalRepository;
import trabalhoindividual.repository.InteresseAdocaoRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final InteresseAdocaoRepository interesseAdocaoRepository;

    public AnimalService(AnimalRepository animalRepository,
            InteresseAdocaoRepository interesseAdocaoRepository) {
        this.animalRepository = animalRepository;
        this.interesseAdocaoRepository = interesseAdocaoRepository;
    }

    public List<Animal> getAllAnimais() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long id, Animal animal) {
        Animal existingAnimal = getAnimalById(id);
        existingAnimal.setNome(animal.getNome());
        existingAnimal.setRaca(animal.getRaca());
        existingAnimal.setSexo(animal.getSexo());
        existingAnimal.setPorte(animal.getPorte());
        existingAnimal.setStatus(animal.getStatus());
        existingAnimal.setEspecie(animal.getEspecie());
        existingAnimal.setIdade(animal.getIdade());
        return animalRepository.save(existingAnimal);
    }

    // verificar se o animal existe, deletar os interesses de adoção relacionados e depois deletar o animal 
    @Transactional
    public void deleteAnimal(Long id) {
        getAnimalById(id);
        interesseAdocaoRepository.deleteByAnimalId(id);
        animalRepository.deleteById(id);
    }

}