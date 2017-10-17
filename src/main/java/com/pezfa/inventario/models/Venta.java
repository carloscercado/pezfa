package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Venta implements java.io.Serializable {

    private int id;
    private Cliente cliente;
    private Usuario usuario;
    private String factura;
    private Date fecha;
    private BigDecimal ingreso;
    private boolean devuelta;
    private double kiloTotal;
    private Set ventaEspecies = new HashSet(0);
    private Set ventaUnidads = new HashSet(0);

    public Venta() {
    }

    public double getKiloTotal() {
        return kiloTotal;
    }

    public void setKiloTotal(double kiloTotal) {
        this.kiloTotal = kiloTotal;
    }

    public Venta(Cliente cliente, Usuario usuario, String factura) {
        this.cliente = cliente;
        this.usuario = usuario;
        this.factura = factura;
    }

    public Venta(Cliente cliente, Usuario usuario, String factura, Date fecha, BigDecimal ingreso, Set ventaEspecies, Set ventaUnidads) {
        this.cliente = cliente;
        this.usuario = usuario;
        this.factura = factura;
        this.fecha = fecha;
        this.ingreso = ingreso;
        this.ventaEspecies = ventaEspecies;
        this.ventaUnidads = ventaUnidads;
    }

    public int getId() {
        return this.id;
    }

    public boolean isDevuelta() {
        return devuelta;
    }

    public void setDevuelta(boolean devuelta) {
        this.devuelta = devuelta;
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

    public BigDecimal getIngreso() {
        return this.ingreso;
    }

    public void setIngreso(BigDecimal ingreso) {
        this.ingreso = ingreso;
    }

    public Set getVentaEspecies() {
        return this.ventaEspecies;
    }

    public void setVentaEspecies(Set ventaEspecies) {
        this.ventaEspecies = ventaEspecies;
    }

    public Set getVentaUnidads() {
        return this.ventaUnidads;
    }

    public void setVentaUnidads(Set ventaUnidads) {
        this.ventaUnidads = ventaUnidads;
    }

}
