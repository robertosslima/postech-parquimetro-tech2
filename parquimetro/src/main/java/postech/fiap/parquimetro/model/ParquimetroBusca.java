package postech.fiap.parquimetro.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ParquimetroBusca {
    private String estado;
    private String cidade;
    private String endereco;
    private Integer numero;
    private String cep;
    private Boolean estacionado;
    private String marca;
    private String modelo;
    private String placa;
    private String nome;
    private String sobrenome;
    private String cpf;
    private LocalDateTime entrada;
    private LocalDateTime saida;
}
