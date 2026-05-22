package trabalhoindividual.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa o interesse de uma pessoa em adotar um animal")
public class InteresseAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do interesse de adoção", example = "1")
    private Long id;

    @Schema(description = "Data em que o interesse foi registrado", example = "2025-05-20")
    private LocalDate dataInteresse;

    @Schema(description = "Status do interesse", example = "Pendente")
    private String status;

    @Schema(description = "Observações adicionais sobre o interesse")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference("pessoa-interesse")
    @Schema(description = "Pessoa que registrou o interesse")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    @JsonBackReference("animal-interesse")
    @Schema(description = "Animal pelo qual há interesse de adoção")
    private Animal animal;

    public InteresseAdocao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInteresse() {
        return dataInteresse;
    }

    public void setDataInteresse(LocalDate dataInteresse) {
        this.dataInteresse = dataInteresse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
