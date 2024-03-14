package postech.fiap.parquimetro.service;

import postech.fiap.parquimetro.model.Carro;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface EstacionamentoService {
    public ResponseEntity<?> estacionar(String parquimetroId, Carro carro, LocalDateTime entrada, LocalDateTime saida);
    public ResponseEntity<?> saida(String parquimetroId);
    public ResponseEntity<?> finalizar(String estacionamentoId);
}
