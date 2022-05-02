package br.com.letscode.comprasvalidator.compra.service;



import br.com.letscode.comprasvalidator.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasvalidator.compra.model.Compra;
import br.com.letscode.comprasvalidator.compra.repository.CompraRepository;
import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import br.com.letscode.comprasvalidator.produto.service.ProdutoService;
import br.com.letscode.comprasvalidator.pedido.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompraService {

    private final CompraRepository compraRepository;
    private final PedidoService pedidoService;

    public void cadastraCompra(RequisicaoCompraDTO requisicaoCompraDTO) {

        Compra novaCompra = new Compra();
        novaCompra.setDataCompra(LocalDateTime.now());
        novaCompra.setCpfCliente(requisicaoCompraDTO.getCpf());
        novaCompra.setValorTotal(calcularValorTotalPedidos(requisicaoCompraDTO.getPedido()));

        Compra novaCompraComIdRegistrado = compraRepository.save(novaCompra);

        novaCompraComIdRegistrado.getPedidos().addAll( requisicaoCompraDTO.getPedido()
                .stream()
                .map(novoPedido ->
                    pedidoService.salvarPedido(novoPedido, novaCompraComIdRegistrado)
                )
                .collect(Collectors.toList())
        );

        compraRepository.save(novaCompraComIdRegistrado);
    }


    private Float calcularValorTotalPedidos (List<RequisicaoPedidoDTO> pedidosDTO) {
        Float valorTotal = 0F;
        for (RequisicaoPedidoDTO requisicaoPedidoDTO : pedidosDTO) {
            ProdutoDTO produto = ProdutoService.controleProduto(requisicaoPedidoDTO.getCodigoProduto());
            valorTotal = valorTotal + ( produto.getPreco() * requisicaoPedidoDTO.getQtdProduto() );
        }
        return valorTotal;
    }
}
