package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.database.CompraInsumoDB;
import com.pezfa.inventario.models.Auditoria;
import com.pezfa.inventario.models.Camion;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.CompraEspecie;
import com.pezfa.inventario.models.CompraInsumo;
import com.pezfa.inventario.models.Especie;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class CompraInsumoController implements Serializable
{       

    private CompraInsumo compraInsumo;
    private Set<CompraInsumo> miLista;
    private List<CompraInsumo> compraInsumos;
    private List<CompraInsumo> detalleCompra;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    @ManagedProperty(value = "#{compraController}")
    private CompraController compraController;
    @ManagedProperty(value = "#{camionController}")
    private CamionController camionController;
    private CompraInsumoDB db;

    public CompraInsumoController() {
        compraInsumo = new CompraInsumo();
        miLista = new HashSet<CompraInsumo>();
        db = new CompraInsumoDB();
    }

    public CompraInsumo getCompraInsumo() {
        return compraInsumo;
    }

    public void setCompraInsumo(CompraInsumo compraInsumo) {
        this.compraInsumo = compraInsumo;
    }

    public Set<CompraInsumo> getMiLista() {
        return miLista;
    }

    public void setMiLista(Set<CompraInsumo> miLista) {
        this.miLista = miLista;
    }

    public List<CompraInsumo> getCompraInsumos() {
        return compraInsumos;
    }

    public void setCompraInsumos(List<CompraInsumo> compraInsumos) {
        this.compraInsumos = compraInsumos;
    }

    public List<CompraInsumo> getDetalleCompra() {
        return detalleCompra;
    }

    public void setDetalleCompra(List<CompraInsumo> detalleCompra) {
        this.detalleCompra = detalleCompra;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public CompraController getCompraController() {
        return compraController;
    }

    public void setCompraController(CompraController compraController) {
        this.compraController = compraController;
    }

    public CamionController getCamionController() {
        return camionController;
    }

    public void setCamionController(CamionController camionController) {
        this.camionController = camionController;
    }

    public CompraInsumoDB getDb() {
        return db;
    }

    public void setDb(CompraInsumoDB db) {
        this.db = db;
    }
    
    public double getTotal() {
        return this.miLista.stream()
                .mapToDouble(x -> x.getCosto().doubleValue() * x.getCantidad())
                .sum();
    }
    public void reset() {
        compraInsumo = new CompraInsumo();
    }
        public void add() {
        miLista.add(compraInsumo);
        compraInsumo = new CompraInsumo();
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('agregar').hide(); PF('registrar').hide();");
    }

   
    public void remove() {
        miLista.remove(compraInsumo);
        compraInsumo = new CompraInsumo();
    }
    
   /* public void register() {
        if (this.miLista.size() > 0) {
            compraController.getCompra().setUsuario(usuarioController.getSesion());
            compraInsumo.setCompra((Compra) compraController.getCompra());
            Compra compra = (Compra) compraController.getCompra();
            if (db.createList(miLista, compra)) {
                Auditoria auditoria = new Auditoria();
                AuditoriaDB auditoDB = new AuditoriaDB();
                auditoria.setUsuario(compra.getUsuario());
                auditoria.setFecha(compra.getFecha());
                auditoria.setHora(compra.getFecha());
                auditoria.setTipo("REGISTRO DE COMPRAS");
                auditoria.setDescripcion("REGISTRO DE COMPRAS CON ORDEN DE COMPRA " + compra.getOrden()
                        + " A PROVEEDOR CON RIF " + compra.getProveedor().getRif());
                auditoDB.create(auditoria);
                miLista.clear();
                compraController.setCompra(new Compra());
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra registrada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            } else {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al registrar compra", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            }
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe agregar minimo un producto a la lista", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }*/
    public void register()
    {
        if (db.create(compraInsumo))
        {
            compraInsumo= new CompraInsumo();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }

    
}

}      
    

    
 

