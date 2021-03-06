package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Especie  implements java.io.Serializable {


     private int id;
     private String codigo;
     private String nombre;
     private Double cantidad;
     private Double maximo;
     private Double minimo;
     private String tipo;
     private BigDecimal precio;
     private Set compraEspecies = new HashSet(0);

    public Especie() {
    }

	
    public Especie(String codigo, String nombre, String tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public Especie(String codigo, String nombre, Double cantidad, Double maximo, Double minimo, String tipo, BigDecimal precio, Set compraEspecies) {
       this.codigo = codigo;
       this.nombre = nombre;
       this.cantidad = cantidad;
       this.maximo = maximo;
       this.minimo = minimo;
       this.tipo = tipo;
       this.precio = precio;
       this.compraEspecies = compraEspecies;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    public Double getMaximo() {
        return this.maximo;
    }
    
    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }
    public Double getMinimo() {
        return this.minimo;
    }
    
    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Set getCompraEspecies() {
        return this.compraEspecies;
    }
    
    public void setCompraEspecies(Set compraEspecies) {
        this.compraEspecies = compraEspecies;
    }

    public void toUpperCase()
    {
       this.nombre = this.nombre.toUpperCase();
       this.tipo = this.tipo.toUpperCase();
    }
}


