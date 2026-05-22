package trabalhoindividual.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import trabalhoindividual.service.PessoaService;
import trabalhoindividual.dto.request.EnderecoRequest;
import trabalhoindividual.dto.request.PessoaRequest;
import trabalhoindividual.dto.response.PessoaResponse;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas", description = "Gerenciamento de pessoas interessadas em adoção")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(summary = "Listar todas as pessoas")
    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarTodos() {
        return ResponseEntity.ok(pessoaService.getAllPessoas());
    }

    @Operation(summary = "Buscar pessoa por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.getPessoaById(id));
    }

    @Operation(summary = "Cadastrar nova pessoa", description = "Cria uma nova pessoa no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou CPF/e-mail já cadastrado")
    })
    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@Valid @RequestBody PessoaRequest pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.createPessoa(pessoa));
    }

    @Operation(summary = "Atualizar pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pessoa atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id,
            @Valid @RequestBody PessoaRequest pessoa) {
        return ResponseEntity.ok(pessoaService.updatePessoa(id, pessoa));
    }

    @Operation(summary = "Deletar pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Adicionar endereço à pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Endereço adicionado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PostMapping("/{id}/enderecos")
    public ResponseEntity<PessoaResponse> adicionarEndereco(@PathVariable Long id,
            @Valid @RequestBody EnderecoRequest endereco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.adicionarEndereco(id, endereco));
    }

    @Operation(summary = "Atualizar endereço da pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @PutMapping("/{id}/enderecos")
    public ResponseEntity<PessoaResponse> atualizarEndereco(@PathVariable Long id,
            @Valid @RequestBody EnderecoRequest endereco) {
        return ResponseEntity.ok(pessoaService.atualizarEndereco(id, endereco));
    }

    @Operation(summary = "Remover endereço da pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Endereço removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
    })
    @DeleteMapping("/{id}/enderecos")
    public ResponseEntity<Void> removerEndereco(@PathVariable Long id) {
        pessoaService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
