package br.com.letscode.comprasapi.compra.service;

import br.com.letscode.comprasapi.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RespostaCompraDTO;
import br.com.letscode.comprasapi.compra.repository.CompraRepository;
import br.com.letscode.comprasapi.producer.service.ProducerService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final ProducerService producerService;

    public void cadastraCompra(RequisicaoCompraDTO requisicaoCompraDTO) {
        producerService.enviarMensagem(requisicaoCompraDTO);
    }

    public Page<RespostaCompraDTO> listaCompras (Predicate predicate, Pageable pageable) {
        return compraRepository.findAll(predicate, pageable).map(RespostaCompraDTO::convertCompraToRespostaCompra);
    }
}
