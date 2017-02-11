package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaTerminadoDB;
import com.pezfa.inventario.models.VentaTerminado;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adela Hernandez
 */
public class VentaTerminadoController implements Serializable
{

    private VentaTerminado ventaterminado = null; // objeto a controlar
    private List<VentaTerminado> ventatermin = null; //lista de objetos de tipo ventaterminado

    //constructor
    public VentaTerminadoController()
    {
        ventaterminado = new VentaTerminado(); //se instancia el objeto
    }
    
    //getter y setter
    public VentaTerminado getVentaTerminado()
    {
        this.ventaterminado = ventaterminado;
        return ventaterminado;
    }

    public VentaTerminado setVentaTerminado()
    {
        this.ventaterminado = ventaterminado;
        return ventaterminado;
    }

    public List<VentaTerminado> getVentaTermin()
    {
        ventatermin = VentaTerminadoDB.read();
        return ventatermin;
    }

    public void setVentaTermin()
    {
        this.ventatermin = ventatermin;
    }

    //logica para registrar una ventaterminado
    public void register()
    {
        if (VentaTerminadoDB.create(ventaterminado))
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
        if (VentaTerminadoDB.delete(ventaterminado))
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
        if (VentaTerminadoDB.update(ventaterminado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
}
