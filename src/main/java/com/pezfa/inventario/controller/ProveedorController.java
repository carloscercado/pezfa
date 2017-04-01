package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProveedorDB;
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
public class ProveedorController implements Serializable
{

    Proveedor proveedor = null; // objeto a controlar
    List<Proveedor> proveedores = null; // lista de objetos de tipo almace

    //constructor
    public ProveedorController()
    {
        proveedor = new Proveedor(); //instancio el objeto almacen
    }

    //getter y setter
    public Proveedor getProveedor()
    {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor)
    {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProvedores()
    {
        proveedores = ProveedorDB.read();
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores)
    {
        this.proveedores = proveedores;
    }

    public void reset()
    {
        System.out.println("Sin limpiar");
        proveedor = new Proveedor();
        System.out.println("Reseteado");        
    }
    public void register()
    {
        if(ProveedorDB.create(proveedor))
        {
            proveedor = new Proveedor();
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
        if(ProveedorDB.update(proveedor))
        {
            proveedor = new Proveedor();
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
        if(ProveedorDB.delete(proveedor))
        {
            proveedor = new Proveedor();
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