package br.com.letscode.comprasvalidator.pedido.service;


import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import br.com.letscode.comprasvalidator.pedido.repository.PedidoRepository;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;


    public Pedido salvarPedido(RequisicaoPedidoDTO pedidoDTO, Compra novaCompra) {
        Pedido novoPedido = new Pedido();
        ProdutoDTO produto = ProdutoService.controleProduto(pedidoDTO.getCodigoProduto());
        novoPedido.setCodigo(pedidoDTO.getCodigoProduto());
        novoPedido.setQuantidade(pedidoDTO.getQtdProduto());
        novoPedido.setValor(produto.getPreco());
        novoPedido.setCompra(novaCompra);
        return pedidoRepository.save(novoPedido);
    }



}
