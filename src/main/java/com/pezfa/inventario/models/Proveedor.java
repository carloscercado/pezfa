package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Proveedor  implements java.io.Serializable {


     private int id;
     private String rif;
     private String nombre;
     private String direccion;
     private String telefono;
     private String correo;
     private Set compras = new HashSet(0);

    public Proveedor() {
    }

	
    public Proveedor(String rif, String nombre, String direccion) {
        this.rif = rif;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Proveedor(String rif, String nombre, String direccion, String telefono, String correo, Set compras) {
       this.rif = rif;
       this.nombre = nombre;
       this.direccion = direccion;
       this.telefono = telefono;
       this.correo = correo;
       this.compras = compras;
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
    public Set getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set compras) {
        this.compras = compras;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 83 * hash + this.id;
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
        final Proveedor other = (Proveedor) obj;
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


