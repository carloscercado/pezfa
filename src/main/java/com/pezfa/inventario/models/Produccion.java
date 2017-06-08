package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Produccion  implements java.io.Serializable {


     private int id;
     private String descripcion;
     private Date fecha;
     private BigDecimal inversion;
     private Set unidads = new HashSet(0);
     private Set detalleProduccions = new HashSet(0);

    public Produccion() {
    }

	
    public Produccion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Produccion(String descripcion, Date fecha, BigDecimal inversion, Set unidads, Set detalleProduccions) {
       this.descripcion = descripcion;
       this.fecha = fecha;
       this.inversion = inversion;
       this.unidads = unidads;
       this.detalleProduccions = detalleProduccions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public BigDecimal getInversion() {
        return this.inversion;
    }
    
    public void setInversion(BigDecimal inversion) {
        this.inversion = inversion;
    }
    public Set getUnidads() {
        return this.unidads;
    }
    
    public void setUnidads(Set unidads) {
        this.unidads = unidads;
    }
    public Set getDetalleProduccions() {
        return this.detalleProduccions;
    }
    
    public void setDetalleProduccions(Set detalleProduccions) {
        this.detalleProduccions = detalleProduccions;
    }

    public void toUpperCase()
    {
       this.descripcion = this.descripcion.toUpperCase();
    }


}


