/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.models;

/**
 *
 * @author yulitza
 */
public class Devoluciones implements java.io.Serializable {

    private int id;
    private int anio;
    private int mes;
    private int devoluciones;
    private int ventas;

    public Devoluciones(int anio, int mes, int devoluciones, int ventas) {
        this.anio = anio;
        this.mes = mes;
        this.devoluciones = devoluciones;
        this.ventas = ventas;
    }

   

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDevoluciones() {
        return devoluciones;
    }

    public void setDevoluciones(int devoluciones) {
        this.devoluciones = devoluciones;
    }

}
