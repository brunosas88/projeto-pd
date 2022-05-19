package br.com.letscode.comprasvalidator.produto.service;

import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ProdutoService {

    public static Mono<ProdutoDTO> buscarProduto (String codigo) {
        WebClient webClient = WebClient.create("http://localhost:8081");
        return webClient
                .get()
                .uri("/produtos/busca/{codigo}", codigo)
                .retrieve()
                .bodyToMono(ProdutoDTO.class);
    }

    public static Mono<ProdutoDTO> controleProduto (String codigo) {
        return ProdutoService.buscarProduto(codigo).switchIfEmpty(Mono.error(new RuntimeException("Produto não encontrado")));
    }

}
