package postech.fiap.parquimetro.service.impl;

import postech.fiap.parquimetro.model.Carro;
import postech.fiap.parquimetro.model.Estacionamento;
import postech.fiap.parquimetro.model.EstacionamentoStatus;
import postech.fiap.parquimetro.model.Parquimetro;
import postech.fiap.parquimetro.repository.EstacionamentoRepository;
import postech.fiap.parquimetro.repository.ParquimetroRepository;
import postech.fiap.parquimetro.service.EstacionamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    @Autowired
    private ParquimetroRepository parquimetroRepository;

    @Autowired
    private EstacionamentoRepository estacionamentoRepository;

    @Override
    public ResponseEntity<?> estacionar(String parquimetroId, Carro carro, LocalDateTime entrada, LocalDateTime saida) {
        try {
            Parquimetro parquimetro = this.findParkingMeterById(parquimetroId);
            Estacionamento estacionamento = new Estacionamento(carro, entrada, saida, parquimetroId, parquimetro.getEndereco().toString());
            estacionamento = this.estacionamentoRepository.save(estacionamento);
            parquimetro.setEstacionamentoId(estacionamento.getId());
            parquimetro.setStringfiedCarro(estacionamento.getCarro().toString());
            parquimetro.setStringfiedProprietario(estacionamento.getCarro().getProprietario().toString());
            this.parquimetroRepository.save(parquimetro);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no parquimetro: " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> finalizar(String parquimetroId) {
        try {
            Parquimetro parquimetro = this.findParkingMeterById(parquimetroId);
            Estacionamento estacionamento = this.findParkingLotById(parquimetro.getEstacionamentoId());
            estacionamento.setEstacionamentoStatus(EstacionamentoStatus.FINALIZADO);
            Integer tempoEstacionado = estacionamento.getEstacionamentoTempo();
            this.estacionamentoRepository.save(estacionamento);
            parquimetro.setEstacionamentoId(null);
            parquimetro.setStringfiedCarro(null);
            parquimetro.setStringfiedProprietario(null);
            this.parquimetroRepository.save(parquimetro);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Você está estacionado por " + tempoEstacionado + " horas");
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro na saida do estacionamento " + exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> saida(String parkingLotId) {
        try {
            Estacionamento estacionamento = this.findParkingLotById(parkingLotId);
            LocalDateTime novoTempo = estacionamento.getSaida().plusHours(1);
            estacionamento.setSaida(novoTempo);
            this.estacionamentoRepository.save(estacionamento);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception exception) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro no estacionamento: " + exception.getMessage());
        }
    }

    private Parquimetro findParkingMeterById(String parkingMeterId) {
        return this.parquimetroRepository.findById(parkingMeterId).orElseThrow(() -> new RuntimeException("Parking meter not found"));
    }

    private Estacionamento findParkingLotById(String parkingLotId) {
        return this.estacionamentoRepository.findById(parkingLotId).orElseThrow(() -> new RuntimeException("Parking lot not found"));
    }
}
