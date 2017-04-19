package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.TerminadoDB;
import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.database.VentaDB;
import com.pezfa.inventario.models.ProductoSalida;
import com.pezfa.inventario.models.Terminado;
import com.pezfa.inventario.models.Unidad;
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

    private Venta venta;
    private VentaDB db;
    private UnidadDB udb;
    private TerminadoDB tdb;
    private List<ProductoSalida> productos;
    private ProductoSalida producto;
    private List<ProductoSalida> salida;
    private Set<VentaDetalle> lista;
    private VentaUnidad ventaUnidad;
    private VentaTerminado ventaTerminado;
    private VentaDetalle ventaDetalle;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    private int cant;

    //constructor
    public VentaController()
    {
        db = new VentaDB();
        udb = new UnidadDB();
        tdb = new TerminadoDB();
        ventaDetalle = new VentaDetalle();
        lista = new HashSet<>();
        venta = new Venta();
        cant = 1;
    }

    public List<ProductoSalida> getSalida()
    {
        return salida;
    }

    public void setSalida(List<ProductoSalida> salida)
    {
        this.salida = salida;
    }

    public int getCant()
    {
        return cant;
    }

    public void setCant(int cant)
    {
        this.cant = cant;
    }

    public VentaUnidad getVentaUnidad()
    {
        return ventaUnidad;
    }

    public void setVentaUnidad(VentaUnidad ventaUnidad)
    {
        this.ventaUnidad = ventaUnidad;
    }

    public VentaTerminado getVentaTerminado()
    {
        return ventaTerminado;
    }

    public void setVentaTerminado(VentaTerminado ventaTerminado)
    {
        this.ventaTerminado = ventaTerminado;
    }

    public VentaDetalle getVentaDetalle()
    {
        return ventaDetalle;
    }

    public void setVentaDetalle(VentaDetalle ventaDetalle)
    {
        this.ventaDetalle = ventaDetalle;
    }

    public Set<VentaDetalle> getLista()
    {
        return lista;
    }

    public void setLista(Set<VentaDetalle> lista)
    {
        this.lista = lista;
    }

    public UsuarioController getUsuarioController()
    {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController)
    {
        this.usuarioController = usuarioController;
    }

    public double getTotal()
    {
        return this.lista.stream()
                .mapToDouble(x ->
                {
                    if (x instanceof VentaUnidad)
                    {
                        return ((VentaUnidad) x).getUnidad().getPrecio().doubleValue() * x.getCantidad();
                    } else if (x instanceof VentaTerminado)
                    {
                        return ((VentaTerminado) x).getTerminado().getPrecio().doubleValue() * x.getCantidad();
                    }
                    return 0;
                })
                .sum();
    }

    public void add()
    {
        VentaUnidad vu;
        VentaTerminado vt;

        venta.setUsuario(usuarioController.getSesion());
        if (this.producto instanceof Unidad)
        {
            vu = new VentaUnidad();
            vu.setUnidad((Unidad) this.producto);
            vu.setVenta(venta);
            vu.setCantidad(cant);
            lista.add(vu);
            cant = 1;
        } else if (this.producto instanceof Terminado)
        {
            vt = new VentaTerminado();
            vt.setTerminado((Terminado) this.producto);
            vt.setVenta(venta);
            vt.setCantidad(cant);
            lista.add(vt);
            cant = 1;
        }

        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('agregar').hide();");
    }

    public void remove()
    {
        lista.remove(ventaDetalle);
    }

    //logica para registrar un venta
    public void register()
    {
        if (this.lista.size() > 0)
        {
            salida = db.create_venta(lista);
            if (salida != null)
            {
                lista.clear();
                venta = new Venta();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta registrada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
                RequestContext con = RequestContext.getCurrentInstance();
                con.execute(" PF('productossalida').show()");
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

    public Venta getVenta()
    {
        return venta;
    }

    public void setVenta(Venta venta)
    {
        this.venta = venta;
    }

    public List<ProductoSalida> getProductos()
    {
        productos = new ArrayList<>();
        udb.read("from Unidad uni join fetch uni.compraEspecie deta join fetch deta.especie esp where esp.cantidad != 0")
                .stream().distinct().forEach(x ->
                {
                    x.setNombre(x.getCompraEspecie().getEspecie().getNombre());
                    x.setCodigo(x.getCompraEspecie().getEspecie().getCodigo());
                    x.setPrecio(x.getCompraEspecie().getEspecie().getPrecio());
                    productos.add((ProductoSalida) x);
                });
        tdb.read("from Terminado ter join fetch ter.producto produ where produ.cantidad != 0").stream()
                .distinct().forEach(x ->
                {
                    x.setNombre(x.getProducto().getNombre());
                    x.setCodigo(x.getProducto().getCodigo());
                    x.setPrecio(x.getProducto().getPrecio());
                    productos.add((ProductoSalida) x);
                });
        return productos;
    }

    public void setProductos(List<ProductoSalida> productos)
    {
        this.productos = productos;
    }

    public ProductoSalida getProducto()
    {
        return producto;
    }

    public void setProducto(ProductoSalida producto)
    {
        this.producto = producto;
    }

}
