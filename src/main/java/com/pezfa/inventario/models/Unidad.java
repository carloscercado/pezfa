package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Unidad extends ProductoSalida implements java.io.Serializable {


     private int id;
     private Produccion produccion;
     private Producto producto;
     private Date vencimiento;
     private String lote;
     private Boolean estado;
     private Set ventaUnidads = new HashSet(0);

    public Unidad() {
    }

	
    public Unidad(Produccion produccion, Producto producto, Date vencimiento, String lote) {
        this.produccion = produccion;
        this.producto = producto;
        this.vencimiento = vencimiento;
        this.lote = lote;
    }
    public Unidad(Produccion produccion, Producto producto, Date vencimiento, String lote, Boolean estado, Set ventaUnidads) {
       this.produccion = produccion;
       this.producto = producto;
       this.vencimiento = vencimiento;
       this.lote = lote;
       this.estado = estado;
       this.ventaUnidads = ventaUnidads;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Produccion getProduccion() {
        return this.produccion;
    }
    
    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Date getVencimiento() {
        return this.vencimiento;
    }
    
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    public String getLote() {
        return this.lote;
    }
    
    public void setLote(String lote) {
        this.lote = lote;
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




}


