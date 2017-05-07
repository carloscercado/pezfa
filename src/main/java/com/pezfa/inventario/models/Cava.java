package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
/**

 * @author Meritzabeth Yegres
 */
public class Cava  implements java.io.Serializable {


     private int id;
     private Almacen almacen;
     private String nombre;
     private Set ubicacions = new HashSet(0);
     private double capacidad;
     private double capacidadDisponible;

    public Cava() {
    }

    public Cava(int id, Almacen almacen, String nombre, double capacidad, double capacidadDisponible) {
        this.id = id;
        this.almacen = almacen;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.capacidadDisponible = capacidadDisponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set getUbicacions() {
        return ubicacions;
    }

    public void setUbicacions(Set ubicacions) {
        this.ubicacions = ubicacions;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public void toUpperCase()
    {
       this.nombre = this.nombre.toUpperCase();
    }
    
    public double getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(double capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cava other = (Cava) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    




}