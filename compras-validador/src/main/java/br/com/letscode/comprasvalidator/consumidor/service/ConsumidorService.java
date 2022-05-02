package br.com.letscode.comprasvalidator.consumidor.service;

import br.com.letscode.comprasvalidator.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumidorService {

    private final CompraService compraService;

    @KafkaListener(topics = "COMPRA_TOPICO", groupId = "grupo-1")
    public void receberMensagem(RequisicaoCompraDTO requisicaoCompraDTO) {
        compraService.cadastraCompra(requisicaoCompraDTO);
    }
}
