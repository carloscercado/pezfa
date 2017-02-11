package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaUnidadDB;
import com.pezfa.inventario.models.VentaUnidad;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adela Hernandez
 */
public class VentaUnidadController implements Serializable
{

    private VentaUnidad ventaunidad = null;//objeto a controlar
    private List<VentaUnidad> ventauni = null;//lista de objetos de tipo ventauni

    //constructor
    public VentaUnidadController()
    {
        ventaunidad = new VentaUnidad();
    }

    //métodos getter y setter
    public VentaUnidad getVentaUnidad()
    {
        return ventaunidad;
    }

    public void setVentaUnidad(VentaUnidad ventaunidad)
    {
        this.ventaunidad = ventaunidad;
    }

    public List<VentaUnidad> getVentauni()
    {
        ventauni = VentaUnidadDB.read();
        return ventauni;
    }

    public void setVentauni(List<VentaUnidad> ventauni)
    {
        this.ventauni = ventauni;
    }

    //logica para registrar una ventaunidad
    public void register()
    {
        if (VentaUnidadDB.create(ventaunidad))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para borrar una ventaunidad
    public void delete()
    {
        if (VentaUnidadDB.delete(ventaunidad))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
    
    //logica para actualizar una ventaunidad
    public void update()
    {
        if (VentaUnidadDB.update(ventaunidad))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
}
