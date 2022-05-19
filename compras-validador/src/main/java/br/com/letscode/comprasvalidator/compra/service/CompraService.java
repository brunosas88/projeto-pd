package br.com.letscode.comprasvalidator.compra.service;

import br.com.letscode.comprasvalidator.compra.dto.ValidacaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.service.PedidoService;
import br.com.letscode.comprasvalidator.producer.service.ProducerService;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService implements Subscriber<ValidacaoCompraDTO> {

    private final ProducerService producerService;
    private final PedidoService pedidoService;

    private void cadastraCompra(ValidacaoCompraDTO validacaoCompraDTO) {
        Mono.just(validacaoCompraDTO)
                .map(this::criarCompra)
                .subscribe(producerService);
    }

    private Compra criarCompra(ValidacaoCompraDTO validacaoCompraDTO) {

        Compra novaCompra = new Compra();
        novaCompra.setIdCompra(validacaoCompraDTO.getIdCompra());
        novaCompra.setDataCompra(validacaoCompraDTO.getDataCompra());
        novaCompra.setCpfCliente(validacaoCompraDTO.getRequisicaoCompraDTO().getCpf());
        novaCompra.setValorTotal(calcularValorTotalPedidos(validacaoCompraDTO.getRequisicaoCompraDTO().getPedido()));

        novaCompra.getPedidos().addAll(validacaoCompraDTO.getRequisicaoCompraDTO().getPedido()
                .stream()
                .map(pedidoService::salvarPedido)
                .map(Mono::block)
                .collect(Collectors.toList())
        );
        return novaCompra;
    }

    private Float calcularValorTotalPedidos (List<RequisicaoPedidoDTO> pedidosDTO) {
        Float valorTotal = 0F;
        for (RequisicaoPedidoDTO requisicaoPedidoDTO : pedidosDTO) {
            ProdutoDTO produto = ProdutoService.controleProduto(requisicaoPedidoDTO.getCodigoProduto()).block();
            valorTotal = valorTotal + ( produto.getPreco() * requisicaoPedidoDTO.getQtdProduto() );
        }
        return valorTotal;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(ValidacaoCompraDTO validacaoCompraDTO) {
        cadastraCompra(validacaoCompraDTO);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
