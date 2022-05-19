package br.com.letscode.comprasapi.compra.dto;


import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.pedido.dto.RequisicaoPedidoDTO;
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

    public static ValidacaoCompraDTO convertToDTO (Compra compra, RequisicaoCompraDTO requisicaoDTO) {
        ValidacaoCompraDTO dto = new ValidacaoCompraDTO();
        dto.setIdCompra(compra.getIdCompra());
        dto.setDataCompra(compra.getDataCompra());
        dto.setRequisicaoCompraDTO(requisicaoDTO);
        return dto;
    }
}
