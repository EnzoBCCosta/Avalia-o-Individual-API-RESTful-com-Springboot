package trabalhoindividual.domain;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa o endereço de uma pessoa")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do endereço", example = "1")
    private Long id;

    @Schema(description = "Nome da rua", example = "Rua das Flores")
    private String rua;

    @Schema(description = "Número do imóvel", example = "123")
    private String numero;

    @Schema(description = "Nome da cidade", example = "Petrópolis")
    private String cidade;

    @Schema(description = "Nome do bairro", example = "Centro")
    private String bairro;

    @Schema(description = "Estado (sigla)", example = "RJ")
    private String estado;

    @Schema(description = "CEP do endereço", example = "25610-100")
    private String cep;

    public Endereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
