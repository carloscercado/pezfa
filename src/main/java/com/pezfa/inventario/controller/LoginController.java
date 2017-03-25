package com.pezfa.inventario.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
//import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class LoginController implements Serializable
{
/*
    private Cuenta cuenta;
    private final HttpServletRequest req;
    private final FacesContext contexto;

    public LoginController()
    {
        contexto = FacesContext.getCurrentInstance();
        req = (HttpServletRequest) contexto.getExternalContext().getRequest();

        if (req.getSession().getAttribute("sesion") != null)
        {
            try
            {
                Cuenta user = (Cuenta) req.getSession().getAttribute("sesion");
                String rol = user.getPersonal().getTipo();
                if (rol.toUpperCase().equals("EMPLEADO"))
                {
                    contexto.getExternalContext().redirect("pages/");
                } else if (rol.equals("GERENTE"))
                {
                    contexto.getExternalContext().redirect("pages/");
                }
            } catch (Exception ex)
            {
                contexto.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Problemas: "
                        + ex.getMessage(), null));
            }
        } else
        {
            cuenta = new Cuenta();
        }
    }

    public Cuenta getCuenta()
    {
        return cuenta;
    }

    public void setCuenta(Cuenta usuario)
    {
        this.cuenta = usuario;
    }

    public void validate()
    {
        Cuenta user = CuentaDAO.validate(cuenta);
        if (user != null)
        {
            try
            {
                req.getSession().setAttribute("sesion", user);
                String rol = user.getPersonal().getTipo();
                if (rol.toUpperCase().equals("EMPLEADO"))
                {
                    contexto.getExternalContext().redirect("pages/");
                } else if (rol.equals("GERENTE"))
                {
                    contexto.getExternalContext().redirect("pages/");
                }
            } catch (Exception ex)
            {
                contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ""
                        + "Ocurrio un problema de conexion, comuniquese con el administrador",
                        null));
            }
        } else
        {
            contexto.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Datos incorrectos",
                    null));
        }
    }
    */
}
