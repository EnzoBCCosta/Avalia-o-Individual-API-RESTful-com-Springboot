package trabalhoindividual.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class InteresseAdocaoRequest {

    @NotNull(message = "Data de interesse é obrigatória")
    @PastOrPresent(message = "Data de interesse deve estar no passado ou presente")
    private LocalDate dataInteresse;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    private String observacao;

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
}