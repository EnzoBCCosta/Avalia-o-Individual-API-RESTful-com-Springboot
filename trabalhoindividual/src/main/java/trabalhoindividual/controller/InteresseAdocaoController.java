package trabalhoindividual.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import trabalhoindividual.dto.request.InteresseAdocaoRequest;
import trabalhoindividual.dto.response.InteresseAdocaoResponse;
import trabalhoindividual.service.InteresseAdocaoService;

@RestController
@RequestMapping("/interesses-adocao")
public class InteresseAdocaoController {

    private final InteresseAdocaoService interesseAdocaoService;

    public InteresseAdocaoController(InteresseAdocaoService interesseAdocaoService) {
        this.interesseAdocaoService = interesseAdocaoService;
    }

    @GetMapping
    public ResponseEntity<List<InteresseAdocaoResponse>> listarTodos() {
        return ResponseEntity.ok(interesseAdocaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteresseAdocaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(interesseAdocaoService.buscarPorId(id));
    }

    @GetMapping("/pessoa/{pessoaId}")
    public List<InteresseAdocaoResponse> buscarPorPessoa(@PathVariable Long pessoaId) {
        return interesseAdocaoService.buscarPorPessoa(pessoaId);
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<InteresseAdocaoResponse>> buscarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(interesseAdocaoService.buscarPorAnimal(animalId));
    }

    @PostMapping("/pessoa/{pessoaId}/animal/{animalId}")
    public ResponseEntity<InteresseAdocaoResponse> criar(
            @PathVariable Long pessoaId,
            @PathVariable Long animalId,
            @Valid @RequestBody InteresseAdocaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(interesseAdocaoService.criar(pessoaId, animalId, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InteresseAdocaoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody InteresseAdocaoRequest request) {
        return ResponseEntity.ok(interesseAdocaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        interesseAdocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}