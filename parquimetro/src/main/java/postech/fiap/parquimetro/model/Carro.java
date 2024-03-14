package postech.fiap.parquimetro.model;

import lombok.Data;

@Data
public class Carro {
    private String marca;
    private String modelo;
    private String placa;
    private Proprietario proprietario;

    public String toString() {
        return "Car: " + this.marca + " - " + this.modelo + " - " + this.placa;
    }
}
