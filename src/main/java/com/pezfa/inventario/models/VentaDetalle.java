package com.pezfa.inventario.models;

/**
 *
 * @author Carlos Cercado
 * @email cercadocarlos@gmail.com
 */
public class VentaDetalle 
{
    private Venta venta;
    private int cantidad;
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

    public int getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(int cantidad)
    {
        this.cantidad = cantidad;
    }
    

    
}
