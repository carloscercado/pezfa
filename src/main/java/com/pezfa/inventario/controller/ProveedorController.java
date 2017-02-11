package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProveedorDB;
import com.pezfa.inventario.models.Proveedor;
import java.io.Serializable;
import java.util.List;

public class ProveedorController implements Serializable
{

    Proveedor proveedor = null; // objeto a controlar
    List<Proveedor> proveedores = null; // lista de objetos de tipo almace

    //constructor
    public ProveedorController()
    {
        proveedor = new Proveedor(); //instancio el objeto almacen
    }

    //getter y setter
    public Proveedor getProveedor()
    {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor)
    {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProvedores()
    {
        proveedores = ProveedorDB.read();
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores)
    {
        this.proveedores = proveedores;
    }

    //logica para registrar un almacen
    public void register()
    {
        if (ProveedorDB.create(proveedor))
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
        if (ProveedorDB.delete(proveedor))
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
        if (ProveedorDB.update(proveedor))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}

