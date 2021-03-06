package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1

public class DetalleProduccion  implements java.io.Serializable {


     private int id;
     private Produccion produccion;
     private Ubicacion ubicacion;
     private double cantidad;

    public DetalleProduccion() {
    }

    public DetalleProduccion(Produccion produccion, Ubicacion ubicacion, double cantidad) {
       this.produccion = produccion;
       this.ubicacion = ubicacion;
       this.cantidad = cantidad;
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
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    public double getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }




}


