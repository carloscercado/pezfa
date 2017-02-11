package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AlmacenDB;
import com.pezfa.inventario.models.Almacen;
import java.io.Serializable;
import java.util.List;

public class AlmacenController implements Serializable
{

    Almacen almacen = null; // objeto a controlar
    List<Almacen> almacenes = null; // lista de objetos de tipo almace

    //constructor
    public AlmacenController()
    {
        almacen = new Almacen(); //instancio el objeto almacen
        //almacen = null; // igualo a null
    }

    //getter y setter
    public Almacen getAlmacen()
    {
        return almacen;
       
    }

    public void setAlmacen(Almacen almacenes)
    {
        this.almacen = almacen;
    }

    public List<Almacen> getAlmacenes()
    {
        almacenes = AlmacenDB.read();
        return almacenes;
    }

    public void setAlmacenes(List almacenes)
    {
        this.almacenes = (List<Almacen>) almacenes;
    }

    //logica para registrar un almacen
    public void register()
    {
        if (AlmacenDB.create(almacen))
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
        if (AlmacenDB.delete(almacen))
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
        if (AlmacenDB.update(almacen))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
