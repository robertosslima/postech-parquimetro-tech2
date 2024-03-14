package postech.fiap.parquimetro.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import postech.fiap.parquimetro.model.Estacionamento;
import postech.fiap.parquimetro.model.Parquimetro;
import postech.fiap.parquimetro.model.ParquimetroBusca;
import postech.fiap.parquimetro.service.BuscaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Consultas", description = "Endpoints para buscar informções do paquimetro e estacionamento")
@RestController
@RequestMapping("/buscar")
public class BuscaController {

    @Autowired
    private BuscaService buscaService;

    @GetMapping("/parquimetro/por-estado/{estado}")
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEstado(@PathVariable String estado) {
        return this.buscaService.buscaParquimetroPorEstado(estado);
    }

    @GetMapping("/parquimetro/por-cidade/{cidade}")
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCidade(@PathVariable String cidade) {
        return this.buscaService.buscaParquimetroPorCidade(cidade);
    }

    @GetMapping("/parquimetro/por-endereco/{endereco}")
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEndereco(@PathVariable String endereco) {
        return this.buscaService.buscaParquimetroPorEndereco(endereco);
    }

    @GetMapping("/parquimetro/por-cep/{cep}")
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCep(@PathVariable String cep) {
        return this.buscaService.buscaParquimetroPorCep(cep);
    }

    @GetMapping("/parquimetro/por-objeto")
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorObjeto(@RequestBody ParquimetroBusca parquimetroBusca) {
        return this.buscaService.buscaParquimetroPorObjeto(parquimetroBusca);
    }

    @GetMapping("/estacionamento/por-parquimetroid/{parquimetroId}")
    public ResponseEntity<Page<Estacionamento>> buscaEstacionamentoPorParquimetroId(@PathVariable String parquimetroId) {
        return this.buscaService.buscaEstacionamentoPorParquimetroId(parquimetroId);
    }

}
