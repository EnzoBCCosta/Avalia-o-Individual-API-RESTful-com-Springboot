package trabalhoindividual.dto.response;

import java.util.List;

public class AnimalResponse {

    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private String porte;
    private Integer idade;
    private String sexo;
    private String status;
    private List<CaracteristicaResponse> caracteristicas;

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

    public String getPorte() {
        return porte;
    }
    public void setPorte(String porte) {
        this.porte = porte;
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

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(List<CaracteristicaResponse> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
