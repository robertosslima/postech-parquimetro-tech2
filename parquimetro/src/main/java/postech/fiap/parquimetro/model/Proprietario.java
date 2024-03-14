package postech.fiap.parquimetro.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class Proprietario {
    private String nome;
    private String sobrenome;
    @CPF
    private String cpf;

    public String toString() {
        return "Owner: " + this.nome + " " + this.sobrenome + " - " + this.cpf;
    }
}
