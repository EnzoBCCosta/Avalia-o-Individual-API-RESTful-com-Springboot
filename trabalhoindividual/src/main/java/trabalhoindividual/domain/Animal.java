package trabalhoindividual.domain;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa um animal disponível para adoção")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do animal", example = "1")
    private Long id;

    @Schema(description = "Nome do animal", example = "Rex")
    private String nome;

    @Schema(description = "Espécie do animal", example = "Cachorro")
    private String especie;

    @Schema(description = "Raça do animal", example = "Labrador")
    private String raca;

    @Schema(description = "Porte do animal", example = "Grande")
    private String porte;

    @Schema(description = "Sexo do animal", example = "Macho")
    private String sexo;

    @Schema(description = "Status de adoção do animal", example = "Disponível")
    private String status;

    @Schema(description = "Idade do animal em anos", example = "3")
    private Integer idade;

    // ManyToMany com Caracteristica: @JsonIgnoreProperties evita loop
    // Animal serializa caracteristicas, mas dentro de cada Caracteristica
    // o campo "animais" é ignorado
    @ManyToMany
    @JoinTable(name = "animal_caracteristica", joinColumns = @JoinColumn(name = "animal_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    @JsonIgnoreProperties("animais")
    @Schema(description = "Lista de características associadas ao animal")
    private List<Caracteristica> caracteristicas;

    // OneToMany com InteresseAdocao: Animal é o lado "pai" (Managed)
    // InteresseAdocao.animal é o lado "filho" (Back) — evita JSON infinito
    @OneToMany(mappedBy = "animal")
    @JsonManagedReference("animal-interesse")
    @Schema(description = "Lista de interesses de adoção registrados para este animal")
    private List<InteresseAdocao> interessesAdocao;

    public Animal() {
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public List<InteresseAdocao> getInteressesAdocao() {
        return interessesAdocao;
    }

    public void setInteressesAdocao(List<InteresseAdocao> interessesAdocao) {
        this.interessesAdocao = interessesAdocao;
    }
}
