package postech.fiap.parquimetro.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import postech.fiap.parquimetro.model.Estacionamento;
import postech.fiap.parquimetro.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Estacionamento", description = "Endpoints para registrar os carros estacionados.")

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    @Autowired
    EstacionamentoService service;

    @PostMapping("/estacionar/{parquimetroId}")
    public ResponseEntity<?> estacionar(@PathVariable String parquimetroId, @RequestBody Estacionamento estacionamento) {
        return this.service.estacionar(parquimetroId, estacionamento.getCarro(), estacionamento.getEntrada(), estacionamento.getSaida());
    }

    @PostMapping("/finalizar/{parquimetroId}")
    public ResponseEntity<?> finalizar(@PathVariable String parquimetroId) {
        return this.service.finalizar(parquimetroId);
    }

    @PostMapping("/saida/{estacionamentoId}")
    public ResponseEntity<?> saida(@PathVariable String estacionamentoId) {
        return this.service.saida(estacionamentoId);
    }
}
