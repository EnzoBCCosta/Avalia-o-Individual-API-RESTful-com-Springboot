package trabalhoindividual.domain;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa uma pessoa interessada em adotar animais")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da pessoa", example = "1")
    private Long id;

    @Schema(description = "Nome completo da pessoa", example = "Maria Silva")
    private String nome;

    @Schema(description = "E-mail da pessoa", example = "maria@email.com")
    private String email;

    @Schema(description = "CPF da pessoa", example = "123.456.789-09")
    private String cpf;

    @Schema(description = "Telefone da pessoa", example = "21987654321")
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @Schema(description = "Endereço da pessoa")
    private Endereco endereco;

    @OneToMany(mappedBy = "pessoa")
    @JsonManagedReference("pessoa-interesse")
    @Schema(description = "Lista de interesses de adoção da pessoa")
    private List<InteresseAdocao> interessesAdocao;

    public Pessoa() {
    }

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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<InteresseAdocao> getInteressesAdocao() {
        return interessesAdocao;
    }

    public void setInteressesAdocao(List<InteresseAdocao> interessesAdocao) {
        this.interessesAdocao = interessesAdocao;
    }
}
