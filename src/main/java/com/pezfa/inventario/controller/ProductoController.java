package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProductoDB;
import com.pezfa.inventario.models.Producto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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
            System.out.println("Producto Registrado Correctamente");
        }else
        {
            System.out.println("No se Registro El Producto");
        }
    }
    
    public void update()
    {
        if(ProductoDB.update(producto))
        {
            System.out.println("Producto Actualizado");
        }else
        {
            System.out.println("Producto No Actualizado");
        }
    }
    
    public void delete()
    {
        if(ProductoDB.delete(producto))
        {
            System.out.println("Producto Eliminado");
        }else
        {
            System.out.println("Producto No Eliminado");
        }
    }
}