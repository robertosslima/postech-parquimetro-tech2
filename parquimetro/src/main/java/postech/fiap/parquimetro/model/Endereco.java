package postech.fiap.parquimetro.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Endereco {
    @NotNull(message = "estado não pode ser vazio")
    private String estado;

    @NotNull(message = "cidade não pode ser vazio")
    private String cidade;

   @NotNull(message = "endereço não pode ser vazio")
    private String endereco;

    @NotNull(message = "numero não pode ser vazio")
    private Integer numero;

    @NotNull(message = "cep não pode ser vazio")
    private String cep;

    public String toString() {
        return "Address: " + this.estado + " - " + this.cidade + " - " + this.endereco;
    }
}
