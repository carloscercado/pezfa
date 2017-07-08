package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProduccionDB;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.Produccion;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
@ViewScoped
@ManagedBean
public class ProduccionController implements Serializable
{

    Produccion produccion = null; // objeto a controlar
    List<Produccion> producciones = null; // lista de objetos de tipo almace
    private ProduccionDB db;
    
    

    public ProduccionController()
    {
        produccion = new Produccion(); //instancio el objeto almacen
        db = new ProduccionDB();
    }

    public Produccion getProduccion()
    {
        return produccion;
    }

    public void setProducciones(Produccion produccion)
    {
        this.produccion = produccion;
    }

    public List<Produccion> getProducciones()
    {
        producciones = db.read("from Produccion");
        return producciones;
    }

    public void register()
    {
        produccion.toUpperCase();
        if (db.create(produccion))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void delete()
    {
        if (db.delete(produccion))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un almacen
    public void update()
    {
        produccion.toUpperCase();
        if (db.update(produccion))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
    

}
