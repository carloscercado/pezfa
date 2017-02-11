package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ClienteDB;
import com.pezfa.inventario.models.Cliente;
import java.io.Serializable;
import java.util.List;

public class ClienteController implements Serializable
{
    private Cliente cliente = null;
    private List<Cliente> clientes = null;
    
    public ClienteController()
    {
        cliente = new Cliente(); 
    }
    
    public Cliente getCliente()
    {
        return cliente;
    }
    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }
    
    public List<Cliente> getClientes()
    {
        clientes = ClienteDB.read();
        return clientes;
    }
    public void setClientes(List<Cliente> clientes)
    {
        this.clientes = clientes;
    }
    
    public void register()
    {
        if(ClienteDB.create(cliente))
        {
            System.out.println("Cliente Registrado");
        }else
        {
            System.out.println("No Se Pudo Registar El Cliente");
        }
    }
    
    public void update()
    {
        if(ClienteDB.update(cliente))
        {
            System.out.println("Cliente Actualizado");
        }else
        {
            System.out.println("No Se Pudo Actualizar El Cliente");
        }
    }
    
    public void delete()
    {
        if(ClienteDB.delete(cliente))
        {
            System.out.println("Cliente Eliminado");
        }else
        {
            System.out.println("No Se Pudo Eliminar El Cliente");
        }
    }
}