package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaEspecieDB;
import com.pezfa.inventario.models.VentaEspecie;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class VentaUnidadController implements Serializable
{

    private VentaEspecie ventaunidad = null;//objeto a controlar
    private List<VentaEspecie> ventauni = null;//lista de objetos de tipo ventauni
    private VentaEspecieDB db;

    //constructor
    public VentaUnidadController()
    {
        ventaunidad = new VentaEspecie();
        db = new VentaEspecieDB();
    }

    //métodos getter y setter
    public VentaEspecie getVentaUnidad()
    {
        return ventaunidad;
    }

    public void setVentaUnidad(VentaEspecie ventaunidad)
    {
        this.ventaunidad = ventaunidad;
    }
    
   

    public List<VentaEspecie> getVentauni()
    {
        ventauni = db.read("from VentaUnidad");
        return ventauni;
    }

    public void setVentauni(List<VentaEspecie> ventauni)
    {
        this.ventauni = ventauni;
    }

    //logica para registrar una ventaunidad
    public void register()
    {
        if (db.create(ventaunidad))
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
        if (db.delete(ventaunidad))
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
        if (db.update(ventaunidad))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
}
