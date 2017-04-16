package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaDB;
import com.pezfa.inventario.models.Venta;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Romario Guerrero
 */
@ManagedBean
@ViewScoped
public class VentaController implements Serializable
{
    private Venta venta = null; // objeto a controlar
    private List<Venta> ventas = null; // lista de objetos de tipo ventas
    private VentaDB db;

    //constructor
    public VentaController()
    {
        venta = new Venta(); //instancio el objeto venta
        db = new VentaDB();
    }

    //getter y setter
    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public List<Venta> getVentas()
    {
        ventas = db.read("from Venta");
        return ventas;
    }

    public void setVentas(List<Venta> ventas)
    {
        this.ventas = ventas;
    }

    //logica para registrar un venta
    public void register()
    {
        if (db.create(venta))
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
        if (db.delete(venta))
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
        if (db.update(venta))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
