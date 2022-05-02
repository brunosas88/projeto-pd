package br.com.letscode.comprasvalidator.pedido.repository;

import br.com.letscode.comprasvalidator.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
