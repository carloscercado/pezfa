package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.DetalleProduccionDB;
import com.pezfa.inventario.models.DetalleProduccion;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean; 
import javax.faces.bean.ViewScoped;
@ManagedBean
@ViewScoped
public class DetalleProduccionController implements Serializable
{

    DetalleProduccion detalleproduccion = null; // objeto a controlar
    List<DetalleProduccion> detalles = null; // lista de objetos de tipo almace
    private DetalleProduccionDB db;

    public DetalleProduccionController()
    {
        detalleproduccion = new DetalleProduccion(); //instancio el objeto almacen
        db = new DetalleProduccionDB();
    }

    public DetalleProduccion getDetalleProduccion()
    {
        return detalleproduccion;
    }

    public void setDetalles(DetalleProduccion detalleproduccion)
    {
        this.detalleproduccion = detalleproduccion;
    }

    public List<DetalleProduccion> getDetalles()
    {
        detalles = db.read("from DetalleProduccion");
        return detalles;
    }

    public void register()
    {
        if (db.create(detalleproduccion))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (db.delete(detalleproduccion))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un almacen
    public void update()
    {
        if (db.update(detalleproduccion))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
