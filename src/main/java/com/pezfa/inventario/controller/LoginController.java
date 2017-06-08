package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UsuarioDB;
import com.pezfa.inventario.models.Usuario;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class LoginController implements Serializable {

    private Usuario cuenta;
    private final HttpServletRequest req;
    private final FacesContext contexto;

    public LoginController() {
        System.out.println("hao");
        contexto = FacesContext.getCurrentInstance();
        req = (HttpServletRequest) contexto.getExternalContext().getRequest();
        Usuario userr = (Usuario) req.getSession().getAttribute("sesion");
        if (userr != null) {
            try {
                contexto.getExternalContext().redirect("pages/");
            } catch (Exception ex) {
                contexto.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Problemas: "
                        + ex.getMessage(), null));
            }
        } else {
            cuenta = new Usuario();
        }
    }

    public Usuario getUsuario() {
        return cuenta;
    }

    public void setUsuario(Usuario usuario) {
        this.cuenta = usuario;
    }

    public void validate() {
        cuenta.setUsuario(cuenta.getUsuario().toLowerCase());
        Usuario user = UsuarioDB.validateUser(cuenta.getUsuario(), cuenta.getClave());
        if (user != null) {
            try {
                req.getSession().setAttribute("sesion", user);
                contexto.getExternalContext().redirect("pages/");
            } catch (Exception ex) {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                        + "Ocurrio un problema de conexion, comuniquese con el administrador",
                        null));
            }
        } else {
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos",
                    null));
        }
    }
}
