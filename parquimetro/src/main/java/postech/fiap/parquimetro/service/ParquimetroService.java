package postech.fiap.parquimetro.service;

import postech.fiap.parquimetro.model.Endereco;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ParquimetroService {
    public ResponseEntity<?> todos(Pageable pageable);
    public ResponseEntity<?> porId(String id);
    public ResponseEntity<?> criar(Endereco endereco);
    public ResponseEntity<?> atualizaEndereco(String id, Endereco endereco);
    public ResponseEntity<?> excluir(String id);
}
