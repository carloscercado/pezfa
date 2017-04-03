package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.models.Unidad;
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
public class UnidadController implements Serializable
{

    private Unidad unidad = null; // objeto a controlar
    private List<Unidad> unidades = null; // lista de objetos de tipo unidades

    //constructor
    public UnidadController()
    {
        unidad = new Unidad(); //instancio el objeto unidad
    }

    //getter y setter
    public Unidad getUnidad()
    {
        return unidad;
    }

    public void setUnidad(Unidad unidad)
    {
        this.unidad = unidad;
    }

    public List<Unidad> getUnidades()
    {
        unidades = UnidadDB.read();
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades)
    {
        this.unidades = unidades;
    }

    //logica para registrar un unidad
    public void register()
    {
        if (UnidadDB.create(unidad))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para eliminar un unidad
    public void delete()
    {
        if (UnidadDB.delete(unidad))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un unidad
    public void update()
    {
        if (UnidadDB.update(unidad))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
