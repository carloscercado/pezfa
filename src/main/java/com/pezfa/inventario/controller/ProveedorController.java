package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProveedorDB;
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

    //logica para registrar un almacen
    public void register()
    {
        if (ProveedorDB.create(proveedor))
        {
            proveedor = new Proveedor();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para eliminar un almacen
    public void delete()
    {
        if (ProveedorDB.delete(proveedor))
        {
            proveedor = new Proveedor();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("eliminado", mensaje);
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
        if (ProveedorDB.update(proveedor))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
