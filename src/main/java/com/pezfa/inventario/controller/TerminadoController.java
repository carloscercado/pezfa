package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.TerminadoDB;
import com.pezfa.inventario.models.Terminado;
import java.io.Serializable;
import java.util.List;

public class TerminadoController implements Serializable
{

    Terminado terminado = null; // objeto a controlar
    List<Terminado> terminados = null; // lista de objetos de tipo almace
    private Object TermiandoDB;

    //constructor
    public TerminadoController()
    {
        terminado = new Terminado(); //instancio el objeto almacen
    }

    //getter y setter
    public Terminado getTerminado()
    {
        return terminado;
    }

    public void setTerminado(Terminado terminado)
    {
        this.terminado = terminado;
    }

    public List<Terminado> getTerminados()
    {
        terminados = TerminadoDB.read();
        return terminados;
    }

    public void setTerminado(List<Terminado> terminado)
    {
        this.terminados = terminado;
    }

    //logica para registrar un almacen
    public void register()
    {
        if (TerminadoDB.create(terminado))
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
        if (TerminadoDB.delete(terminado))
        {
            System.out.println("No Eliminado");
        } else
        {
            System.out.println("Eliminado");
        }
    }

    //logica para actualizar un almacen
    public void update()
    {
        if (TerminadoDB.update(terminado))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}

