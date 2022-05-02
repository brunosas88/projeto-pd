package br.com.letscode.comprasapi.producer.service;

import br.com.letscode.comprasapi.compra.dto.RequisicaoCompraDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, RequisicaoCompraDTO> kafkaTemplate;

    public void enviarMensagem(RequisicaoCompraDTO requisicaoCompraDTO) {
        kafkaTemplate.send("COMPRA_TOPICO", requisicaoCompraDTO);
    }
}
