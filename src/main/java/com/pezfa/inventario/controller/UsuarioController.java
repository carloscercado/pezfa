package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UsuarioDB;
import com.pezfa.inventario.models.Usuario;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Adela Hernandez
 */
public class UsuarioController implements Serializable
{

    private Usuario usuario = null; // objeto a controlar
    private List<Usuario> usuarios = null; // lista de objetos de tipo usuarios

    //constructor
    public UsuarioController()
    {
        usuario = new Usuario(); //instancio el objeto usuario
    }

    //getter y setter
    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario()
    {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios()
    {
        usuarios = UsuarioDB.read();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios)
    {
        this.usuarios = usuarios;
    }

    //logica para registrar un usuario
    public void register()
    {
        if (UsuarioDB.create(usuario))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    //logica para eliminar un usuario
    public void delete()
    {
        if (UsuarioDB.delete(usuario))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un usuario
    public void update()
    {
        if (UsuarioDB.update(usuario))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
