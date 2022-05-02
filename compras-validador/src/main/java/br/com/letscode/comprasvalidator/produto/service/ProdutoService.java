package br.com.letscode.comprasvalidator.produto.service;

import br.com.letscode.comprasvalidator.produto.dto.ProdutoDTO;
import org.springframework.web.reactive.function.client.WebClient;

public class ProdutoService {

    public static ProdutoDTO buscarProduto (String codigo) {
        WebClient webClient = WebClient.create("http://localhost:8081");
        return webClient
                .get()
                .uri("/produtos/busca/{codigo}", codigo)
                .retrieve()
                .bodyToMono(ProdutoDTO.class)
                .block();
    }

    public static ProdutoDTO controleProduto (String codigo) {
        ProdutoDTO produto = ProdutoService.buscarProduto(codigo);
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        return produto;
    }

}
