package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Cliente  implements java.io.Serializable {


     private int id;
     private String rif;
     private String nombre;
     private String direccion;
     private String telefono;
     private String correo;
     private Set ventas = new HashSet(0);

    public Cliente() {
    }

	
    public Cliente(String rif, String nombre, String direccion) {
        this.rif = rif;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Cliente(String rif, String nombre, String direccion, String telefono, String correo, Set ventas) {
       this.rif = rif;
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.correo = correo;
       this.ventas = ventas;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getRif() {
        return this.rif;
    }
    
    public void setRif(String rif) {
        this.rif = rif;
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
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
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
        final Cliente other = (Cliente) obj;
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


