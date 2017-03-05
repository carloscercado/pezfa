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
 * @author Adela Hernandez
 */
@ManagedBean
@ViewScoped
public class EmpleadoController implements Serializable {

    private Empleado empleado = null;
    private List<Empleado> empleadores = null;

    //constructor
    public EmpleadoController() {
        empleado = new Empleado();
    }

    //getter y setter
    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleadores() {
        empleadores = EmpleadoDB.read();
        return empleadores;
    }

    public void setEmpleadores(List<Empleado> empleadores) {
        this.empleadores = empleadores;
    }

    public void register() {
        if (EmpleadoDB.create(empleado)) {
            empleado = new Empleado();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void delete() {
        if (EmpleadoDB.delete(empleado)) {
            empleado = new Empleado();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Problemas al eliminar", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void update() {
        if (EmpleadoDB.update(empleado)) {
            System.out.println("Actualizado");
        } else {
            System.out.println("No Actualizado");
        }
    }
}
