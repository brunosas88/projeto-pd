package br.com.letscode.comprasvalidator.pedido.service;


import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService {

    public Mono<Pedido> salvarPedido(RequisicaoPedidoDTO pedidoDTO) {
        return ProdutoService.controleProduto(pedidoDTO.getCodigoProduto())
                .map(produtoDTO -> criaPedido(produtoDTO, pedidoDTO));
    }

    private Pedido criaPedido(ProdutoDTO produtoDTO, RequisicaoPedidoDTO pedidoDTO ) {
        Pedido novoPedido = new Pedido();
        novoPedido.setId(UUID.randomUUID().toString());
        novoPedido.setCodigo(pedidoDTO.getCodigoProduto());
        novoPedido.setQuantidade(pedidoDTO.getQtdProduto());
        novoPedido.setValor(produtoDTO.getPreco());
        return novoPedido;
    }
}
