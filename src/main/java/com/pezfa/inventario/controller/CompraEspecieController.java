package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.database.CompraEspecieDB;
import com.pezfa.inventario.models.Auditoria;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.CompraEspecie;
import java.io.Serializable;
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
public class CompraEspecieController implements Serializable
{

    private CompraEspecie compraEspecie;
    private Set<CompraEspecie> miLista;
    private List<CompraEspecie> compraEspecies;
    private List<CompraEspecie> detalleCompra;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    @ManagedProperty(value = "#{compraController}")
    private CompraController compraController;
    @ManagedProperty(value = "#{camionController}")
    private CamionController camionController;
    private CompraEspecieDB db;

    public CompraEspecieController()
    {
        compraEspecie = new CompraEspecie();
        miLista = new HashSet<CompraEspecie>();
        db = new CompraEspecieDB();
    }

    public CamionController getCamionController()
    {
        return camionController;
    }

    public void setCamionController(CamionController camionController)
    {
        this.camionController = camionController;
    }

    public double getTotal()
    {
        return this.miLista.stream()
                .mapToDouble(x -> x.getCosto().doubleValue() * x.getCantidad())
                .sum();
    }

    public double getCantidadDesubicados()
    {
        return this.getCompraEspecies().stream()
                .mapToDouble(x -> x.getCantidad() - x.getUbicados())
                .sum();
    }

    public double getCostoTransito()
    {
        return this.getCompraEspecies().stream()
                .mapToDouble(x -> (x.getCantidad() - x.getUbicados()) * x.getCosto().floatValue())
                .sum();

    }

    public void reset()
    {
        compraEspecie = new CompraEspecie();
    }

    public double getGastoTransito()
    {
        return this.getCompraEspecies().stream()
                .mapToDouble(x -> x.getCosto().doubleValue() * x.getCantidad())
                .sum();
    }

    public UsuarioController getUsuarioController()
    {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController)
    {
        this.usuarioController = usuarioController;
    }

    public double obtenerCostoTotal()
    {
        try
        {
            return this.compraEspecie.getCosto().floatValue() * this.compraEspecie.getCantidad();
        } catch (Exception e)
        {
            return 0;
        }
    }

    public CompraController getCompraController()
    {
        return compraController;
    }

    public void setCompraController(CompraController compraController)
    {
        this.compraController = compraController;
    }

    public Set<CompraEspecie> getMiLista()
    {
        return miLista;
    }

    public void setMiLista(Set<CompraEspecie> miLista)
    {
        this.miLista = miLista;
    }

    public CompraEspecie getCompraEspecie()
    {
        return compraEspecie;
    }

    public void setCompraEspecie(CompraEspecie compraEspecie)
    {
        this.compraEspecie = compraEspecie;
    }

    public List<CompraEspecie> getCompraEspecies()
    {
        compraEspecies = db.read("from CompraEspecie com join fetch com.especie join fetch com.compra");
        return compraEspecies;
    }

    public void setCompraEspecies(List<CompraEspecie> compraEspecies)
    {
        this.compraEspecies = compraEspecies;
    }

    public List<CompraEspecie> getDetalleCompra()
    {
        int id = compraController.getCompra().getId();
        detalleCompra = db.findBy(id);
        return detalleCompra;
    }
    
    public List<CompraEspecie> getDetalles()
    {
        int id = compraController.getCompra().getId();
        List<CompraEspecie> result =  db.read("from CompraEspecie ce join fetch ce.compra com join fetch ce.especie where com.id="+id);
        System.err.println(result);
        return result;
    } 

    public void setDetalleCompra(List<CompraEspecie> detalleCompra)
    {
        this.detalleCompra = detalleCompra;
    }

    public void add()
    {
        compraController.getCompra().setUsuario(usuarioController.getSesion());
        compraEspecie.setCompra(compraController.getCompra());
        compraEspecie.setUbicados(0.0);
        miLista.add(compraEspecie);
        compraEspecie = new CompraEspecie();
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('agregar').hide(); PF('registrar').hide();");
    }

    public void register()
    {
        if (this.miLista.size() > 0)
        {
            if (db.createList(miLista))
            {
                Auditoria auditoria = new Auditoria();
                AuditoriaDB auditoDB = new AuditoriaDB();
                Compra compra = compraController.getCompra();
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
            } else
            {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al registrar compra", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            }
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe agregar minimo un producto a la lista", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void remove()
    {
        miLista.remove(compraEspecie);
        compraEspecie = new CompraEspecie();
    }

}
