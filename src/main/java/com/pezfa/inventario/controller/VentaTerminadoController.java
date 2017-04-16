package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaTerminadoDB;
import com.pezfa.inventario.models.VentaTerminado;
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

    private VentaTerminado ventaterminado = null; // objeto a controlar
    private List<VentaTerminado> ventatermin = null; //lista de objetos de tipo ventaterminado
    private VentaTerminadoDB db;

    //constructor
    public VentaTerminadoController()
    {
        ventaterminado = new VentaTerminado(); //se instancia el objeto
        db = new VentaTerminadoDB();
    }
    
    //getter y setter
    public VentaTerminado getVentaTerminado()
    {
        return ventaterminado;
    }

    public void setVentaTerminado(VentaTerminado ventaterminado)
    {
        this.ventaterminado = ventaterminado;
    }

    public List<VentaTerminado> getVentaTermin()
    {
        ventatermin = db.read("from VentaTerminado");
        return ventatermin;
    }

    public void setVentaTermin(List<VentaTerminado> ventatermin)
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
