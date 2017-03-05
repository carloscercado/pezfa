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
    
    public CompraController()
    {
        compra = new Compra();
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
        compras = CompraDB.read();
        return compras;
    }
    public void setCompras(List<Compra> compras)
    {
        this.compras = compras;
    }
    
    public void register()
    {
        if(CompraDB.create(compra))
        {
            System.out.println("Su Compra Ha Sido Registrada");
        }else
        {
            System.out.println("No Se Pudo Registrar La Compra");
        }
    }
    
    public void delete()
    {
        if(CompraDB.delete(compra))
        {
            System.out.println("Su Compra Ha Sido ELiminada");
        }else
        {
            System.out.println("No Se Pudo Eliminar La Compra");
        }
    }
    
    public void update()
    {
        if(CompraDB.update(compra))
        {
            System.out.println("Su Copra Ha Sido Actualizada");
        }else
        {
            System.out.println("No Se Actualizo Su Compra");
        }
    }
}