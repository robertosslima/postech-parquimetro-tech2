package postech.fiap.parquimetro.repository;

import postech.fiap.parquimetro.model.Parquimetro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParquimetroRepository extends MongoRepository<Parquimetro, String> { }
