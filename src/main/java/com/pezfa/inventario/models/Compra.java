package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Compra  implements java.io.Serializable {


     private int id;
     private Proveedor proveedor;
     private Usuario usuario;
     private String orden;
     private Date fecha;
     private String estado;
     private BigDecimal gasto;
     private Empleado chofer;
     private Camion camion;
     private Set compraEspecies = new HashSet(0);

    public Compra() {
    }

    public Compra(int id, Proveedor proveedor, Usuario usuario, String orden, Date fecha, String estado, BigDecimal gasto, Empleado chofer, Camion camion)
    {
        this.id = id;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.orden = orden;
        this.fecha = fecha;
        this.estado = estado;
        this.gasto = gasto;
        this.chofer = chofer;
        this.camion = camion;
    }

	
    
    public Empleado getChofer()
    {
        return chofer;
    }

    public void setChofer(Empleado chofer)
    {
        this.chofer = chofer;
    }

    public Camion getCamion()
    {
        return camion;
    }

    public void setCamion(Camion camion)
    {
        this.camion = camion;
    }
   
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Proveedor getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String getOrden() {
        return this.orden;
    }
    
    public void setOrden(String orden) {
        this.orden = orden;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public BigDecimal getGasto() {
        return this.gasto;
    }
    
    public void setGasto(BigDecimal gasto) {
        this.gasto = gasto;
    }
    public Set getCompraEspecies() {
        return this.compraEspecies;
    }
    
    public void setCompraEspecies(Set compraEspecies) {
        this.compraEspecies = compraEspecies;
    }




}


