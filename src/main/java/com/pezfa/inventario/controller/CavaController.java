package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CavaDB;
import com.pezfa.inventario.models.Cava;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class CavaController implements Serializable {

    private Cava cava = null;
    private List<Cava> cavas = null;
    private List<Cava> cavasFiltro = null;
    private CavaDB db;
    @ManagedProperty(value = "#{almacenController}")
    private AlmacenController almacenController;
    private PieChartModel capacidadIndicador;

    public PieChartModel getCapacidadIndicador() {

        capacidadIndicador = new PieChartModel();
        double capacidad = this.getCavas().stream().mapToDouble(x -> x.getCapacidad()).sum();

        double disponible = this.getCavas().stream().mapToDouble(x -> x.getCapacidadDisponible()).sum();

        capacidadIndicador.set("Espacio utilizado", disponible);
        capacidadIndicador.set("Espacio libre", capacidad);
        capacidadIndicador.setTitle("Disponibilidad de Almacenes");
        capacidadIndicador.setLegendPosition("w");
        capacidadIndicador.setShowDataLabels(true);
        capacidadIndicador.setSeriesColors("79ef68, ef6868");
        return capacidadIndicador;

    }

    public void setCapacidadIndicador(PieChartModel capacidadIndicador) {
        this.capacidadIndicador = capacidadIndicador;
    }

    public CavaController() {
        db = new CavaDB();
        cava = new Cava();
    }

    public double getCapacidad() {
        double totalCapacidad = this.getCavas().stream().mapToDouble(x -> x.getCapacidad()).sum();
        double totalDisponible = this.getCavas().stream().mapToDouble(x -> x.getCapacidadDisponible()).sum();
        double resultado = ((totalDisponible / totalCapacidad) * 100);
        return CavaController.redondear(resultado, 2);
    }

    public AlmacenController getAlmacenController() {
        return almacenController;
    }

    public void setAlmacenController(AlmacenController almacenController) {
        this.almacenController = almacenController;
    }
    
    public double getCapacidadDisponibleAlmacen(int id)
    {
        List<Cava> cavas = db.read("from Cava cav where cav.id ="+id);
        return cavas.stream().mapToDouble(x -> x.getCapacidadDisponible()).sum();
    }
    
    public double getCapacidadAlmacen(int id)
    {
        List<Cava> cavas = db.read("from Cava cav where cav.id ="+id);
        return cavas.stream().mapToDouble(x -> x.getCapacidad()).sum();
    }

    public static double redondear(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd;
        try {
            bd = new BigDecimal(value);
        } catch (java.lang.NumberFormatException e) {
            bd = new BigDecimal(0);
        }
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double redondearFlotanteParaElFront(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public List<Cava> getCavasFiltro() {
        try {
            int almacen = almacenController.getAlmacen().getId();
            cavasFiltro = db.read("from Cava cava join fetch cava.almacen al where al.id=" + almacen);
            return cavasFiltro;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void setCavasFiltro(List<Cava> cavasFiltro) {
        this.cavasFiltro = cavasFiltro;
    }

    public Cava getCava() {
        return cava;
    }

    public void setCava(Cava cava) {
        this.cava = cava;
    }

    public List<Cava> getCavas() {
        cavas = db.read("from Cava cavita join fetch cavita.almacen");
        return cavas;
    }
    public double getEspacioFisico(){
        return this.getCavas().stream().mapToDouble(x -> x.getCapacidadDisponible()).sum();
    }

    public double getCapacidadTotal() {
        return this.getCavas().stream().mapToDouble(x -> x.getCapacidad()).sum();
    }

    public void setCavas(List<Cava> cavas) {
        this.cavas = cavas;
    }

    public void reset() {
        cava = new Cava();
    }

    public void register() {

        cava.setCapacidadDisponible(cava.getCapacidad());
        cava.toUpperCase();
        if (db.create(cava)) {

            cava = new Cava();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('registrar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser guardado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void update() {
        cava.toUpperCase();
        if (cava.getCapacidadDisponible() < cava.getCapacidad()) {
            cava.setCapacidadDisponible(cava.getCapacidad() - cava.getCapacidadDisponible());
        } else {
            cava.setCapacidadDisponible(cava.getCapacidad());

        }

        if (db.update(cava)) {
            cava = new Cava();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void delete() {
        if (db.delete(cava)) {
            cava = new Cava();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('eliminar').hide();");
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN, "Este registro no puede ser eliminado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}
