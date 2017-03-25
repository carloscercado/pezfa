package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProductoDB;
import com.pezfa.inventario.models.Producto;
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
public class ProductoController implements Serializable
{
    private Producto producto = null;
    private List<Producto> productos = null;
    
    public ProductoController()
    {
        producto = new Producto();
    }
    
    public Producto getProducto()
    {
        return producto;
    }
    public void setProducto(Producto producto)
    {
        this.producto = producto;
    }
    
    public List<Producto> getProductos()
    {
        productos = ProductoDB.read();
        return productos;
    }
    public void setProductos(List<Producto> productos)
    {
        this.productos = productos;
    }
    
    public void register()
    {
        if(ProductoDB.create(producto))
        {
            producto = new Producto();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
    
    public void update()
    {
        if(ProductoDB.update(producto))
        {
            producto = new Producto();
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
        if(ProductoDB.delete(producto))
        {
            producto = new Producto();
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