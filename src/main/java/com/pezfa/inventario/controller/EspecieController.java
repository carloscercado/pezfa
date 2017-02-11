package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EspecieDB;
import com.pezfa.inventario.models.Especie;
import java.io.Serializable;
import java.util.List;

public class EspecieController implements Serializable
{

    Especie especie = null; // objeto a controlar
    List<Especie> especies = null; // lista de objetos de tipo almace

    //constructor
    public EspecieController()
    {
        especie = new Especie(); //instancio el objeto almacen
    }

    //getter y setter
    public Especie getEspecie()
    {
        return especie;
    }

    public void setEspecie(Especie especie)
    {
        this.especie = especie;
    }

    public List<Especie> getEspecies()
    {
        especies = EspecieDB.read();
        return especies;
    }

    public void setAlmacenes(List<Especie> especies)
    {
        this.especies = especies;
    }

    //logica para registrar un almacen
    public void register()
    {
        if (EspecieDB.create(especie))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para eliminar un almacen
    public void delete()
    {
        if (EspecieDB.delete(especie))
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
        if (EspecieDB.update(especie))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}

