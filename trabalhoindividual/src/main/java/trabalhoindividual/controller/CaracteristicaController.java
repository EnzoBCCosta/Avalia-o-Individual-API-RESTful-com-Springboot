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
import trabalhoindividual.dto.request.CaracteristicaRequest;
import trabalhoindividual.dto.response.CaracteristicaResponse;
import trabalhoindividual.service.CaracteristicaService;

@RestController
@RequestMapping("/caracteristicas")
@Tag(name = "Características", description = "Gerenciamento de características dos animais")
public class CaracteristicaController {

    private final CaracteristicaService caracteristicaService;

    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @Operation(summary = "Listar todas as características")
    @GetMapping
    public ResponseEntity<List<CaracteristicaResponse>> listarTodos() {
        return ResponseEntity.ok(caracteristicaService.listarTodos());
    }

    @Operation(summary = "Buscar característica por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Característica encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Característica não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(caracteristicaService.buscarPorId(id));
    }

    @Operation(summary = "Cadastrar nova característica", description = "Cria uma nova característica para associar a animais")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Característica criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<CaracteristicaResponse> criar(@RequestBody @Valid CaracteristicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaService.criar(request));
    }

    @Operation(summary = "Atualizar característica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Característica atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Característica não encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaResponse> atualizar(@PathVariable Long id,
            @RequestBody @Valid CaracteristicaRequest request) {
        return ResponseEntity.ok(caracteristicaService.atualizar(id, request));
    }

    @Operation(summary = "Deletar característica")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Característica deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Característica não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        caracteristicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
