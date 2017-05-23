package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.Date;

public class Auditoria  implements java.io.Serializable {


     private int id;
     private Usuario usuario;
     private Date fecha;
     private Date hora;
     private String tipo;
     private String descripcion;

    public Auditoria() {
    }

	
    public Auditoria(Usuario usuario, String tipo, String descripcion) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    public Auditoria(Usuario usuario, Date fecha, Date hora, String tipo, String descripcion) {
       this.usuario = usuario;
       this.fecha = fecha;
       this.hora = hora;
       this.tipo = tipo;
       this.descripcion = descripcion;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getHora() {
        return this.hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    public String getTipo() {
        return this.tipo;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


