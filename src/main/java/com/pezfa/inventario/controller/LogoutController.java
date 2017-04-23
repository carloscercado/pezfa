package com.pezfa.inventario.controller;


import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.models.Auditoria;
import com.pezfa.inventario.models.Usuario;
import java.util.Date;
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
            Auditoria auditoria = new Auditoria();
            AuditoriaDB auditoDB = new AuditoriaDB();
            auditoria.setUsuario((Usuario) req.getSession().getAttribute("sesion"));
            Date fecha = new Date();
            auditoria.setFecha(fecha);
            auditoria.setHora(fecha);
            auditoria.setTipo("ACCESO");
            auditoria.setDescripcion("CIERRE DE SESION CON USUARIO '"+auditoria.getUsuario().getUsuario()+"'");
            auditoDB.create(auditoria);
            req.getSession().removeAttribute("sesion");
            req.getSession().invalidate();
            contexto.getExternalContext().redirect("");
        } catch (Exception ex)
        {
            //nada
        }
    }

}
