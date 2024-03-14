package postech.fiap.parquimetro.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Estacionamento {
    @Id
    private String id;

    @NotNull
    private Carro carro;

    @NotNull
    private LocalDateTime entrada;

    @NotNull
    private LocalDateTime saida;

    private EstacionamentoStatus status;

    @NotNull
    private String parquimetroId;

    private String endereco;

    @Version
    private Long versao;

    public Estacionamento(Carro carro, LocalDateTime entrada, LocalDateTime saida, String parquimetroId, String endereco) {
        this.setCarro(carro);
        this.setEntrada(entrada);
        this.setSaida(saida);
        this.setParquimetroId(parquimetroId);
        this.setEndereco(endereco);
        this.setStatus(EstacionamentoStatus.INICIADO);
    }


    public void setEstacionamentoStatus(EstacionamentoStatus status) {
        this.status = status;
    }

    public Integer getEstacionamentoTempo() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.compareTo(this.entrada);
    }
}
