package com.pezfa.inventario.models;

/**
 *
 * @author Carlos Cercado
 * @email cercadocarlos@gmail.com
 */
public class VentaDetalle 
{
    private Venta venta;
    private float cantidad;
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

    public float getCantidad()
    {
        return cantidad;
    }

    public void setCantidad(float cantidad)
    {
        this.cantidad = cantidad;
    }
    

    
}
