package com.pezfa.inventario.models;

import java.util.HashSet;
import java.util.Set;

/**
 * Almacen generated by hbm2java
 */
public class Almacen  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String direccion;
     private String telefono;
     private Set cavas = new HashSet(0);

    public Almacen() {
    }

	
    public Almacen(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Almacen(int id, String nombre, String direccion, String telefono, Set cavas) {
       this.id = id;
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.cavas = cavas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public Set getCavas() {
        return this.cavas;
    }
    
    public void setCavas(Set cavas) {
        this.cavas = cavas;
    }




}


