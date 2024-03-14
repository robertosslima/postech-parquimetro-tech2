package postech.fiap.parquimetro.repository;

import postech.fiap.parquimetro.model.Estacionamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends MongoRepository<Estacionamento, String> { }
