package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.database.UbicacionDB;
import com.pezfa.inventario.database.VentaDB;
import com.pezfa.inventario.models.Auditoria;
import com.pezfa.inventario.models.ProductoSalida;
import com.pezfa.inventario.models.Unidad;
import com.pezfa.inventario.models.Ubicacion;
import com.pezfa.inventario.models.Venta;
import com.pezfa.inventario.models.VentaDetalle;
import com.pezfa.inventario.models.VentaUnidad;
import com.pezfa.inventario.models.VentaEspecie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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
    private UbicacionDB udb;
    private UnidadDB tdb;
    private List<ProductoSalida> productos;
    private ProductoSalida producto;
    private List<ProductoSalida> salida;
    private List<Venta> historico = null;
    private Set<VentaDetalle> lista;
    private VentaEspecie ventaUnidad;
    private VentaUnidad ventaTerminado;
    private VentaDetalle ventaDetalle;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    private double cant;

    //constructor
    public VentaController()
    {
        db = new VentaDB();
        udb = new UbicacionDB();
        tdb = new UnidadDB();
        ventaDetalle = new VentaDetalle();
        lista = new HashSet<>();
        venta = new Venta();
        venta.setFecha(new Date());
        cant = 1;
    }

    public List<Venta> getHistorico()
    {
        historico = db.read("from Venta ven join fetch ven.cliente "
                + "join fetch ven.venta_especie order by ven.fecha");

        return historico;
    }

    public void setHistorico(List<Venta> historico)
    {
        this.historico = historico;
    }
   
    public List<ProductoSalida> getSalida()
    {
        return salida;
    }
    
    public void onRowSelect(SelectEvent event) 
    {
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('detalles').show();");
    }

    public void setSalida(List<ProductoSalida> salida)
    {
        this.salida = salida;
    }

    public double getCant()
    {
        return cant;
    }

    public void setCant(double cant)
    {
        this.cant = cant;
    }

    public VentaEspecie getVentaUnidad()
    {
        return ventaUnidad;
    }

    public void setVentaUnidad(VentaEspecie ventaUnidad)
    {
        this.ventaUnidad = ventaUnidad;
    }

    public VentaUnidad getVentaTerminado()
    {
        return ventaTerminado;
    }

    public void setVentaTerminado(VentaUnidad ventaTerminado)
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
                            if (x instanceof VentaEspecie)
                            {
                                return ((VentaEspecie) x).getUbicacion().getPrecio().doubleValue() * x.getCantidad();
                            } else if (x instanceof VentaUnidad)
                            {
                                return ((VentaUnidad) x).getUnidad().getPrecio().doubleValue() * x.getCantidad();
                            }
                            return 0;
                })
                .sum();
    }

    public void add()
    {
        VentaEspecie vu;
        VentaUnidad vt;

        venta.setUsuario(usuarioController.getSesion());
        if (this.producto instanceof Ubicacion)
        {
            vu = new VentaEspecie();
            vu.setUbicacion((Ubicacion) this.producto);
            vu.setVenta(venta);
            vu.setCantidad(cant);
            lista.add(vu);
            cant = 1;
        } else if (this.producto instanceof Unidad)
        {
            vt = new VentaUnidad();
            vt.setUnidad((Unidad) this.producto);
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

    public void reset()
    {
        venta = new Venta();
    }

    public void validarFactura()
    {
        if (db.validarFactura(this.venta.getFactura()))
        {
            this.venta.setFactura("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrada esta venta", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    //logica para registrar un venta
    public void register()
    {
        if (this.lista.size() > 0)
        {
            salida = db.create_venta(lista);
            if (salida != null)
            {
                Auditoria auditoria = new Auditoria();
                AuditoriaDB auditoDB = new AuditoriaDB();

                auditoria.setUsuario(venta.getUsuario());
                auditoria.setFecha(venta.getFecha());
                auditoria.setHora(venta.getFecha());
                auditoria.setTipo("REGISTRO DE VENTAS");
                auditoria.setDescripcion("REGISTRO DE VENTA CON FACTURA " + venta.getFactura()
                        + " A CLIENTE CON RIF " + venta.getCliente().getRif());
                auditoDB.create(auditoria);
                lista.clear();
                venta = new Venta();
                venta.setFecha(new Date());
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
        try
        {
            productos = new ArrayList<>();
            udb.read("from Ubicacion uni join fetch uni.ventaEspecie deta join fetch deta.especie esp where esp.cantidad > 0")
                    .stream().distinct().forEach(x ->
                            {
                                x.setNombre(x.getCompraEspecie().getEspecie().getNombre());
                                x.setCodigo(x.getCompraEspecie().getEspecie().getCodigo());
                                x.setPrecio(x.getCompraEspecie().getEspecie().getPrecio());
                                productos.add((ProductoSalida) x);
                    });
            tdb.read("from Unidad ter join fetch ter.producto produ where produ.cantidad > 0").stream()
                    .distinct().forEach(x ->
                            {
                                x.setNombre(x.getProducto().getNombre());
                                x.setCodigo(x.getProducto().getCodigo());
                                x.setPrecio(x.getProducto().getPrecio());
                                productos.add((ProductoSalida) x);
                    });

            return productos;

        } catch (Exception e)
        {
            System.out.println("Lista de productos para ventas, entro en la excepcion: " + e.getMessage());
            return new ArrayList<>();
        }
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
