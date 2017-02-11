package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaDB;
import com.pezfa.inventario.models.Venta;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adela Hernandez
 */
public class VentaController implements Serializable
{
    private Venta venta = null; // objeto a controlar
    private List<Venta> ventas = null; // lista de objetos de tipo ventas

    //constructor
    public VentaController()
    {
        venta = new Venta(); //instancio el objeto venta
    }

    //getter y setter
    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta()
    {
        this.venta = venta;
    }

    public List<Venta> getVentas()
    {
        ventas = VentaDB.read();
        return ventas;
    }

    public void setVentas(List<Venta> ventas)
    {
        this.ventas = ventas;
    }

    //logica para registrar un venta
    public void register()
    {
        if (VentaDB.create(venta))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para eliminar un venta
    public void delete()
    {
        if (VentaDB.delete(venta))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un venta
    public void update()
    {
        if (VentaDB.update(venta))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
