package com.pezfa.inventario.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class LogoutController
{

    private final HttpServletRequest req;
    private final FacesContext contexto;

    public LogoutController()
    {
        contexto = FacesContext.getCurrentInstance();
        req = (HttpServletRequest) contexto.getExternalContext().getRequest();

        if (req.getSession().getAttribute("sesion") == null)
        {
            try
            {
                contexto.getExternalContext().redirect("pages/");
            } catch (Exception ex)
            {
                //nada
            }
        }

    }

    public void cerrar()
    {
        try
        {
            req.getSession().removeAttribute("sesion");
            req.getSession().invalidate();
            contexto.getExternalContext().redirect("");
        } catch (Exception ex)
        {
            //nada
        }
    }

}
