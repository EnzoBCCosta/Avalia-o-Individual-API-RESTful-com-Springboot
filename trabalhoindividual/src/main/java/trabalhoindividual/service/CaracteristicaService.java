package trabalhoindividual.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import trabalhoindividual.domain.Caracteristica;
import trabalhoindividual.dto.request.CaracteristicaRequest;
import trabalhoindividual.dto.response.CaracteristicaResponse;
import trabalhoindividual.exception.DuplicateEntryException;
import trabalhoindividual.exception.ResourceNotFoundException;
import trabalhoindividual.repository.CaracteristicaRepository;

@Service
public class CaracteristicaService {

    private final CaracteristicaRepository caracteristicaRepository;

    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository) {
        this.caracteristicaRepository = caracteristicaRepository;
    }

    private CaracteristicaResponse toResponse(Caracteristica caracteristica) {
        CaracteristicaResponse response = new CaracteristicaResponse();
        response.setId(caracteristica.getId());
        response.setTipo(caracteristica.getTipo());
        response.setDescricao(caracteristica.getDescricao());
        return response;
    }

    private Caracteristica toEntity(CaracteristicaRequest request) {
        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setTipo(request.getTipo());
        caracteristica.setDescricao(request.getDescricao());
        return caracteristica;
    }

    public List<CaracteristicaResponse> listarTodos() {
        return caracteristicaRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CaracteristicaResponse buscarPorId(Long id) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Característica não encontrada"));
        return toResponse(caracteristica);
    }

    public CaracteristicaResponse criar(CaracteristicaRequest request) {
        Caracteristica caracteristica = toEntity(request);
        return toResponse(caracteristicaRepository.save(caracteristica));
    }

    public CaracteristicaResponse atualizar(Long id, CaracteristicaRequest request) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Característica não encontrada"));
        caracteristica.setTipo(request.getTipo());
        caracteristica.setDescricao(request.getDescricao());
        return toResponse(caracteristicaRepository.save(caracteristica));
    }


    //verificar se a característica está vinculada a algum animal antes de deletar  
    public void deletar(Long id) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Característica não encontrada"));

        if (caracteristica.getAnimais() != null && !caracteristica.getAnimais().isEmpty()) {
            throw new DuplicateEntryException(
                    "Característica está vinculada a um ou mais animais e não pode ser excluída.");
        }

        caracteristicaRepository.deleteById(id);
    }
}