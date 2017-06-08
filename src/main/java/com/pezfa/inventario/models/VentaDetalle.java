package com.pezfa.inventario.models;

public class VentaDetalle 
{
    private Venta venta;
    private double cantidad;
    public VentaDetalle()
    {
        
    }

    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(double cantidad)
    {
        this.cantidad = cantidad;
    }
    

    
}
