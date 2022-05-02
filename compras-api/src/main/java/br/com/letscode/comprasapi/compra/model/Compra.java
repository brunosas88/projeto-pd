package br.com.letscode.comprasapi.compra.model;


import br.com.letscode.comprasapi.pedido.model.Pedido;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "compra")
public class Compra{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompra;

    @Column(name = "data_compra")
    private LocalDateTime dataCompra;

    @Column(name = "cpf_cliente")
    private String cpfCliente;

    @Column(name = "valot_total", columnDefinition = "valot_total >= 0")
    private Float valorTotal;

    @OneToMany(mappedBy = "compra")
    private List<Pedido> pedidos = new ArrayList<>();

}
