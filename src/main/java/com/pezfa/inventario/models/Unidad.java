package com.pezfa.inventario.models;

import java.util.HashSet;
import java.util.Set;

public class Unidad  implements java.io.Serializable {


     private int id;
     private Cava cava;
     private CompraEspecie compraEspecie;
     private double peso;
     private String codigo;
     private Boolean estado;
     private Set ventaUnidads = new HashSet(0);
     private Set detalleProduccions = new HashSet(0);

    public Unidad() {
    }

    public Unidad(int id, Cava cava, CompraEspecie compraEspecie, double peso, String codigo, Boolean estado)
    {
        this.id = id;
        this.cava = cava;
        this.compraEspecie = compraEspecie;
        this.peso = peso;
        this.codigo = codigo;
        this.estado = estado;
    }

    public Unidad(int id, Cava cava, double peso, String codigo, Boolean estado)
    {
        this.id = id;
        this.cava = cava;
        this.peso = peso;
        this.codigo = codigo;
        this.estado = estado;
    }

    
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Cava getCava()
    {
        return cava;
    }

    public void setCava(Cava cava)
    {
        this.cava = cava;
    }

    public CompraEspecie getCompraEspecie()
    {
        return compraEspecie;
    }

    public void setCompraEspecie(CompraEspecie compraEspecie)
    {
        this.compraEspecie = compraEspecie;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public Set getVentaUnidads()
    {
        return ventaUnidads;
    }

    public void setVentaUnidads(Set ventaUnidads)
    {
        this.ventaUnidads = ventaUnidads;
    }

    public Set getDetalleProduccions()
    {
        return detalleProduccions;
    }

    public void setDetalleProduccions(Set detalleProduccions)
    {
        this.detalleProduccions = detalleProduccions;
    }
    
    
	
}

