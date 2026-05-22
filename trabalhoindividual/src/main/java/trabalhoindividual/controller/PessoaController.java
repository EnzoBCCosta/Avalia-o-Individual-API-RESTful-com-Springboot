package trabalhoindividual.controller;

import java.util.List;

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
import jakarta.validation.Valid;

import trabalhoindividual.service.PessoaService;
import trabalhoindividual.dto.request.EnderecoRequest;
import trabalhoindividual.dto.request.PessoaRequest;
import trabalhoindividual.dto.response.PessoaResponse;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listarTodos() {
        return ResponseEntity.ok(pessoaService.getAllPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.getPessoaById(id));
    }

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@Valid @RequestBody PessoaRequest pessoa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.createPessoa(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @Valid @RequestBody PessoaRequest pessoa) {
        return ResponseEntity.ok(pessoaService.updatePessoa(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<PessoaResponse> adicionarEndereco(@PathVariable Long id,
            @Valid @RequestBody EnderecoRequest endereco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.adicionarEndereco(id, endereco));
    }

    @PutMapping("/{id}/enderecos")
    public ResponseEntity<PessoaResponse> atualizarEndereco(@PathVariable Long id,
            @Valid @RequestBody EnderecoRequest endereco) {
        return ResponseEntity.ok(pessoaService.atualizarEndereco(id, endereco));
    }

    @DeleteMapping("/{id}/enderecos")
    public ResponseEntity<Void> removerEndereco(@PathVariable Long id) {
        pessoaService.removerEndereco(id);
        return ResponseEntity.noContent().build();
    }
}