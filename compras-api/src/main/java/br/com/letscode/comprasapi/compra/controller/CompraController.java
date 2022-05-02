package br.com.letscode.comprasapi.compra.controller;


import br.com.letscode.comprasapi.compra.dto.RequisicaoCompraDTO;
import br.com.letscode.comprasapi.compra.dto.RespostaCompraDTO;
import br.com.letscode.comprasapi.compra.model.Compra;
import br.com.letscode.comprasapi.compra.service.CompraService;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("compras")
@RequiredArgsConstructor
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public void cadastrarCompra (@RequestBody RequisicaoCompraDTO requisicaoCompra) {
        compraService.cadastraCompra(requisicaoCompra);
    }

    @GetMapping
    public ResponseEntity<Page<RespostaCompraDTO>> listarCompras (@QuerydslPredicate(root = Compra.class) Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(compraService.listaCompras(predicate, pageable));
    }

}
