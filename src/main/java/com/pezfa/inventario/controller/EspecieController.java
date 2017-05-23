package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EspecieDB;
import com.pezfa.inventario.models.Especie;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    private Especie especie = null; // objeto a controlar
    private List<Especie> especies = null; // lista de objetos de tipo almace
    private List<String> tipos;
    private EspecieDB db;
    
    //constructor
    public EspecieController()
    {
        especie = new Especie(); //instancio el objeto almacen
        db = new EspecieDB();
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
        especies = db.read("from Especie");
        return especies;
    }

    public void setEspecie(List<Especie> especies)
    {
        this.especies = especies;
    }

    public void setTipos(List<String> tipos) 
    {
        this.tipos = tipos;
    }
   
    public List<String> getTipos() 
    {
        tipos = new ArrayList<String>();
        tipos.add("CRUSTACEOS");
        tipos.add("MOLUSCOS");
        tipos.add("PEZ");
        return tipos;
    }
     public void reset()
    {
        especie = new Especie();
    }
     
     public void validarCodigo()
    {  
        if(db.validarCodigo(this.especie.getCodigo()))
        {
            this.especie.setCodigo("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrado esta especie", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);            
        }
    }
     
    public void register()
    {
        especie.setMinimo(10.0);
        especie.setCantidad(0.0);
        especie.setPrecio(BigDecimal.ZERO);
        especie.setMaximo(100.0);
        
        especie.toUpperCase();
        if(db.create(especie))
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
        especie.toUpperCase();
        if(db.update(especie))
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
        if(db.delete(especie))
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