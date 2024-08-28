package com.example.api_infuse_pedidos.repository;

import com.example.api_infuse_pedidos.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MySqlRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.dataCadastro = :dataCadastro")
    List<Order> buscarPorDataCadastro(@Param("dataCadastro") String dataCadastro);
}
