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
import trabalhoindividual.dto.request.CaracteristicaRequest;
import trabalhoindividual.dto.response.CaracteristicaResponse;
import trabalhoindividual.service.CaracteristicaService;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    private final CaracteristicaService caracteristicaService;

    public CaracteristicaController(CaracteristicaService caracteristicaService) {
        this.caracteristicaService = caracteristicaService;
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicaResponse>> listarTodos() {
        return ResponseEntity.ok(caracteristicaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(caracteristicaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<CaracteristicaResponse> criar(@RequestBody @Valid CaracteristicaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaService.criar(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaResponse> atualizar(@PathVariable Long id,
            @RequestBody @Valid CaracteristicaRequest request) {
        return ResponseEntity.ok(caracteristicaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        caracteristicaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}