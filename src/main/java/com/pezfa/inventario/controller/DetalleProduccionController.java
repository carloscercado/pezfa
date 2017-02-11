package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.DetalleProduccionDB;
import com.pezfa.inventario.models.DetalleProduccion;
import java.io.Serializable;
import java.util.List;

public class DetalleProduccionController implements Serializable
{

    DetalleProduccion detalleproduccion = null; // objeto a controlar
    List<DetalleProduccion> dp = null; // lista de objetos de tipo almace

    //constructor
    public DetalleProduccionController()
    {
        detalleproduccion = new DetalleProduccion(); //instancio el objeto almacen
    }

    //getter y setter
    public DetalleProduccion getDetalleProdccion()
    {
        return detalleproduccion;
    }

    public void setDetalleProduccion(DetalleProduccion detalleproduccion)
    {
        this.detalleproduccion = detalleproduccion;
    }

    public List<DetalleProduccion> getDetalleProduccion()
    {
        dp = DetalleProduccionDB.read();
        return dp;
    }

    public void setAlmacenes(List<DetalleProduccion> detalleproduccion)
    {
        this.dp = detalleproduccion;
    }

    //logica para registrar un almacen
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

    //logica para eliminar un almacen
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
