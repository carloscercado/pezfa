package com.pezfa.inventario.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Venta generated by hbm2java
 */
public class Venta  implements java.io.Serializable {


     private int id;
     private Cliente cliente;
     private Usuario usuario;
     private String factura;
     private Date fecha;
     private Date hora;
     private BigDecimal ingreso;
     private Set ventaTerminados = new HashSet(0);
     private Set ventaUnidads = new HashSet(0);

    public Venta() {
    }

	
    public Venta(int id, Cliente cliente, Usuario usuario, String factura) {
        this.id = id;
        this.cliente = cliente;
        this.usuario = usuario;
        this.factura = factura;
    }
    public Venta(int id, Cliente cliente, Usuario usuario, String factura, Date fecha, Date hora, BigDecimal ingreso, Set ventaTerminados, Set ventaUnidads) {
       this.id = id;
       this.cliente = cliente;
       this.usuario = usuario;
       this.factura = factura;
       this.fecha = fecha;
       this.hora = hora;
       this.ingreso = ingreso;
       this.ventaTerminados = ventaTerminados;
       this.ventaUnidads = ventaUnidads;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getFactura() {
        return this.factura;
    }
    
    public void setFactura(String factura) {
        this.factura = factura;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public BigDecimal getIngreso() {
        return this.ingreso;
    }
    
    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }
    public Set getVentaTerminados() {
        return this.ventaTerminados;
    }
    
    public void setVentaTerminados(Set ventaTerminados) {
        this.ventaTerminados = ventaTerminados;
    }
    public Set getVentaUnidads() {
        return this.ventaUnidads;
    }
    
    public void setVentaUnidads(Set ventaUnidads) {
        this.ventaUnidads = ventaUnidads;
    }




}

