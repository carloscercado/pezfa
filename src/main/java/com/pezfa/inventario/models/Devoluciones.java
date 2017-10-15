package com.pezfa.inventario.models;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Romario
 */
public class Devoluciones {

    private int id;
    private int anio;
    private int mes;
    private int devoluciones;
    private int ventas;
    private Map meses;

    public Devoluciones() {
        meses = new HashMap();
        meses.put(1, "Ene");
        meses.put(2, "Feb");
        meses.put(3, "Mar");
        meses.put(4, "Abr");
        meses.put(5, "May");
        meses.put(6, "Jun");
        meses.put(7, "Jul");
        meses.put(8, "Ago");
        meses.put(9, "Sep");
        meses.put(10, "Oct");
        meses.put(11, "Nov");
        meses.put(12, "Dic");
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

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }
    
    public String getMesString()
    {
        return this.meses.get(this.mes).toString();
    }
    

}
