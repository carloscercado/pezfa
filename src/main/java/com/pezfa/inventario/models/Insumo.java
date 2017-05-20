package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1

/**
 * 
 */
public class Insumo  implements java.io.Serializable {


     private int id;
     private String codigo;
     private String nombre;
     private Double cantidad;
     private String medida;
     private Double minimo;
     private String tipo;
    

    public Insumo() {
    }

    public Insumo(int id, String codigo, String nombre, Double cantidad, String medida, Double minimo, String tipo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.medida = medida;
        this.minimo = minimo;
        this.tipo = tipo;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Double getMinimo() {
        return minimo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

  


    
}


