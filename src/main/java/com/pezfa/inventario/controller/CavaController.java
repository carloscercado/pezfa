package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CavaDB;
import com.pezfa.inventario.models.Cava;
import java.io.Serializable;
import java.util.List;

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
    
    public void register()
    {
        if(CavaDB.create(cava))
        {
            System.out.println("Registrado Exitosamente");
        }else
        {
            System.out.println("No Se Pudo Registrar");
        }
    }
    
    public void delete()
    {
        if(CavaDB.delete(cava))
        {
            System.out.println("Eliminado Exitosamente");
        }else
        {
            System.out.println("No Se Pudo Eliminar");
        }
    }
    
    public void update()
    {
        if(CavaDB.update(cava))
        {
            System.out.println("Actualizado Exitosamente");
        }else
        {
            System.out.println("No Se Pudo Actualizar");
        }
    }
}