package trabalhoindividual.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public class AnimalRequest {

    @NotBlank(message = "O nome do animal é obrigatório.")
    private String nome;

    @NotBlank(message = "A espécie do animal é obrigatória.")
    private String especie;

    @NotBlank(message = "A raça do animal é obrigatória.")
    private String raca;

    @NotNull(message = "A idade do animal é obrigatória.")
    @Min(value = 0, message = "A idade deve ser positiva.")
    private Integer idade;

    @NotBlank(message = "O sexo do animal é obrigatório.")
    private String sexo;

    @NotBlank(message = "O porte do animal é obrigatório.")
    private String porte;

    @NotBlank(message = "O status do animal é obrigatório.")
    private String status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}