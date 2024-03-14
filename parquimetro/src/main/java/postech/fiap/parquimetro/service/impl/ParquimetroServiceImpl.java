package postech.fiap.parquimetro.service.impl;

import postech.fiap.parquimetro.model.Endereco;
import postech.fiap.parquimetro.model.Parquimetro;
import postech.fiap.parquimetro.repository.ParquimetroRepository;
import postech.fiap.parquimetro.service.ParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ParquimetroServiceImpl implements ParquimetroService {

    @Autowired
    private ParquimetroRepository repository;

    @Override
    public ResponseEntity<?> todos(Pageable pageable) {
        try {
            Page<Parquimetro> pagina = this.repository.findAll(pageable);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(pagina);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar todos: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> porId(String id) {
        try {
            Parquimetro parquimetro = this.repository.findById(id).orElse(null);
            if (parquimetro == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parquimetro não encontrado");
            }
            return ResponseEntity.status(HttpStatus.OK).body(parquimetro);
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar parquimetro Id: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> criar(Endereco endereco) {
        Parquimetro parquimetro = new Parquimetro(endereco);
        this.repository.save(parquimetro);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public ResponseEntity<?> atualizaEndereco(String id, Endereco atualizaEndereco) {
        try {
            Parquimetro currentParquimetro = this.repository.findById(id).orElse(null);
            if (currentParquimetro == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parquimetro não encontrado");
            }
            if (currentParquimetro.getEstacionamentoId() != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Desestacione primeiro para atualizar");
            }
            currentParquimetro.setEndereco(atualizaEndereco);
            this.repository.save(currentParquimetro);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar endereco do parquimetro ID: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> excluir(String id) {
        try {
            Parquimetro parquimetro = this.repository.findById(id).orElse(null);
            if (parquimetro == null) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Parquimetro não encontrado");
            }
            if (parquimetro.getEstacionamentoId() != null) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Desestacione primeiro para excluir");
            }
            this.repository.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao excluir: " + exception.getMessage());
        }
    }
}
