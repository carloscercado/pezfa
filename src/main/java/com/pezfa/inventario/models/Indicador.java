/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.models;

import java.util.HashSet;
import java.util.Set;

public class Indicador implements java.io.Serializable {

    private int id;
    private String nombre;
    private double bueno;
    private double malo;
    private String mensajeBueno;
    private String mensajeMalo;
    private String mensajeAceptable;
    private String descripcion;

    public Indicador() {
    }

    public Indicador(int id, String nombre, double bueno, double malo, String mensajeBueno, String mensajeMalo, String mensajeAceptable, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.bueno = bueno;
        this.malo = malo;
        this.mensajeBueno = mensajeBueno;
        this.mensajeMalo = mensajeMalo;
        this.mensajeAceptable = mensajeAceptable;
        this.descripcion = descripcion;
    }

    public Indicador(int id, String nombre, double bueno, double malo, String mensajeBueno, String mensajeMalo, String mensajeAceptable) {
        this.id = id;
        this.nombre = nombre;
        this.bueno = bueno;
        this.malo = malo;
        this.mensajeBueno = mensajeBueno;
        this.mensajeMalo = mensajeMalo;
        this.mensajeAceptable = mensajeAceptable;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getBueno() {
        return bueno;
    }

    public void setBueno(double bueno) {
        this.bueno = bueno;
    }

    public double getMalo() {
        return malo;
    }

    public void setMalo(double malo) {
        this.malo = malo;
    }

    public String getMensajeBueno() {
        return mensajeBueno;
    }

    public void setMensajeBueno(String mensajeBueno) {
        this.mensajeBueno = mensajeBueno;
    }

    public String getMensajeMalo() {
        return mensajeMalo;
    }

    public void setMensajeMalo(String mensajeMalo) {
        this.mensajeMalo = mensajeMalo;
    }

    public String getMensajeAceptable() {
        return mensajeAceptable;
    }

    public void setMensajeAceptable(String mensajeAceptable) {
        this.mensajeAceptable = mensajeAceptable;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
