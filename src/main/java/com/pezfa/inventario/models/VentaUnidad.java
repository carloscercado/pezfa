package com.pezfa.inventario.models;

/**
 * VentaUnidad generated by hbm2java
 */
public class VentaUnidad  implements java.io.Serializable {


     private int id;
     private Unidad unidad;
     private Venta venta;

    public VentaUnidad() {
    }

    public VentaUnidad(int id, Unidad unidad, Venta venta) {
       this.id = id;
       this.unidad = unidad;
       this.venta = venta;
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
    public Venta getVenta() {
        return this.venta;
    }
    
    public void setVenta(Venta venta) {
        this.venta = venta;
    }




}


