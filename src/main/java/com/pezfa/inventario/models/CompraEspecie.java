package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CompraEspecie  implements java.io.Serializable {


     private int id;
     private Compra compra;
     private Especie especie;
     private double cantidad;
     private BigDecimal costo;
     private Double ubicados;
     private Set ubicacions = new HashSet(0);

    public CompraEspecie() {
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public CompraEspecie(int id, Compra compra, Especie especie, double cantidad, BigDecimal costo, Double ubicados) {
        this.id = id;
        this.compra = compra;
        this.especie = especie;
        this.cantidad = cantidad;
        this.costo = costo;
        this.ubicados = ubicados;
    }
   
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Compra getCompra() {
        return this.compra;
    }
    
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public Especie getEspecie() {
        return this.especie;
    }
    
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    
    public BigDecimal getCosto() {
        return this.costo;
    }
    
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    public Double getUbicados() {
        return this.ubicados;
    }
    
    public void setUbicados(Double ubicados) {
        this.ubicados = ubicados;
    }
    public Set getUbicacions() {
        return this.ubicacions;
    }
    
    public void setUbicacions(Set ubicacions) {
        this.ubicacions = ubicacions;
    }




}


