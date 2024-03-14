package postech.fiap.parquimetro.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import postech.fiap.parquimetro.model.Endereco;
import postech.fiap.parquimetro.service.ParquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Parquimetro", description = "Endpoints relacionados ao parquimtero")
@RestController
@RequestMapping(value = "/parquimetro")
public class ParquimetroController {

    @Autowired
    private ParquimetroService service;

    @GetMapping
    public ResponseEntity<?> todos(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return this.service.todos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable String id) {
        return this.service.porId(id);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Endereco endereco) {
        return this.service.criar(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaEndereco(
            @PathVariable String id,
            @RequestBody Endereco atualizaEndereco
    ) {
        return this.service.atualizaEndereco(id, atualizaEndereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable String id) {
        return this.service.excluir(id);
    }
}
