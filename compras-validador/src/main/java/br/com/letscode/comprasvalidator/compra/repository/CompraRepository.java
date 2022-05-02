package br.com.letscode.comprasvalidator.compra.repository;



import br.com.letscode.comprasvalidator.compra.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
}