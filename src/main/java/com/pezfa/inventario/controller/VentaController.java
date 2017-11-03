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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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
    private BarChartModel ventasAnual;
    private BarChartModel ventasAnualKilos;
    private Date fecha = new Date();
    private List<Ubicacion> listaInterna = new ArrayList();

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

    public BarChartModel getVentasAnual()
    {
        ventasAnual = this.inicializarGrafica()[0];
        return ventasAnual;
    }

    public void setVentasAnual(BarChartModel ventasAnual)
    {
        this.ventasAnual = ventasAnual;
    }

    public BarChartModel getVentasAnualKilos()
    {
        ventasAnualKilos = this.inicializarGrafica()[1];
        return ventasAnualKilos;
    }

    public void setVentasAnualKilos(BarChartModel ventasAnualKilos)
    {
        this.ventasAnualKilos = ventasAnualKilos;
    }

    public BarChartModel[] inicializarGrafica()
    {
        Double[] meses = this.getIndicadorVentas();
        Double[] mesesKilos = this.getIndicadorVentasKilos();

        BarChartModel[] modelos = new BarChartModel[2];

        ventasAnual = initBarModel(meses);
        ventasAnualKilos = initBarModel(mesesKilos);
        ventasAnual.setAnimate(true);
        ventasAnual.setNegativeSeriesColors("22c80a");

        ventasAnualKilos.setAnimate(true);
        ventasAnualKilos.setNegativeSeriesColors("22c80a");

        Axis yAxis = ventasAnual.getAxis(AxisType.Y);
        yAxis.setTickFormat("%#.2f");
        yAxis.setLabel("Bolivares");

        Axis yAxis2 = ventasAnualKilos.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(this.getMontoMayor(meses));
        yAxis2.setMin(0);
        yAxis2.setMax(this.getMontoMayor(mesesKilos));
        yAxis2.setLabel("Kilos");
        modelos[0] = ventasAnual;
        modelos[1] = ventasAnualKilos;
        return modelos;
    }

    private BarChartModel initBarModel(Double[] meses)
    {
        BarChartModel model = new BarChartModel();

        ChartSeries res = new ChartSeries();
        res.set("Ene", meses[0]);
        res.set("Feb", meses[1]);
        res.set("Mar", meses[2]);
        res.set("Abr", meses[3]);
        res.set("May", meses[4]);
        res.set("Jun", meses[5]);
        res.set("Jul", meses[6]);
        res.set("Ago", meses[7]);
        res.set("Sep", meses[8]);
        res.set("Oct", meses[9]);
        res.set("Nov", meses[10]);
        res.set("Dic", meses[11]);
        model.addSeries(res);

        return model;
    }

    public Double[] getIndicadorVentas()
    {
        Double[] meses = new Double[12];
        int anio_actual = new Date().getYear() + 1900;
        for (int i = 1; i <= 12; i++)
        {
            List<Venta> res = db.read("from Venta ven where month(ven.fecha)=" + i + " and year(ven.fecha)=" + anio_actual);
            double valor = res.stream().mapToDouble(x -> x.getIngreso().doubleValue()).sum();
            meses[i - 1] = valor;
        }
        return meses;
    }

    public Double getValorDelMes(int mes)
    {
        Double[] meses = this.getIndicadorVentas();
        return meses[mes];
    }

    public Double getValorDelMesKilo(int mes)
    {
        Double[] meses = this.getIndicadorVentasKilos();
        return meses[mes];
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public String getMes(int mes)
    {
        Map meses = new HashMap();
        meses.put(1, "Enero");
        meses.put(2, "Febrero");
        meses.put(3, "Marzo");
        meses.put(4, "Abril");
        meses.put(5, "Mayo");
        meses.put(6, "Junio");
        meses.put(7, "Julio");
        meses.put(8, "Agosto");
        meses.put(9, "Septiembre");
        meses.put(10, "Octubre");
        meses.put(11, "Noviembre");
        meses.put(12, "Diciembre");
        return meses.get(mes).toString();
    }

    public Double getTotalVentas()
    {
        Double[] meses = this.getIndicadorVentas();
        double total = 0.0;
        for (int i = 0; i < 12; i++)
        {
            total = total + meses[i];
        }
        return total;
    }

    public Double getTotalVentasKilos()
    {
        Double[] meses = this.getIndicadorVentasKilos();
        double total = 0.0;
        for (int i = 0; i < 12; i++)
        {
            total = total + meses[i];
        }
        return total;
    }

    public double getProcentanjeVentaEntreMeses(int actual, int anterior)
    {
        if (anterior < 0)
            anterior = 0;
        Double[] meses = this.getIndicadorVentas();
        double valor = ((meses[actual] / meses[anterior]) - 1) * 100;
        if (Double.isInfinite(valor) || Double.isNaN(valor))
        {
            valor = 0;
        }
        return CavaController.redondear(valor, 2);
    }
    
    public double getProcentanjeVentaEntreMesesKilos(int actual, int anterior)
    {
        if (anterior < 0)
            anterior = 0;
        Double[] meses = this.getIndicadorVentasKilos();
        double valor = ((meses[actual] / meses[anterior]) - 1) * 100;
        if (Double.isInfinite(valor) || Double.isNaN(valor))
        {
            valor = 0;
        }
        return CavaController.redondear(valor, 2);
    }

    public List<Map.Entry<String, Double>> getVentasMes()
    {
        Double[] meses = this.getIndicadorVentas();
        Map meses2 = new HashMap();
        meses2.put(1, meses[0]);
        meses2.put(2, meses[1]);
        meses2.put(3, meses[2]);
        meses2.put(4, meses[3]);
        meses2.put(5, meses[4]);
        meses2.put(6, meses[5]);
        meses2.put(7, meses[6]);
        meses2.put(8, meses[7]);
        meses2.put(9, meses[8]);
        meses2.put(10, meses[9]);
        meses2.put(11, meses[10]);
        meses2.put(12, meses[11]);

        Set<Map.Entry<String, Double>> salidas = meses2.entrySet();
        salidas.stream().mapToDouble(x -> CavaController.redondear(x.getValue(), 2)).sum();
        return new ArrayList<Map.Entry<String, Double>>(salidas);
    }

    public List<Map.Entry<String, Double>> getVentasMesKilo()
    {
        Double[] meses = this.getIndicadorVentasKilos();
        Map meses2 = new HashMap();
        meses2.put(1, meses[0]);
        meses2.put(2, meses[1]);
        meses2.put(3, meses[2]);
        meses2.put(4, meses[3]);
        meses2.put(5, meses[4]);
        meses2.put(6, meses[5]);
        meses2.put(7, meses[6]);
        meses2.put(8, meses[7]);
        meses2.put(9, meses[8]);
        meses2.put(10, meses[9]);
        meses2.put(11, meses[10]);
        meses2.put(12, meses[11]);

        Set<Map.Entry<String, Double>> salidas = meses2.entrySet();
        return new ArrayList<Map.Entry<String, Double>>(salidas);
    }

    public Double[] getIndicadorVentasKilos()
    {
        Double[] meses = new Double[12];
        int anio_actual = new Date().getYear() + 1900;
        for (int i = 1; i <= 12; i++)
        {
            List<Venta> res = db.read("from Venta ven where month(ven.fecha)=" + i + " and year(ven.fecha)=" + anio_actual);
            double valor = res.stream().mapToDouble(x -> x.getKiloTotal()).sum();
            meses[i - 1] = valor;
        }
        return meses;
    }

    public double getMontoMayor(Double[] meses)
    {
        double mayor = 0;
        for (int i = 0; i < 12; i++)
        {
            if (meses[i] > mayor)
            {
                mayor = meses[i];
            }
        }
        return mayor;
    }

    public List<Venta> getHistorico()
    {
        historico = db.read("from Venta ven join fetch ven.cliente");

        return historico;
    }

    public void setHistorico(List<Venta> historico)
    {
        this.historico = historico;
    }

    public void onRowSelect(SelectEvent event)
    {
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('detalles').show();");
    }

    public List<ProductoSalida> getSalida()
    {
        return salida;
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
                    return ((VentaEspecie) x).getUbicacion().getPrecio().doubleValue() * x.getCantidad();
                })
                .sum();
    }

    public void add()
    {
        VentaEspecie vu;
        VentaUnidad vt;
        RequestContext con = RequestContext.getCurrentInstance();

        venta.setUsuario(usuarioController.getSesion());
        if (this.producto instanceof Ubicacion)
        {
            long numero = listaInterna.stream().filter(x -> x.getId() == this.producto.getId()).count();
            if (numero > 0)
            {
                con.execute("PF('agregar').hide();");
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este producto ya fue agregado", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            } else
            {
                listaInterna.add((Ubicacion) this.producto);
                vu = new VentaEspecie();
                vu.setUbicacion((Ubicacion) this.producto);
                vu.setCantidad(cant);
                lista.add(vu);
                cant = 1;
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto agregado exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            }
        } else if (this.producto instanceof Unidad)
        {
            vt = new VentaUnidad();
            vt.setUnidad((Unidad) this.producto);
            vt.setCantidad(cant);

            lista.add(vt);
            cant = 1;
        }
        con.execute("PF('agregar').hide();");
    }

    public void remove()
    {
        listaInterna.clear();
        lista.clear();
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
            salida = db.create_venta(lista, venta);
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
                this.remove();
                venta = new Venta();
                venta.setFecha(new Date());
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta registrada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
                RequestContext con = RequestContext.getCurrentInstance();

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

    public void registrarDevolucion()
    {
        venta.setDevuelta(true);
        db.update(venta);
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Devolucion registrada exitosamente", null);
        FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('devolucion').hide();");
        con.update("formulario:tabla");
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
            udb.read("from Ubicacion uni join fetch uni.compraEspecie deta join fetch deta.especie esp where esp.cantidad > 0")
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
