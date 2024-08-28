package com.example.api_infuse_pedidos.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_orders")
@JacksonXmlRootElement(localName = "order")
public class Order {
    @Id
    @Column(
            name = "id_pedido"
    )
    private Integer idPedido;
    @Column(
            name = "data_cadastro"
    )
    private String dataCadastro;
    @Column(
            name = "nome_produto"
    )
    private String nomeProduto;
    @Column(
            name = "vl_produto"
    )
    private Double vlProduto;
    @Column(
            name = "qnt_produto"
    )
    private Integer qntProduto;
    @Column(
            name = "cod_cliente"
    )
    private Integer codCliente;
    @Column(
            name = "vl_total"
    )
    private Double vlTotal;

    public Order() {
    }

    public Order(Integer idPedido, String dataCadastro, String nomeProduto, Double vlProduto, Integer qntProduto, Integer codCliente, Double vlTotal) {
        this.idPedido = idPedido;
        this.dataCadastro = dataCadastro;
        this.nomeProduto = nomeProduto;
        this.vlProduto = vlProduto;
        this.qntProduto = qntProduto;
        this.codCliente = codCliente;
        this.vlTotal = vlTotal;
    }

    public Integer getIdPedido() {
        return this.idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQntProduto() {
        return this.qntProduto;
    }

    public void setQntProduto(Integer qntProduto) {
        this.qntProduto = qntProduto;
    }

    public Integer getCodCliente() {
        return this.codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Double getVlProduto() {
        return this.vlProduto;
    }

    public void setVlProduto(Double vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Double getVlTotal() {
        return this.vlTotal;
    }

    public void setVlTotal(Double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String toString() {
        return "Order{idPedido=" + this.idPedido + ", dataCadastro='" + this.dataCadastro + "', nomeProduto='" + this.nomeProduto + "', vlProduto=" + this.vlProduto + ", qntProduto=" + this.qntProduto + ", codCliente=" + this.codCliente + ", vlTotal=" + this.vlTotal + "}";
    }
}

