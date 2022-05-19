package br.com.letscode.comprasvalidator.compra.dto;

import br.com.letscode.comprasvalidator.pedido.dto.RequisicaoPedidoDTO;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoCompraDTO {
    private String idCompra;
    private LocalDateTime dataCompra;
    private RequisicaoCompraDTO requisicaoCompraDTO;
}
