package trabalhoindividual.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class PessoaResponse {

    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private EnderecoResponse endereco;
    private List<InteresseAdocaoResponse> interessesAdocao;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoResponse endereco) {
        this.endereco = endereco;
    }

    public List<InteresseAdocaoResponse> getInteressesAdocao() {
        return interessesAdocao;
    }
    public void setInteressesAdocao(List<InteresseAdocaoResponse> interessesAdocao) {
        this.interessesAdocao = interessesAdocao;
    }
}
