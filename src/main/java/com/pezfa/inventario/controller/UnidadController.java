package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UbicacionDB;
import com.pezfa.inventario.models.Ubicacion;
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

    private Ubicacion unidad = null; // objeto a controlar
    private List<Ubicacion> unidades = null; // lista de objetos de tipo unidades
    @ManagedProperty(value = "#{compraEspecieController}")
    private CompraEspecieController compraEspecieController;
    private UbicacionDB db;

    public UnidadController()
    {
        unidad = new Ubicacion(); //instancio el objeto unidad
        db = new UbicacionDB();
    }

    public Ubicacion getUnidad()
    {
        return unidad;
    }

    public void setUnidad(Ubicacion unidad)
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

    public List<Ubicacion> getUnidades()
    {
        unidades = db.read("from Unidad uni join fetch uni.cava cav join fetch cav.almacen join fetch uni.compraEspecie cpe join fetch cpe.especie");
        return unidades;
    }

    public void setUnidades(List<Ubicacion> unidades)
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
            unidad = new Ubicacion();
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
