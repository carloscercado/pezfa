package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ClienteDB;
import com.pezfa.inventario.models.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class ClienteController implements Serializable
{
    private Cliente cliente = null;
    private List<Cliente> clientes = null;
    
    public ClienteController()
    {
        cliente = new Cliente(); 
    }
    
    public Cliente getCliente()
    {
        return cliente;
    }
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
    public List<Cliente> getClientes()
    {
        clientes = ClienteDB.read();
        return clientes;
    }
    public void setClientes(List<Cliente> clientes)
    {
        this.clientes = clientes;
    }
    
    public void reset()
    {
        System.out.println("Sin limpiar");
        cliente = new Cliente();
        System.out.println("Reseteado");        
    }
    public void register()
    {
        if(ClienteDB.create(cliente))
        {
            cliente = new Cliente();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
    
    public void update()
    {
        if(ClienteDB.update(cliente))
        {
            cliente = new Cliente();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
    
    public void delete()
    {
        if(ClienteDB.delete(cliente))
        {
            cliente = new Cliente();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Problemas al eliminar", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}