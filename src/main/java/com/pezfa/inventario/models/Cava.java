package com.pezfa.inventario.models;
// Generated 28/01/2017 05:34:12 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Cava generated by hbm2java
 */
public class Cava  implements java.io.Serializable {


     private int id;
     private Almacen almacen;
     private String nombre;
     private Set unidads = new HashSet(0);

    public Cava() {
    }

	
    public Cava(int id, Almacen almacen, String nombre) {
        this.id = id;
        this.almacen = almacen;
        this.nombre = nombre;
    }
    public Cava(int id, Almacen almacen, String nombre, Set unidads) {
       this.id = id;
       this.almacen = almacen;
       this.nombre = nombre;
       this.unidads = unidads;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Almacen getAlmacen() {
        return this.almacen;
    }
    
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Set getUnidads() {
        return this.unidads;
    }
    
    public void setUnidads(Set unidads) {
        this.unidads = unidads;
    }




}


