package postech.fiap.parquimetro.service;

import postech.fiap.parquimetro.model.Estacionamento;
import postech.fiap.parquimetro.model.ParquimetroBusca;
import postech.fiap.parquimetro.model.Parquimetro;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface BuscaService {
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEstado(String estado);
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCidade(String cidade);
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEndereco(String endereco);
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCep(String cep);
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorObjeto(ParquimetroBusca parquimetroBusca);
    public ResponseEntity<Page<Estacionamento>> buscaEstacionamentoPorParquimetroId(String ParquimetroId);
}
