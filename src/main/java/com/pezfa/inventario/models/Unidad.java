package com.pezfa.inventario.models;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Unidad generated by hbm2java
 */
public class Unidad  implements java.io.Serializable {


     private int id;
     private Cava cava;
     private CompraEspecie compraEspecie;
     private double peso;
     private BigDecimal precio;
     private String codigo;
     private Boolean estado;
     private Set ventaUnidads = new HashSet(0);
     private Set detalleProduccions = new HashSet(0);

    public Unidad() {
    }

	
    public Unidad(int id, Cava cava, CompraEspecie compraEspecie, double peso, String codigo) {
        this.id = id;
        this.cava = cava;
        this.compraEspecie = compraEspecie;
        this.peso = peso;
        this.codigo = codigo;
    }
    public Unidad(int id, Cava cava, CompraEspecie compraEspecie, double peso, BigDecimal precio, String codigo, Boolean estado, Set ventaUnidads, Set detalleProduccions) {
       this.id = id;
       this.cava = cava;
       this.compraEspecie = compraEspecie;
       this.peso = peso;
       this.precio = precio;
       this.codigo = codigo;
       this.estado = estado;
       this.ventaUnidads = ventaUnidads;
       this.detalleProduccions = detalleProduccions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Cava getCava() {
        return this.cava;
    }
    
    public void setCava(Cava cava) {
        this.cava = cava;
    }
    public CompraEspecie getCompraEspecie() {
        return this.compraEspecie;
    }
    
    public void setCompraEspecie(CompraEspecie compraEspecie) {
        this.compraEspecie = compraEspecie;
    }
    public double getPeso() {
        return this.peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Set getVentaUnidads() {
        return this.ventaUnidads;
    }
    
    public void setVentaUnidads(Set ventaUnidads) {
        this.ventaUnidads = ventaUnidads;
    }
    public Set getDetalleProduccions() {
        return this.detalleProduccions;
    }
    
    public void setDetalleProduccions(Set detalleProduccions) {
        this.detalleProduccions = detalleProduccions;
    }




}


