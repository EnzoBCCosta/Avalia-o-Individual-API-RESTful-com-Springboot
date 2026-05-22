package trabalhoindividual.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entidade que representa uma característica associada a animais")
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da característica", example = "1")
    private Long id;

    @Schema(description = "Tipo da característica", example = "Temperamento")
    private String tipo;

    @Schema(description = "Descrição da característica", example = "Animal dócil e brincalhão")
    private String descricao;

    @ManyToMany(mappedBy = "caracteristicas")
    @JsonIgnoreProperties("caracteristicas")
    @Schema(description = "Lista de animais que possuem esta característica")
    private List<Animal> animais;

    public Caracteristica() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void setAnimais(List<Animal> animais) {
        this.animais = animais;
    }
}
