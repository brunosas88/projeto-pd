package br.com.letscode.produtoapi.produto.dto;


import br.com.letscode.produtoapi.produto.model.Produto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDTO implements Serializable {
    private String codigo;
    private Float preco;
    private Integer qtdDisponivel;

    public static ProdutoDTO convertProdutoToDTO (Produto produto) {
        return new ProdutoDTO(produto.getCodigo(), produto.getPreco(), produto.getQtdDisponivel());
    }

}
