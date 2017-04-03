package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CavaDB;
import com.pezfa.inventario.models.Cava;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class CavaController implements Serializable
{
    private Cava cava = null;
    private List<Cava> cavas = null;
    
    public CavaController()
    {
        cava = new Cava();
    }
    
    public Cava getCava()
    {
        return cava;
    }
    public void setCava(Cava cava)
    {
        this.cava = cava;
    }
    
    public List<Cava> getCavas()
    {
        cavas = CavaDB.read();
        return cavas;
    }
    public void setCavas(List<Cava> cavas)
    {
        this.cavas = cavas;
    }
    public void reset()
    {
        System.out.println("Sin limpiar");
        cava = new Cava();
        System.out.println("Reseteado");        
    }
    
    public void register()
    {
        if(CavaDB.create(cava))
        {
            cava = new Cava();
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
        if(CavaDB.update(cava))
       {
            cava = new Cava();
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
        if(CavaDB.delete(cava))
        {
            cava = new Cava();
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