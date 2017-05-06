package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EmpleadoDB;
import com.pezfa.inventario.models.Empleado;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Romario Guerrero
 */
@ManagedBean
@ViewScoped
public class EmpleadoController implements Serializable
{

    private Empleado empleado = null;
    private List<Empleado> empleadores = null;
    private EmpleadoDB db;

    //constructor
    public EmpleadoController()
    {
        db = new EmpleadoDB();
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
        empleadores = db.read("From Empleado");
        return empleadores;
    }

    public void setEmpleadores(List<Empleado> empleadores)
    {
        this.empleadores = empleadores;
    }

    public void reset()
    {
        System.out.println("Sin limpiar");
        empleado = new Empleado();
        System.out.println("Reseteado");
    }

    public void validarCedula()
    {  
        if(db.validarCedula(this.empleado.getCedula()))
        {
            this.empleado.setCedula("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrado este empleado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);            
        }
    }
    public void register()
    {
        empleado.toUpperCase();
        if (db.create(empleado))
        {
            empleado = new Empleado();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void update()
    {
        empleado.toUpperCase();
        if (db.update(empleado))
        {
            empleado = new Empleado();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void delete()
    {
        if (db.delete(empleado))
        {
            empleado = new Empleado();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Este registro no puede ser eliminado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}
