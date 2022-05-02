package br.com.letscode.produtoapi.produto.model;


import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo", unique = true)
    private String codigo;

    @Column(name = "preco", columnDefinition = "preco > 0")
    private Float preco;

    @Column(name = "qtd_disponivel", columnDefinition = "qtd_disponivel >= 0")
    private Integer qtdDisponivel;

    public static Produto convertProdutoDTO (ProdutoDTO dto) {
        return Produto.builder().codigo(dto.getCodigo()).preco(dto.getPreco()).qtdDisponivel(dto.getQtdDisponivel()).build();
    }

}
