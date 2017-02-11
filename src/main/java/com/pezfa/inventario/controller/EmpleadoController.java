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

    private Empleado empleado = null; // objeto a controlar
    private List<Empleado> empleadores = null; // lista de objetos de tipo empleados

    //constructor
    public EmpleadoController()
    {
        empleado = new Empleado(); //instancio el objeto empleado
    }

    //getter y setter
    public Empleado getEmpleado()
    {
        this.empleado = empleado;
        return empleado;
    }

    public Empleado setEmpleado()
    {
        this.empleado = empleado;
        return empleado;
    }

    public List<Empleado> getEmpleadores()
    {
        empleadores = EmpleadoDB.read();
        return empleadores;
    }

    public void setEmpleadores()
    {
        this.empleadores = empleadores;
    }

    //logica para registrar un empleado
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

    //logica para eliminar un empleado
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

    //logica para actualizar un empleado
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
