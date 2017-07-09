package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EmpleadoDB;
import com.pezfa.inventario.database.UsuarioDB;
import com.pezfa.inventario.models.Empleado;
import com.pezfa.inventario.models.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Carlos Cercado
 * @email cercadocarlos@gmail.com
 */
@ManagedBean
@RequestScoped
public class NuevaCuentaControllar implements Serializable
{

    private final HttpServletRequest req;
    private final FacesContext contexto;
    private Empleado empleado;
    private Date fecha = new Date();

    public NuevaCuentaControllar()
    {
        empleado = new Empleado();
        contexto = FacesContext.getCurrentInstance();
        req = (HttpServletRequest) contexto.getExternalContext().getRequest();
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    
    public Empleado getEmpleado()
    {
        return empleado;
    }

    public void setEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    public void registerNuevo()
    {
        empleado.toUpperCase();
        EmpleadoDB db = new EmpleadoDB();
        if (db.create(empleado))
        {
            UsuarioDB dbu = new UsuarioDB();
            Usuario obj = new Usuario();
            obj.setUsuario("admin");
            obj.setClave("admin");
            obj.setEmpleado(empleado);
            obj.setRol("ADMINISTRACION");
            if (dbu.create(obj))
            {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
                req.getSession().setAttribute("sesion", obj);
                RequestContext con = RequestContext.getCurrentInstance();
                con.execute("location.href='/pezfa/pages/'");
            }

        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

}
