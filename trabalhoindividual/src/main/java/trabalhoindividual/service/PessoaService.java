    package trabalhoindividual.service;

    import java.util.List;

    import org.springframework.stereotype.Service;

    import trabalhoindividual.domain.Endereco;
    import trabalhoindividual.domain.Pessoa;
    import trabalhoindividual.dto.request.EnderecoRequest;
    import trabalhoindividual.dto.request.PessoaRequest;
    import trabalhoindividual.dto.response.EnderecoResponse;
    import trabalhoindividual.dto.response.PessoaResponse;
    import trabalhoindividual.exception.DuplicateEntryException;
    import trabalhoindividual.exception.ResourceNotFoundException;
    import trabalhoindividual.repository.PessoaRepository;

    @Service
    public class PessoaService {
        private final PessoaRepository pessoaRepository;

        public PessoaService(PessoaRepository pessoaRepository) {
            this.pessoaRepository = pessoaRepository;
        }

        public List<PessoaResponse> getAllPessoas() {
            return pessoaRepository.findAll().stream()
                    .map(this::toResponse)
                    .toList();
        }

        public PessoaResponse getPessoaById(Long id) {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
            return toResponse(pessoa);
        }

        public PessoaResponse createPessoa(PessoaRequest request) {
            if (pessoaRepository.existsByCpf(request.getCpf())) {
                throw new DuplicateEntryException("CPF já existe");
            }
            if (pessoaRepository.existsByEmail(request.getEmail())) {
                throw new DuplicateEntryException("Email já existe");
            }
            Pessoa pessoa = new Pessoa();
            pessoa.setNome(request.getNome());
            pessoa.setCpf(request.getCpf());
            pessoa.setEmail(request.getEmail());
            pessoa.setTelefone(request.getTelefone());
            return toResponse(pessoaRepository.save(pessoa));
        }

        public PessoaResponse updatePessoa(Long id, PessoaRequest request) {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
            pessoa.setNome(request.getNome());
            pessoa.setCpf(request.getCpf());
            pessoa.setEmail(request.getEmail());
            pessoa.setTelefone(request.getTelefone());
            return toResponse(pessoaRepository.save(pessoa));
        }

        public void deletePessoa(Long id) {
            getPessoaById(id);
            pessoaRepository.deleteById(id);
        }

        public PessoaResponse adicionarEndereco(Long id, EnderecoRequest request) {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
            Endereco endereco = new Endereco();
            endereco.setRua(request.getRua());
            endereco.setNumero(request.getNumero());
            endereco.setBairro(request.getBairro());
            endereco.setCidade(request.getCidade());
            endereco.setEstado(request.getEstado());
            endereco.setCep(request.getCep());
            pessoa.setEndereco(endereco);
            return toResponse(pessoaRepository.save(pessoa));
        }

        public PessoaResponse atualizarEndereco(Long id, EnderecoRequest request) {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
            Endereco endereco = pessoa.getEndereco() != null ? pessoa.getEndereco() : new Endereco();
            endereco.setRua(request.getRua());
            endereco.setNumero(request.getNumero());
            endereco.setBairro(request.getBairro());
            endereco.setCidade(request.getCidade());
            endereco.setEstado(request.getEstado());
            endereco.setCep(request.getCep());
            pessoa.setEndereco(endereco);
            return toResponse(pessoaRepository.save(pessoa));
        }

        public void removerEndereco(Long id) {
            Pessoa pessoa = pessoaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
            pessoa.setEndereco(null);
            pessoaRepository.save(pessoa);
        }

        private PessoaResponse toResponse(Pessoa pessoa) {
            PessoaResponse response = new PessoaResponse();
            response.setId(pessoa.getId());
            response.setNome(pessoa.getNome());
            response.setCpf(pessoa.getCpf());
            response.setEmail(pessoa.getEmail());
            response.setTelefone(pessoa.getTelefone());
            if (pessoa.getEndereco() != null) {
                EnderecoResponse endResp = new EnderecoResponse();
                endResp.setId(pessoa.getEndereco().getId());
                endResp.setRua(pessoa.getEndereco().getRua());
                endResp.setNumero(pessoa.getEndereco().getNumero());
                endResp.setBairro(pessoa.getEndereco().getBairro());
                endResp.setCidade(pessoa.getEndereco().getCidade());
                endResp.setEstado(pessoa.getEndereco().getEstado());
                endResp.setCep(pessoa.getEndereco().getCep());
                response.setEndereco(endResp);
            } else {
                response.setEndereco(null);
            }
            return response;
        }
    }