package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.models.Unidad;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class TerminadoController implements Serializable
{

    Unidad terminado = null; // objeto a controlar
    List<Unidad> terminados = null; // lista de objetos de tipo almace
    private UnidadDB db;

    //constructor
    public TerminadoController()
    {
        terminado = new Unidad(); //instancio el objeto almacen
        db = new UnidadDB();
    }

    //getter y setter
    public Unidad getTerminado()
    {
        return terminado;
    }

    public void setTerminado(Unidad terminado)
    {
        this.terminado = terminado;
    }

    public List<Unidad> getTerminados()
    {
        terminados = db.read("from Terminado");
        return terminados;
    }

    public void setTerminados(List<Unidad> terminados)
    {
        this.terminados = terminados;
    }

    public void register()
    {
        if (db.create(terminado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (db.delete(terminado))
        {
            System.out.println("No Eliminado");
        } else
        {
            System.out.println("Eliminado");
        }
    }

    public void update()
    {
        if (db.update(terminado))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}

