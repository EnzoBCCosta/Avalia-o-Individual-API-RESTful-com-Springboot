package trabalhoindividual.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import trabalhoindividual.domain.Animal;
import trabalhoindividual.dto.request.AnimalRequest;
import trabalhoindividual.dto.response.AnimalResponse;
import trabalhoindividual.service.AnimalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/animais")
@Tag(name = "Animais", description = "Gerenciamento de animais disponíveis para adoção")
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

    private AnimalResponse toAnimalResponse(Animal animal) {
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

    @Operation(summary = "Listar todos os animais", description = "Retorna uma lista com todos os animais cadastrados")
    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAllAnimais() {
        List<Animal> animais = animalService.getAllAnimais();
        List<AnimalResponse> responses = animais.stream()
                .map(this::toAnimalResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Buscar animal por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> getAnimalById(@PathVariable Long id) {
        Animal animal = animalService.getAnimalById(id);
        return new ResponseEntity<>(toAnimalResponse(animal), HttpStatus.OK);
    }

    @Operation(summary = "Cadastrar novo animal", description = "Cria um novo animal no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Animal cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<AnimalResponse> createAnimal(@Valid @RequestBody AnimalRequest request) {
        Animal animal = toAnimal(request);
        Animal createdAnimal = animalService.createAnimal(animal);
        return new ResponseEntity<>(toAnimalResponse(createdAnimal), HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar animal")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Animal atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponse> updateAnimal(@PathVariable Long id,
            @Valid @RequestBody AnimalRequest request) {
        Animal animal = toAnimal(request);
        Animal updatedAnimal = animalService.updateAnimal(id, animal);
        return new ResponseEntity<>(toAnimalResponse(updatedAnimal), HttpStatus.OK);
    }

    @Operation(summary = "Deletar animal")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Animal deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        animalService.deleteAnimal(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
