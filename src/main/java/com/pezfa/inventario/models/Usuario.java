package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Usuario  implements java.io.Serializable {


     private int id;
     private Empleado empleado;
     private String usuario;
     private String clave;
     private String rol;
     private Set auditorias = new HashSet(0);
     private Set ventas = new HashSet(0);
     private Set compras = new HashSet(0);

    public Usuario() {
    }

	
    public Usuario(Empleado empleado, String usuario, String clave, String rol) {
        this.empleado = empleado;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }
    public Usuario(Empleado empleado, String usuario, String clave, String rol, Set auditorias, Set ventas, Set compras) {
       this.empleado = empleado;
       this.usuario = usuario;
       this.clave = clave;
       this.rol = rol;
       this.auditorias = auditorias;
       this.ventas = ventas;
       this.compras = compras;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getRol() {
        return this.rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
    public Set getAuditorias() {
        return this.auditorias;
    }
    
    public void setAuditorias(Set auditorias) {
        this.auditorias = auditorias;
    }
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }
    public Set getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set compras) {
        this.compras = compras;
    }

    public void toUpperCase()
    {
       this.rol = this.rol.toUpperCase();
    }

}


