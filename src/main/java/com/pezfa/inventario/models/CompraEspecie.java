package com.pezfa.inventario.models;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * CompraEspecie generated by hbm2java
 */
public class CompraEspecie  implements java.io.Serializable {


     private int id;
     private Compra compra;
     private Especie especie;
     private int cantidad;
     private BigDecimal costo;
     private Set unidads = new HashSet(0);

    public CompraEspecie() {
    }

	
    public CompraEspecie(int id, Compra compra, Especie especie, int cantidad) {
        this.id = id;
        this.compra = compra;
        this.especie = especie;
        this.cantidad = cantidad;
    }
    public CompraEspecie(int id, Compra compra, Especie especie, int cantidad, BigDecimal costo, Set unidads) {
       this.id = id;
       this.compra = compra;
       this.especie = especie;
       this.cantidad = cantidad;
       this.costo = costo;
       this.unidads = unidads;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Compra getCompra() {
        return this.compra;
    }
    
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    public Especie getEspecie() {
        return this.especie;
    }
    
    public void setEspecie(Especie especie) {
        this.especie = especie;
    }
    public int getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public BigDecimal getCosto() {
        return this.costo;
    }
    
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    public Set getUnidads() {
        return this.unidads;
    }
    
    public void setUnidads(Set unidads) {
        this.unidads = unidads;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 47 * hash + (this.especie != null ? this.especie.hashCode() : 0);
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
        final CompraEspecie other = (CompraEspecie) obj;
        if (this.especie != other.especie && (this.especie == null || !this.especie.equals(other.especie)))
        {
            return false;
        }
        return true;
    }

       


}


