package trabalhoindividual.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import trabalhoindividual.dto.request.InteresseAdocaoRequest;
import trabalhoindividual.dto.response.InteresseAdocaoResponse;
import trabalhoindividual.service.InteresseAdocaoService;

@RestController
@RequestMapping("/interesses-adocao")
@Tag(name = "Interesses de Adoção", description = "Gerenciamento de interesses de adoção de animais")
public class InteresseAdocaoController {

    private final InteresseAdocaoService interesseAdocaoService;

    public InteresseAdocaoController(InteresseAdocaoService interesseAdocaoService) {
        this.interesseAdocaoService = interesseAdocaoService;
    }

    @Operation(summary = "Listar todos os interesses de adoção")
    @GetMapping
    public ResponseEntity<List<InteresseAdocaoResponse>> listarTodos() {
        return ResponseEntity.ok(interesseAdocaoService.listarTodos());
    }

    @Operation(summary = "Buscar interesse de adoção por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Interesse encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Interesse não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InteresseAdocaoResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(interesseAdocaoService.buscarPorId(id));
    }

    @Operation(summary = "Listar interesses por pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<InteresseAdocaoResponse>> buscarPorPessoa(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(interesseAdocaoService.buscarPorPessoa(pessoaId));
    }

    @Operation(summary = "Listar interesses por animal")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Animal não encontrado")
    })
    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<InteresseAdocaoResponse>> buscarPorAnimal(@PathVariable Long animalId) {
        return ResponseEntity.ok(interesseAdocaoService.buscarPorAnimal(animalId));
    }

    @Operation(summary = "Registrar interesse de adoção", description = "Vincula uma pessoa a um animal como interesse de adoção")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Interesse registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Pessoa ou animal não encontrado")
    })
    @PostMapping("/pessoa/{pessoaId}/animal/{animalId}")
    public ResponseEntity<InteresseAdocaoResponse> criar(@PathVariable Long pessoaId,
            @PathVariable Long animalId,
            @Valid @RequestBody InteresseAdocaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(interesseAdocaoService.criar(pessoaId, animalId, request));
    }

    @Operation(summary = "Atualizar interesse de adoção")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Interesse atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Interesse não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<InteresseAdocaoResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody InteresseAdocaoRequest request) {
        return ResponseEntity.ok(interesseAdocaoService.atualizar(id, request));
    }

    @Operation(summary = "Deletar interesse de adoção")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Interesse deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Interesse não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        interesseAdocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
