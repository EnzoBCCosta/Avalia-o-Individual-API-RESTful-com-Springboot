package trabalhoindividual.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import trabalhoindividual.domain.Animal;
import trabalhoindividual.domain.InteresseAdocao;
import trabalhoindividual.domain.Pessoa;
import trabalhoindividual.dto.request.InteresseAdocaoRequest;
import trabalhoindividual.dto.response.InteresseAdocaoResponse;
import trabalhoindividual.exception.ResourceNotFoundException;
import trabalhoindividual.repository.AnimalRepository;
import trabalhoindividual.repository.InteresseAdocaoRepository;
import trabalhoindividual.repository.PessoaRepository;

@Service
public class InteresseAdocaoService {

    private final InteresseAdocaoRepository interesseAdocaoRepository;
    private final PessoaRepository pessoaRepository;
    private final AnimalRepository animalRepository;

    public InteresseAdocaoService(
            InteresseAdocaoRepository interesseAdocaoRepository,
            PessoaRepository pessoaRepository,
            AnimalRepository animalRepository) {
        this.interesseAdocaoRepository = interesseAdocaoRepository;
        this.pessoaRepository = pessoaRepository;
        this.animalRepository = animalRepository;
    }

    private InteresseAdocaoResponse toResponse(InteresseAdocao interesse) {
        InteresseAdocaoResponse response = new InteresseAdocaoResponse();
        response.setId(interesse.getId());
        response.setDataInteresse(interesse.getDataInteresse());
        response.setStatus(interesse.getStatus());
        response.setObservacao(interesse.getObservacao());
        response.setNomePessoa(interesse.getPessoa().getNome());
        response.setNomeAnimal(interesse.getAnimal().getNome());
        return response;
    }

    public List<InteresseAdocaoResponse> listarTodos() {
        return interesseAdocaoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public InteresseAdocaoResponse buscarPorId(Long id) {
        InteresseAdocao interesse = interesseAdocaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interesse de adoção não encontrado"));
        return toResponse(interesse);
    }

    public List<InteresseAdocao> buscarPorPessoa(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Pessoa não encontrada: " + pessoaId);
        }
        return interesseAdocaoRepository.findByPessoaId(pessoaId);
    }

    public List<InteresseAdocaoResponse> buscarPorAnimal(Long animalId) {
        return interesseAdocaoRepository.findByAnimalId(animalId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public InteresseAdocaoResponse criar(Long pessoaId, Long animalId, InteresseAdocaoRequest request) {
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException

                ("Pessoa não encontrada"));

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado"));

        InteresseAdocao interesse = new InteresseAdocao();
        interesse.setDataInteresse(request.getDataInteresse());
        interesse.setStatus(request.getStatus());
        interesse.setObservacao(request.getObservacao());
        interesse.setPessoa(pessoa);
        interesse.setAnimal(animal);

        return toResponse(interesseAdocaoRepository.save(interesse));
    }

    public InteresseAdocaoResponse atualizar(Long id, InteresseAdocaoRequest request) {
        InteresseAdocao interesse = interesseAdocaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interesse de adoção não encontrado"));

        interesse.setStatus(request.getStatus());
        interesse.setObservacao(request.getObservacao());
        interesse.setDataInteresse(request.getDataInteresse());

        return toResponse(interesseAdocaoRepository.save(interesse));
    }

    public void deletar(Long id) {
        interesseAdocaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interesse de adoção não encontrado"));
        interesseAdocaoRepository.deleteById(id);
    }
}