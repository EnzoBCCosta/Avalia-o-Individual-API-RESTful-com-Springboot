package trabalhoindividual.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import trabalhoindividual.domain.Endereco;
import trabalhoindividual.dto.request.EnderecoRequest;
import trabalhoindividual.dto.response.EnderecoResponse;
import trabalhoindividual.exception.ResourceNotFoundException;
import trabalhoindividual.repository.EnderecoRepository;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    private EnderecoResponse toResponse(Endereco endereco) {
        EnderecoResponse response = new EnderecoResponse();
        response.setId(endereco.getId());
        response.setRua(endereco.getRua());
        response.setNumero(endereco.getNumero());
        response.setBairro(endereco.getBairro());
        response.setCidade(endereco.getCidade());
        response.setEstado(endereco.getEstado());
        response.setCep(endereco.getCep());
        return response;
    }

    public Endereco toEntity(EnderecoRequest request) {
        Endereco endereco = new Endereco();
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());
        return endereco;
    }

    public List<EnderecoResponse> listarTodos() {
        return enderecoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public EnderecoResponse buscarPorId(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
        return toResponse(endereco);
    }

    public EnderecoResponse criar(EnderecoRequest request) {
        Endereco endereco = toEntity(request);
        return toResponse(enderecoRepository.save(endereco));
    }

    public EnderecoResponse atualizar(Long id, EnderecoRequest request) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
        endereco.setRua(request.getRua());
        endereco.setNumero(request.getNumero());
        endereco.setBairro(request.getBairro());
        endereco.setCidade(request.getCidade());
        endereco.setEstado(request.getEstado());
        endereco.setCep(request.getCep());
        return toResponse(enderecoRepository.save(endereco));
    }

    public void deletar(Long id) {
        enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
        enderecoRepository.deleteById(id);
    }
}