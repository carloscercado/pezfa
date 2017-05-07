package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProductoDB;
import com.pezfa.inventario.models.Producto;
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
    private ProductoDB db;

    public ProductoController()
    {
        producto = new Producto();
        db = new ProductoDB();
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
        productos = db.read("from Producto");
        return productos;
    }

    public void setProductos(List<Producto> productos)
    {
        this.productos = productos;
    }

    public void reset()
    {
        producto = new Producto();
    }

    public void validarCodigo()
    {  
        if(db.validarCodigo(this.producto.getCodigo()))
        {
            this.producto.setCodigo("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrado este producto", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);            
        }
    }
    
    public void register()
    {
        producto.toUpperCase();
        if (db.create(producto))
        {
            producto = new Producto();
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

    public void update()
    {
        producto.toUpperCase();
        if (db.update(producto))
        {
            producto = new Producto();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void delete()
    {
        if (db.delete(producto))
        {
            producto = new Producto();
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
}
