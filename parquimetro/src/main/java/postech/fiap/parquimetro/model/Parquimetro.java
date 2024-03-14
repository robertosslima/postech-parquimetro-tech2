package postech.fiap.parquimetro.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Parquimetro {
    @Id
    private String id;

    @NotNull(message = "endereco não pode ser vazio")
    private Endereco endereco;

    private String estacionamentoId;

    private String stringfiedCarro;

    private String stringfiedProprietario;

    @Version
    private Long versao;

    public Parquimetro(Endereco endereco) {
        this.setEndereco(endereco);
        this.setEstacionamentoId(null);
        this.setStringfiedCarro(null);
        this.setStringfiedProprietario(null);
    }
}
