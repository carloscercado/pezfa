package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UsuarioDB;
import com.pezfa.inventario.models.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Romario Guerrero
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario = null; // objeto a controlar
    private final HttpServletRequest req;
    private UsuarioDB db;
    private final FacesContext contexto;
    private List<Usuario> usuarios = null; // lista de objetos de tipo usuarios
    private Usuario sesion;

    //constructor

    public UsuarioController() {
        contexto = FacesContext.getCurrentInstance();
        req = (HttpServletRequest) contexto.getExternalContext().getRequest();
        Usuario userr = (Usuario) req.getSession().getAttribute("sesion");

        if ((Usuario)req.getSession().getAttribute("sesion") != null)
        {
            sesion = (Usuario) req.getSession().getAttribute("sesion");
            usuario = new Usuario();
            db = new UsuarioDB();
        } else
        {
            try
            {
                
                contexto.getExternalContext().redirect("/pezfa");
            } catch (Exception ex)
            {
                System.out.println("Problemas al redireccionar "+ex.getMessage());
            }
        }
    }

    //getter y setter
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        usuarios = db.read("from Usuario user join fetch user.empleado");
        return usuarios;
    }

    public Usuario getSesion() {
        return sesion;
    }

    public void setSesion(Usuario sesion) {
        this.sesion = sesion;
    }

    
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    //logica para registrar un usuario
    public void register() {
        String user = usuario.getUsuario();
        usuario.setClave(user);
        if (db.create(usuario)) {
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
        if (db.delete(usuario)) {
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
        if (db.update(usuario)) {
            System.out.println("Actualizado");
        } else {
            System.out.println("No Actualizado");
        }
    }
}
