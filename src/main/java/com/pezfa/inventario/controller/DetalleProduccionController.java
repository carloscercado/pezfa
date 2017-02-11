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

    public DetalleProduccionController()
    {
        detalleproduccion = new DetalleProduccion(); //instancio el objeto almacen
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
        detalles = DetalleProduccionDB.read();
        return detalles;
    }

    public void register()
    {
        if (DetalleProduccionDB.create(detalleproduccion))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (DetalleProduccionDB.delete(detalleproduccion))
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
        if (DetalleProduccionDB.update(detalleproduccion))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
