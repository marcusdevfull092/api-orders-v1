package com.example.api_infuse_pedidos.usecase.impl;

import com.example.api_infuse_pedidos.model.Order;
import com.example.api_infuse_pedidos.repository.MySqlRepository;
import com.example.api_infuse_pedidos.usecase.OrderUseCase;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderUseCaseImpl implements OrderUseCase {
    @Autowired
    MySqlRepository repository;

    public OrderUseCaseImpl() {
    }

    private boolean validaNumControleCadastrado(Integer numeroControle) {
        return !this.repository.existsById(numeroControle);
    }

    private boolean validaLimiteDePedidos(List<Order> itens) {
        return itens != null && itens.size() <= 10;
    }

    public ResponseEntity<String> cadastrarNovoPedido(List<Order> orders) {
        if (!this.validaLimiteDePedidos(orders)) {
            return new ResponseEntity("O n\u00famero de pedidos excede o limite de 10.", HttpStatus.BAD_REQUEST);
        } else {
            ResponseEntity<String> order = this.extrairPedidos(orders);
            if (order != null) {
                return order;
            } else {
                this.repository.saveAll(orders);
                return new ResponseEntity("Pedidos cadastrados com sucesso!", HttpStatus.OK);
            }
        }
    }

    private ResponseEntity<String> extrairPedidos(List<Order> orders) {
        Iterator var2 = orders.iterator();

        while(var2.hasNext()) {
            Order order = (Order)var2.next();
            if (!this.validaNumControleCadastrado(order.getIdPedido())) {
                return new ResponseEntity("N\u00famero de controle " + order.getIdPedido() + " j\u00e1 cadastrado.", HttpStatus.BAD_REQUEST);
            }

            this.calcularValorTotal(order);
        }

        return null;
    }

    public ResponseEntity<?> buscarTodosPedidos() {
        List<Order> pedidos = this.repository.findAll();
        return pedidos.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum pedido cadastrado") : ResponseEntity.ok(pedidos);
    }

    public ResponseEntity<?> buscarPedidoPorId(Integer idPedido) {
        Optional<Order> pedidos = this.repository.findById(idPedido);
        return pedidos.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum pedido cadastrado") : ResponseEntity.ok(pedidos);
    }

    public ResponseEntity<?> buscarPedidoPorData(String dataCadastro) {
        try {
            List<Order> pedidos = this.repository.buscarPorDataCadastro(dataCadastro);
            return pedidos.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum pedido cadastrado") : ResponseEntity.ok(pedidos);
        } catch (DateTimeParseException var3) {
            System.err.println("Formato de data inv\u00e1lido: " + var3.getMessage());
            return (ResponseEntity)ResponseEntity.badRequest();
        }
    }

    private void calcularValorTotal(Order order) {
        if (order.getQntProduto() == null) {
            order.setQntProduto(1);
        }

        double total = (double)order.getQntProduto() * order.getVlProduto();
        if (order.getQntProduto() > 10) {
            total *= 0.9;
        } else if (order.getQntProduto() > 5) {
            total *= 0.95;
        }

        order.setVlTotal(total);
        if (order.getDataCadastro() == null || order.getDataCadastro().isEmpty()) {
            order.setDataCadastro(LocalDate.now().toString());
        }

    }
}

