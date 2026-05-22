package trabalhoindividual.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class InteresseAdocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInteresse;
    private String status;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference("pessoa-interesse") 
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "animal_id")
    @JsonBackReference("animal-interesse")
    private Animal animal;

    public InteresseAdocao() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataInteresse() { return dataInteresse; }
    public void setDataInteresse(LocalDate dataInteresse) { this.dataInteresse = dataInteresse; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public Pessoa getPessoa() { return pessoa; }
    public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
}