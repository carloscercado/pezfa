package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AlmacenDB;
import com.pezfa.inventario.models.Almacen;
import java.io.Serializable;
import java.util.List;

public class AlmacenController implements Serializable
{

    private Almacen almacen = null; // objeto a controlar
    private List<Almacen> almacenes = null; // lista de objetos de tipo almace

    //constructor
    public AlmacenController()
    {
        almacen = new Almacen(); //instancio el objeto almacen
    }

    //getter y setter
    public Almacen getAlmacen()
    {
        this.almacen = almacen;
        return almacen;
    }

    public Almacen setAlmacen()
    {
        this.almacen = almacen;
        return almacen;
    }

    public List<Almacen> getAlmacenes()
    {
        almacenes = AlmacenDB.read();
        return almacenes;
    }

    public void setAlmacenes()
    {
        this.almacenes = almacenes;
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
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
