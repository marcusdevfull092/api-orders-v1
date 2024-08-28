package com.example.api_infuse_pedidos.usecase;

import com.example.api_infuse_pedidos.model.Order;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface OrderUseCase {
    default ResponseEntity<String> cadastrarNovoPedido(List<Order> orders) {
        return ResponseEntity.ok("M\u00e9todo n\u00e3o implementado.");
    }

    ResponseEntity<?> buscarTodosPedidos();

    ResponseEntity<?> buscarPedidoPorId(Integer idPedido);

    ResponseEntity<?> buscarPedidoPorData(String dataCadastro);
}

