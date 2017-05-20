package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.InsumoDB;
import com.pezfa.inventario.models.Insumo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class InsumoController implements Serializable {

    private Insumo insumo;
    private InsumoDB db;
    private List<Insumo> insumos;

    //constructor
    public InsumoController() {
        insumo = new Insumo();
        db = new InsumoDB();
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    
    public List<Insumo> getInsumos() {
        insumos = db.read("from Insumo");
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }

   
    public void reset() {
        insumo = new Insumo();
    }

    public void register() {
        
        insumo.setMinimo(10.0);
        insumo.setCantidad(0.0);
        if (db.create(insumo)) {
            insumo = new Insumo();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para eliminar un camion
    public void delete() {
        if (db.delete(insumo)) {
            insumo = new Insumo();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Este registro no puede ser eliminado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void update() {
        if (db.update(insumo)) {
            insumo = new Insumo();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }

    }
}
