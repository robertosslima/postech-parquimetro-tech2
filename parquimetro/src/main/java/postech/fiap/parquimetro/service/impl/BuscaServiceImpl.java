package postech.fiap.parquimetro.service.impl;

import postech.fiap.parquimetro.model.Estacionamento;
import postech.fiap.parquimetro.model.ParquimetroBusca;
import postech.fiap.parquimetro.model.Parquimetro;
import postech.fiap.parquimetro.service.BuscaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscaServiceImpl implements BuscaService {

    private final MongoTemplate mongoTemplate;

    public BuscaServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEstado(String estado) {
        Query query = new Query(
                Criteria
                        .where("endereco.estado")
                        .is(estado)
        );
        List<Parquimetro> result = this.mongoTemplate.find(query, Parquimetro.class);
        Page<Parquimetro> page = new PageImpl<Parquimetro>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCidade(String cidade) {
        Query query = new Query(
                Criteria
                        .where("endereco.cidade")
                        .is(cidade)
        );
        List<Parquimetro> result = this.mongoTemplate.find(query, Parquimetro.class);
        Page<Parquimetro> page = new PageImpl<Parquimetro>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorEndereco(String endereco) {
        Query query = new Query(
                Criteria
                        .where("endereco.endereco")
                        .is(endereco)
        );
        List<Parquimetro> result = this.mongoTemplate.find(query, Parquimetro.class);
        Page<Parquimetro> page = new PageImpl<Parquimetro>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorCep(String cep) {
        Query query = new Query(
                Criteria
                        .where("endereco.cep")
                        .is(cep)
        );
        List<Parquimetro> result = this.mongoTemplate.find(query, Parquimetro.class);
        Page<Parquimetro> page = new PageImpl<Parquimetro>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<Parquimetro>> buscaParquimetroPorObjeto(ParquimetroBusca parquimetroBusca) {
        Criteria criteria = new Criteria();
        if (parquimetroBusca.getEstado() != null) {
            criteria.and("endereco.estado").is(parquimetroBusca.getEstado());
        }
        if (parquimetroBusca.getCidade() != null) {
            criteria.and("endereco.cidade").is(parquimetroBusca.getCidade());
        }
        if (parquimetroBusca.getEndereco() != null) {
            criteria.and("endereco.endereco").is(parquimetroBusca.getEndereco());
        }
        if (parquimetroBusca.getCep() != null) {
            criteria.and("endereco.cep").is(parquimetroBusca.getCep());
        }
        if (parquimetroBusca.getEstacionado()) {
            criteria.and("estacionamento").ne(null);
            if (parquimetroBusca.getMarca() != null) {
                criteria.and("estacionamento.carro.marca").is(parquimetroBusca.getMarca());
            }
            if (parquimetroBusca.getModelo() != null) {
                criteria.and("estacionamento.carro.modelo").is(parquimetroBusca.getModelo());
            }
            if (parquimetroBusca.getPlaca() != null) {
                criteria.and("estacionamento.carro.placa").is(parquimetroBusca.getPlaca());
            }
            if (parquimetroBusca.getNome() != null) {
                criteria.and("estacionamento.carro.proprietario.nome").is(parquimetroBusca.getNome());
            }
            if (parquimetroBusca.getSobrenome() != null) {
                criteria.and("estacionamento.carro.proprietario.sobrenome").is(parquimetroBusca.getSobrenome());
            }
            if (parquimetroBusca.getCpf() != null) {
                criteria.and("estacionamento.carro.proprietario.cpf").is(parquimetroBusca.getCpf());
            }
            if (parquimetroBusca.getEntrada() != null && parquimetroBusca.getSaida() != null) {
                criteria.and("estacionamento.entrada").gte(parquimetroBusca.getEntrada());
                criteria.and("estacionamento.saida").lte(parquimetroBusca.getSaida());
            } else if (parquimetroBusca.getEntrada() != null) {
                criteria.and("estacionamento.entrada").gte(parquimetroBusca.getEntrada());
            } else if (parquimetroBusca.getSaida() != null) {
                criteria.and("estacionamento.saida").is(parquimetroBusca.getSaida());
            }
        } else {
            criteria.and("estacionamento").isNull();
        }
        Query query = new Query(criteria);
        List<Parquimetro> result = this.mongoTemplate.find(query, Parquimetro.class);
        Page<Parquimetro> page = new PageImpl<Parquimetro>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }

    @Override
    public ResponseEntity<Page<Estacionamento>> buscaEstacionamentoPorParquimetroId(String parquimetroId) {
        Criteria criteria = new Criteria();
        criteria.and("id").is(parquimetroId);
        Query query = new Query(
                Criteria
                    .where("parquimetroId")
                    .is(parquimetroId)
        );
        List<Estacionamento> result = this.mongoTemplate.find(query, Estacionamento.class);
        Page<Estacionamento> page = new PageImpl<Estacionamento>(result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(page);
    }
}
