package com.example.api_infuse_pedidos.controller;

import com.example.api_infuse_pedidos.model.Order;
import com.example.api_infuse_pedidos.usecase.OrderUseCase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderUseCase orderUseCase;

    public OrderController() {
    }

    @GetMapping({"/orders"})
    public ResponseEntity<?> buscarTodosPedidos() {
        return this.orderUseCase.buscarTodosPedidos();
    }

    @GetMapping({"/order-by-id/{idPedido}"})
    public ResponseEntity<?> buscarPedidoPorId(@PathVariable("idPedido") Integer idPedido) {
        return this.orderUseCase.buscarPedidoPorId(idPedido);
    }

    @GetMapping({"/order-by-data/{dataCadastro}"})
    public ResponseEntity<?> buscarPedidoPorData(@PathVariable("dataCadastro") String dataCadastro) {
        return this.orderUseCase.buscarPedidoPorData(dataCadastro);
    }

    @PostMapping(
            value = {"/new-order"},
            consumes = {"application/json", "application/xml"}
    )
    public ResponseEntity<String> cadastrarNovoPedido(@RequestBody List<Order> orders) {
        return this.orderUseCase.cadastrarNovoPedido(orders);
    }
}
