package br.com.letscode.comprasapi.pedido.model;

import br.com.letscode.comprasapi.compra.model.Compra;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    private String codigo;

    private Integer quantidade;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

}
