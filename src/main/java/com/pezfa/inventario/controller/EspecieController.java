package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EspecieDB;
import com.pezfa.inventario.models.Especie;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class EspecieController implements Serializable
{

    Especie especie = null; // objeto a controlar
    List<Especie> especies = null; // lista de objetos de tipo almace

    //constructor
    public EspecieController()
    {
        especie = new Especie(); //instancio el objeto almacen
    }

    //getter y setter
    public Especie getEspecie()
    {
        return especie;
    }

    public void setEspecie(Especie especie)
    {
        this.especie = especie;
    }

    public List<Especie> getEspecies()
    {
        especies = EspecieDB.read();
        return especies;
    }

    public void setEspecie(List<Especie> especies)
    {
        this.especies = especies;
    }

     public void reset()
    {
        System.out.println("Sin limpiar");
        especie = new Especie();
        System.out.println("Reseteado");        
    }
    public void register()
    {
        if(EspecieDB.create(especie))
        {
            especie = new Especie();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
    
    public void update()
    {
        if(EspecieDB.update(especie))
        {
            especie = new Especie();
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
    
    public void delete()
    {
        if(EspecieDB.delete(especie))
        {
            especie = new Especie();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Este registro no puede ser eliminado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}