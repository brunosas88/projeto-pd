package br.com.letscode.produtoapi.produto.service;

import br.com.letscode.produtoapi.cache.CacheService;
import br.com.letscode.produtoapi.produto.dto.ProdutoDTO;
import br.com.letscode.produtoapi.produto.model.Produto;
import br.com.letscode.produtoapi.produto.repository.ProdutoRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CacheService cacheService;

    public ProdutoDTO cadastrarProduto (ProdutoDTO produtoDTO){
        return ProdutoDTO.convertProdutoToDTO(produtoRepository.save(Produto.convertProdutoDTO(produtoDTO)));
    }

    public Page<ProdutoDTO> listarProdutos(Predicate predicate, Pageable pageable){
        return produtoRepository.findAll(predicate, pageable).map(ProdutoDTO::convertProdutoToDTO);
    }

    public ProdutoDTO buscarProdutoPorCodigo (String codigo){
//        ProdutoDTO produtoBuscado = cacheService.busca(codigo);
//        if (Objects.equals(produtoBuscado, null)) {
//            produtoBuscado = ProdutoDTO.convertProdutoToDTO(produtoRepository.findProdutoByCodigo(codigo));
//            cacheService.salvarEmCache(produtoBuscado);
//        }
//        return cacheService.busca(codigo).orElseGet(()-> {
//            produtoRepository.findProdutoByCodigo(codigo).ifPresent(produto -> cacheService.salvarEmCache(ProdutoDTO.convertProdutoToDTO(produto)));
//            return ProdutoDTO.convertProdutoToDTO(produtoRepository.findProdutoByCodigo(codigo).orElseGet(()-> Produto.builder().codigo("null").build()));
//        });
        return ProdutoDTO.convertProdutoToDTO(produtoRepository.findProdutoByCodigo(codigo).get());
    }



}
