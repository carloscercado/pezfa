package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaUnidadDB;
import com.pezfa.inventario.models.VentaUnidad;
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
public class VentaTerminadoController implements Serializable
{

    private VentaUnidad ventaterminado = null; // objeto a controlar
    private List<VentaUnidad> ventatermin = null; //lista de objetos de tipo ventaterminado
    private VentaUnidadDB db;

    //constructor
    public VentaTerminadoController()
    {
        ventaterminado = new VentaUnidad(); //se instancia el objeto
        db = new VentaUnidadDB();
    }
    
    //getter y setter
    public VentaUnidad getVentaTerminado()
    {
        return ventaterminado;
    }

    public void setVentaTerminado(VentaUnidad ventaterminado)
    {
        this.ventaterminado = ventaterminado;
    }

    public List<VentaUnidad> getVentaTermin()
    {
        ventatermin = db.read("from VentaTerminado");
        return ventatermin;
    }

    public void setVentaTermin(List<VentaUnidad> ventatermin)
    {
        this.ventatermin = ventatermin;
    }

    //logica para registrar una ventaterminado
    public void register()
    {
        if (db.create(ventaterminado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para borrar una ventaterminado
    public void delete()
    {
        if (db.delete(ventaterminado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para actualizar una ventaterminado
    public void update()
    {
        if (db.update(ventaterminado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
}
