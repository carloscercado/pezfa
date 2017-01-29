package com.pezfa.inventario.models;
// Generated 28-ene-2017 17:44:34 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private int id;
     private String codigo;
     private String nombre;
     private Integer cantidad;
     private Integer maximo;
     private Integer minimo;
     private String categoria;
     private BigDecimal precio;
     private Set terminados = new HashSet(0);

    public Producto() {
    }

	
    public Producto(int id, String codigo, String nombre, String categoria, BigDecimal precio) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }
    public Producto(int id, String codigo, String nombre, Integer cantidad, Integer maximo, Integer minimo, String categoria, BigDecimal precio, Set terminados) {
       this.id = id;
       this.codigo = codigo;
       this.nombre = nombre;
       this.cantidad = cantidad;
       this.maximo = maximo;
       this.minimo = minimo;
       this.categoria = categoria;
       this.precio = precio;
       this.terminados = terminados;
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
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Integer getMaximo() {
        return this.maximo;
    }
    
    public void setMaximo(Integer maximo) {
        this.maximo = maximo;
    }
    public Integer getMinimo() {
        return this.minimo;
    }
    
    public void setMinimo(Integer minimo) {
        this.minimo = minimo;
    }
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Set getTerminados() {
        return this.terminados;
    }
    
    public void setTerminados(Set terminados) {
        this.terminados = terminados;
    }




}


