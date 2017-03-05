package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EspecieDB;
import com.pezfa.inventario.models.Especie;
import com.pezfa.inventario.models.Proveedor;
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

    public void register()
    {
        if (EspecieDB.create(especie))
        {
            especie = new Especie();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (EspecieDB.delete(especie))
        {
            especie = new Especie();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado existosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
            
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al Eliminar", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para actualizar un almacen
    public void update()
    {
        if (EspecieDB.update(especie))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
