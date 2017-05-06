package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CamionDB;
import com.pezfa.inventario.models.Camion;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class CamionController implements Serializable
{

    private Camion camion = null;  
    private List<Camion> camiones = null;
    private CamionDB db;

    //constructor
    public CamionController()
    {
        camion = new Camion();
        db = new CamionDB();
    }

    //getter y setter
    public Camion getCamion()
    {
        return camion;
    }

    public void setCamion(Camion camion)
    {
        this.camion = camion;
    }

    public List<Camion> getCamiones()
    {
        camiones = db.read("from Camion");
        return camiones;
    }

    public void setCamiones(List<Camion> camiones)
    {
        this.camiones = camiones;
    }
    
     public void reset()
    {
        camion = new Camion();
    }
    public void register()
    {
        if (db.create(camion))
        {
            camion = new Camion();
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

    //logica para eliminar un camion
    public void delete()
    {
        if (db.delete(camion))
        {
            camion = new Camion();
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
    
    public void update()
    {
        if(db.update(camion))
       {
            camion = new Camion();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }

    }
}