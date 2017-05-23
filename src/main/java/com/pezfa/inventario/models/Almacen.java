package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Almacen  implements java.io.Serializable {


     private int id;
     private String nombre;
     private String direccion;
     private String telefono;
     private Set cavas = new HashSet(0);

    public Almacen() {
    }

	
    public Almacen(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Almacen(String nombre, String direccion, String telefono, Set cavas) {
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

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 71 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Almacen other = (Almacen) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }

   public void toUpperCase()
    {
       this.nombre = this.nombre.toUpperCase();
       this.direccion = this.direccion.toUpperCase();
    }
   
}


