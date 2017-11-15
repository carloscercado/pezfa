package com.pezfa.inventario.models;

import java.math.BigDecimal;

public class VentaEspecie extends VentaDetalle  implements java.io.Serializable {


     private int id;
     private Ubicacion ubicacion;
     private BigDecimal precio;

    public VentaEspecie() {
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}


