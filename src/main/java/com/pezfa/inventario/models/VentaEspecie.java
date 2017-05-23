package com.pezfa.inventario.models;

public class VentaEspecie extends VentaDetalle  implements java.io.Serializable {


     private int id;
     private Ubicacion ubicacion;

    public VentaEspecie() {
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }
    
    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
    
}


