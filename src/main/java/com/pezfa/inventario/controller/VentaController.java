package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.VentaDB;
import com.pezfa.inventario.database.VentaTerminadoDB;
import com.pezfa.inventario.database.VentaUnidadDB;
import com.pezfa.inventario.models.Venta;
import com.pezfa.inventario.models.VentaDetalle;
import com.pezfa.inventario.models.VentaTerminado;
import com.pezfa.inventario.models.VentaUnidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class VentaController implements Serializable
{
    
    private Venta venta = null; // objeto a controlar
    private List<Venta> ventas = null; // lista de objetos de tipo ventas
    private VentaDB db;
    private VentaTerminadoDB vtdb;
    private VentaUnidadDB vudb;
    private Set<VentaDetalle> miLista;
    @ManagedProperty(value = "#{ventaTerminadoController}")
    private VentaTerminadoController ventaTerminadoController;
    @ManagedProperty(value = "#{ventaUnidadController}")
    private VentaUnidadController ventaUnidadController;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    private VentaDetalle ventaDetalle;
    private List<VentaDetalle> lista;

    //constructor
    public VentaController()
    {
        vudb = new VentaUnidadDB();
        vtdb = new VentaTerminadoDB();
        venta = new Venta(); //instancio el objeto venta
        db = new VentaDB();
        miLista = new HashSet<>();
    }
    
    public List<VentaDetalle> getLista()
    {
        lista = new ArrayList<>();
        vudb.read("from VentaUnidad vu join fetch vu.unidad join fetch vu.venta").forEach(x -> lista.add(x));
        vtdb.read("from VentaTerminado vt join fetch vt.terminado join fetch vt.venta").forEach(x -> lista.add(x));
        return lista;
    }
    
    public void setLista(List<VentaDetalle> lista)
    {
        this.lista = lista;
    }
    
    public VentaDetalle getVentaDetalle()
    {
        return ventaDetalle;
    }
    
    public void setVentaDetalle(VentaDetalle ventaDetalle)
    {
        this.ventaDetalle = ventaDetalle;
    }
    
    public VentaTerminadoController getVentaTerminadoController()
    {
        return ventaTerminadoController;
    }
    
    public void setVentaTerminadoController(VentaTerminadoController ventaTerminadoController)
    {
        this.ventaTerminadoController = ventaTerminadoController;
    }
    
    public Set<VentaDetalle> getMiLista()
    {
        return miLista;
    }
    
    public void setMiLista(Set<VentaDetalle> miLista)
    {
        this.miLista = miLista;
    }
    
    public UsuarioController getUsuarioController()
    {
        return usuarioController;
    }
    
    public void setUsuarioController(UsuarioController usuarioController)
    {
        this.usuarioController = usuarioController;
    }
    
    public VentaUnidadController getVentaUnidadController()
    {
        return ventaUnidadController;
    }
    
    public void setVentaUnidadController(VentaUnidadController ventaUnidadController)
    {
        this.ventaUnidadController = ventaUnidadController;
    }

    //getter y setter
    public Venta getVenta()
    {
        return venta;
    }
    
    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }
    
    public List<Venta> getVentas()
    {
        ventas = db.read("from Venta");
        return ventas;
    }
    
    public void setVentas(List<Venta> ventas)
    {
        this.ventas = ventas;
    }
    
    public void add()
    {
        venta.setUsuario(usuarioController.getSesion());
        ventaDetalle.setVenta(venta);
        miLista.add(ventaDetalle);
        ventaDetalle = new VentaDetalle();
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('agregar').hide(); PF('registrar2').hide();");
    }
    
    public void remove()
    {
        miLista.remove(ventaDetalle);
        ventaDetalle = new VentaDetalle();
    }

    //logica para registrar un venta
    public void register()
    {
        if (this.miLista.size() > 0)
        {
            if (db.createList(miLista))
            {
                miLista.clear();
                venta = new Venta();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta registrada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            } else
            {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al registrar venta", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            }
        } else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Debe agregar minimo un producto a la lista", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}
