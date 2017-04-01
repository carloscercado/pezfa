package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AlmacenDB;
import com.pezfa.inventario.models.Almacen;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class AlmacenController implements Serializable
{

    private Almacen almacen = null; // objeto a controlar
    private List<Almacen> almacenes = null; // lista de objetos de tipo almace

    //constructor
    public AlmacenController()
    {
        almacen = new Almacen(); //instancio el objeto almacen
    }

    //getter y setter
    public Almacen getAlmacen()
    {
        return almacen;
    }

    public void setAlmacen(Almacen almacen)
    {
        this.almacen = almacen;
    }

    public List<Almacen> getAlmacenes()
    {
        almacenes = AlmacenDB.read();
        return almacenes;
    }

    public void setAlmacenes(List<Almacen> almacenes)
    {
        this.almacenes = almacenes;
    }
    
     public void reset()
    {
        System.out.println("Sin limpiar");
        almacen = new Almacen();
        System.out.println("Reseteado");        
    }
    public void register()
    {
        if (AlmacenDB.create(almacen))
        {
            almacen = new Almacen();
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

    //logica para eliminar un almacen
    public void delete()
    {
        if (AlmacenDB.delete(almacen))
        {
            almacen = new Almacen();
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
        if(AlmacenDB.update(almacen))
       {
            almacen = new Almacen();
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