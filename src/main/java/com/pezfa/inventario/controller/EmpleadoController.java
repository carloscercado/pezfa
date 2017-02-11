package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EmpleadoDB;
import com.pezfa.inventario.models.Empleado;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adela Hernandez
 */
public class EmpleadoController implements Serializable
{

    private Empleado empleado = null; 
    private List<Empleado> empleadores = null; 

    //constructor
    public EmpleadoController()
    {
        empleado = new Empleado(); 
    }

    //getter y setter
    public Empleado getEmpleado()
    {
        return empleado;
    }

    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleadores()
    {
        empleadores = EmpleadoDB.read();
        return empleadores;
    }

    public void setEmpleadores(List<Empleado> empleadores)
    {
        this.empleadores = empleadores;
    }

    public void register()
    {
        if (EmpleadoDB.create(empleado))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (EmpleadoDB.delete(empleado))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    public void update()
    {
        if (EmpleadoDB.update(empleado))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
