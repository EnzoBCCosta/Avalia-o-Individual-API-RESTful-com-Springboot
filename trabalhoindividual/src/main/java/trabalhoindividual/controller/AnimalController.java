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
import trabalhoindividual.dto.response.AnimalResponse;
import trabalhoindividual.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    private Animal toAnimal(AnimalRequest request) {
        Animal animal = new Animal();
        animal.setNome(request.getNome());
        animal.setEspecie(request.getEspecie());
        animal.setIdade(request.getIdade());
        animal.setRaca(request.getRaca());
        animal.setSexo(request.getSexo());
        animal.setPorte(request.getPorte());
        animal.setStatus(request.getStatus());
        return animal;
    }

    private AnimalResponse AnimalResponse(Animal animal) {
        AnimalResponse response = new AnimalResponse();
        response.setId(animal.getId());
        response.setNome(animal.getNome());
        response.setEspecie(animal.getEspecie());
        response.setIdade(animal.getIdade());
        response.setRaca(animal.getRaca());
        response.setSexo(animal.getSexo());
        response.setPorte(animal.getPorte());
        response.setStatus(animal.getStatus());
        return response;
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAllAnimais() {
        List<Animal> animais = animalService.getAllAnimais();
        List<AnimalResponse> responses = animais.stream()
                .map(this::AnimalResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        return new ResponseEntity<>(AnimalResponse(animal), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@Valid @RequestBody AnimalRequest request) {
        Animal animal = toAnimal(request);
        Animal createdAnimal = animalService.createAnimal(animal);
        return new ResponseEntity<>(AnimalResponse(createdAnimal), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id, @Valid @RequestBody AnimalRequest request) {
        Animal animal = toAnimal(request);
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        return new ResponseEntity<>(AnimalResponse(updatedAnimal), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}