package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CompraDB;
import com.pezfa.inventario.models.Compra;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CompraController implements Serializable
{
    private Compra compra = null;
    private List<Compra> compras = null;
    private CompraDB db;
    
    public CompraController()
    {
        compra = new Compra();
        db = new CompraDB();
    }
    
    public Compra getCompra()
    {
        return compra;
    }
    public void setCompra(Compra compra)
    {
        this.compra = compra;
    }
    
    public List<Compra> getCompras()
    {
        compras = db.read("from Compra comprita join fetch comprita.proveedor join fetch comprita.usuario where comprita.estado = 'Comprado'");
        return compras;
    }
    public void setCompras(List<Compra> compras)
    {
        this.compras = compras;
    }
    
    public void register()
    {
        if(db.create(compra))
        {
            System.out.println("Su Compra Ha Sido Registrada");
        }else
        {
            System.out.println("No Se Pudo Registrar La Compra");
        }
    }
    
    public void delete()
    {
        if(db.delete(compra))
        {
            System.out.println("Su Compra Ha Sido ELiminada");
        }else
        {
            System.out.println("No Se Pudo Eliminar La Compra");
        }
    }
    
    public void update()
    {
        if(db.update(compra))
        {
            System.out.println("Su Copra Ha Sido Actualizada");
        }else
        {
            System.out.println("No Se Actualizo Su Compra");
        }
    }
}