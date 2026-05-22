package trabalhoindividual.dto.response;

import java.time.LocalDate;

public class InteresseAdocaoResponse {
     private Long id;
    private LocalDate dataInteresse;
    private String status;
    private String observacao;
    private String nomePessoa;
    private String nomeAnimal;

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

    public String getNomePessoa() {
         return nomePessoa; 
        }
    public void setNomePessoa(String nomePessoa) {
         this.nomePessoa = nomePessoa; 
        }

    public String getNomeAnimal() {
         return nomeAnimal; 
        }
    public void setNomeAnimal(String nomeAnimal) {
         this.nomeAnimal = nomeAnimal; 
        }
}

