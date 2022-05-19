package br.com.letscode.comprasvalidator.consumidor.service;

import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ConsumidorService {

    private final CompraService compraService;

    @KafkaListener(topics = "COMPRA_PROCESSADA", groupId = "grupo-1")
    public void receberMensagem(ValidacaoCompraDTO validacaoCompraDTO) {
        Mono.just(validacaoCompraDTO).subscribe(compraService);
    }
}
