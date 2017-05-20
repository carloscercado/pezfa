package com.pezfa.inventario.models;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductoSalida 
{
    private int id;
    private String codigo;
    private String nombre;
    private BigDecimal precio;
    
    public ProductoSalida()
    {
        
    }

    public ProductoSalida(int id, String codigo, String nombre, BigDecimal precio)
    {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCodigo()
    {
        return codigo;
    }

    public void setCodigo(String codigo)
    {
        this.codigo = codigo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio()
    {
        return precio;
    }

    public void setPrecio(BigDecimal precio)
    {
        this.precio = precio;
    }    
    
    public void toUpperCase(String nombre)
    {
       this.nombre = this.nombre.toUpperCase();
    }
    
}
