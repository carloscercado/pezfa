package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.models.Unidad;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Romario Guerrero
 */
@ManagedBean
@ViewScoped
public class UnidadController implements Serializable
{

    private Unidad unidad = null; // objeto a controlar
    private List<Unidad> unidades = null; // lista de objetos de tipo unidades
    @ManagedProperty(value = "#{compraEspecieController}")
    private CompraEspecieController compraEspecieController;
    private UnidadDB db;

    public UnidadController()
    {
        unidad = new Unidad(); //instancio el objeto unidad
        db = new UnidadDB();
    }

    public Unidad getUnidad()
    {
        return unidad;
    }

    public void setUnidad(Unidad unidad)
    {
        this.unidad = unidad;
    }

    public CompraEspecieController getCompraEspecieController()
    {
        return compraEspecieController;
    }

    public void setCompraEspecieController(CompraEspecieController compraEspecieController)
    {
        this.compraEspecieController = compraEspecieController;
    }

    public List<Unidad> getUnidades()
    {
        unidades = db.read("from Unidad uni join fetch uni.cava cav join fetch cav.almacen join fetch uni.compraEspecie cpe join fetch cpe.especie");
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades)
    {
        this.unidades = unidades;
    }

    //logica para registrar un unidad
    public void register()
    {
        unidad.setCompraEspecie(compraEspecieController.getCompraEspecie());
        unidad.setEstado(Boolean.TRUE);
        unidad.setCodigo(UUID.randomUUID().toString());
        if (db.create(unidad))
        {
            unidad = new Unidad();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unidad ubicada exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide(); PF('registrar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al ubicar insumo", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

}
