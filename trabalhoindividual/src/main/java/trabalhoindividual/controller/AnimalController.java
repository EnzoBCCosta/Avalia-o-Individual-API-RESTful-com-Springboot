package trabalhoindividual.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trabalhoindividual.domain.Animal;
import trabalhoindividual.dto.request.AnimalRequest;
import trabalhoindividual.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    private AnimalRequest toRequest(Animal animal) {
        AnimalRequest request = new AnimalRequest();
        request.setNome(animal.getNome());
        request.setEspecie(animal.getEspecie());
        request.setIdade(animal.getIdade());
        request.setRaca(animal.getRaca());
        request.setSexo(animal.getSexo());
        request.setPorte(animal.getPorte());
        request.setStatus(animal.getStatus());
        return request;
    }

    @GetMapping
    public ResponseEntity<List<AnimalRequest>> getAllAnimais() {
        List<Animal> animais = animalService.getAllAnimais();
        List<AnimalRequest> requests = animais.stream()
                .map(this::toRequest)
                .collect(Collectors.toList());
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalRequest> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        return new ResponseEntity<>(toRequest(animal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalRequest> createAnimal(@Valid @RequestBody Animal animal) {
        Animal createdAnimal = animalService.createAnimal(animal);
        return new ResponseEntity<>(toRequest(createdAnimal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalRequest> updateAnimal(@PathVariable Long id, @Valid @RequestBody Animal animal) {
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        return new ResponseEntity<>(toRequest(updatedAnimal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}