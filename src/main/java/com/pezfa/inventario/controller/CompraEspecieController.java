package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CompraEspecieDB;
import com.pezfa.inventario.models.CompraEspecie;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CompraEspecieController implements Serializable
{
    private CompraEspecie compraEspecie = null;
   private List<CompraEspecie> compraEspecies = null;

    public CompraEspecieController()
    {
        compraEspecie = new CompraEspecie(); 
    }

    public CompraEspecie getCompraEspecie()
    {
        return compraEspecie;
    }
    public void setCompraEspecie(CompraEspecie compraEspecie)
    {
        this.compraEspecie = compraEspecie;
    }

    public List<CompraEspecie> getCompraEspecies()
    {
        compraEspecies = CompraEspecieDB.read();
        return compraEspecies;
    }
    public void setCompraEspecies(List<CompraEspecie> compraEspecies)
    {
        this.compraEspecies = compraEspecies;
    }

    public void register()
    {
        if (CompraEspecieDB.create(compraEspecie))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (CompraEspecieDB.delete(compraEspecie))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    public void update()
    {
        if (CompraEspecieDB.update(compraEspecie))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}