package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1

public class VentaUnidad extends VentaDetalle implements java.io.Serializable {


     private int id;
     private Unidad unidad;

    public VentaUnidad() {
    }

    public VentaUnidad(Unidad unidad, Venta venta) {
       this.unidad = unidad;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Unidad getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

}


