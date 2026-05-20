package trabalhoindividual.service;

import java.util.List;

import org.springframework.stereotype.Service;

import trabalhoindividual.domain.Animal;
import trabalhoindividual.repository.AnimalRepository;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAllAnimais() {
        return animalRepository.findAll();
    }

    public Animal getAnimalById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("Animal não encontrado"));
    }

    public Animal createAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    public Animal updateAnimal(Long id, Animal animal) {
        Animal existingAnimal = getAnimalById(id);
        existingAnimal.setNome(animal.getNome());
        existingAnimal.setEspecie(animal.getEspecie());
        existingAnimal.setIdade(animal.getIdade());
        return animalRepository.save(existingAnimal);
    }

    public void deleteAnimal(Long id) {
        getAnimalById(id);
        animalRepository.deleteById(id);
    }
    
}
