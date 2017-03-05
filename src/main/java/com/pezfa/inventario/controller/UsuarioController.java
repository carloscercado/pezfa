package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UsuarioDB;
import com.pezfa.inventario.models.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Adela Hernandez
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario = null; // objeto a controlar
    private List<Usuario> usuarios = null; // lista de objetos de tipo usuarios

    //constructor
    public UsuarioController() {
        usuario = new Usuario(); //instancio el objeto usuario
    }

    //getter y setter
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        usuarios = UsuarioDB.read();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void validateUsuario() {
        String clave = usuario.getClave();
        String user = usuario.getUsuario();
        Usuario userr = UsuarioDB.validateUser(user, clave);
        if (userr == null) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "HOLA " + userr.getEmpleado().getPrimerApellido(), null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }

    }

    //logica para registrar un usuario
    public void register() {
        String user = usuario.getUsuario();
        usuario.setClave(user);
        if (UsuarioDB.create(usuario)) {
            usuario = new Usuario();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro existoso", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro fallido", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para eliminar un usuario
    public void delete() {
        if (UsuarioDB.delete(usuario)) {
            usuario = new Usuario();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminargit ').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Problemas al eliminar", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para actualizar un usuario
    public void update() {
        if (UsuarioDB.update(usuario)) {
            System.out.println("Actualizado");
        } else {
            System.out.println("No Actualizado");
        }
    }
}
