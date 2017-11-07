package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.DevolucionDB;
import com.pezfa.inventario.models.Devoluciones;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LinearAxis;

/**
 *
 * @author Romario
 */
@ManagedBean
@ViewScoped
public class DevolucionController implements Serializable {

    private Devoluciones devolucion;
    private List<Devoluciones> devoluciones;
    private DevolucionDB db;
    private CartesianChartModel devolucionesIndicador;

    public DevolucionController() {
        db = new DevolucionDB();
    }

    public CartesianChartModel getDevolucionesIndicador() {
        return cargarIndicador();
    }

    public void setDevolucionesIndicador(CartesianChartModel devolucionesIndicador) {
        this.devolucionesIndicador = devolucionesIndicador;
    }

    public Devoluciones getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Devoluciones devolucion) {
        this.devolucion = devolucion;
    }

    public List<Devoluciones> getDevoluciones() {
        List<Devoluciones> lista = db.read("from Devoluciones de where de.anio=date_part('year', now()) order by de.mes");
        return lista;
    }

    public void setDevoluciones(List<Devoluciones> devoluciones) {
        this.devoluciones = devoluciones;
    }

    public List<Devoluciones> getDevolucionesDb() {
        db = new DevolucionDB();
        List<Devoluciones> lista = db.read("from Devoluciones de where de.anio=date_part('year', now()) order by de.mes");
        return lista;
    }

    private CartesianChartModel cargarIndicador() {
        devolucionesIndicador = new BarChartModel();

        BarChartSeries ventas = new BarChartSeries();
        ventas.setLabel("Ventas");

        LineChartSeries devolucionesData = new LineChartSeries();
        devolucionesData.setLabel("Devoluciones");
        

        List<Devoluciones> datos = this.getDevolucionesDb();
        
        int mayor = 0;
        for (Devoluciones obj : datos) {
            ventas.set(obj.getMesString(), obj.getVentas());
            devolucionesData.set(obj.getMesString(), obj.getDevoluciones());
            if (obj.getVentas() > mayor) {
                mayor = obj.getVentas();
            }
        }
        if (ventas.getData().isEmpty())
        {
            ventas.set("Enero", 0);
        }
        
        

        devolucionesIndicador.addSeries(ventas);
        devolucionesIndicador.addSeries(devolucionesData);

        devolucionesIndicador.setTitle("Satisfacción de los clientes");
        devolucionesIndicador.setLegendPosition("ne");
        devolucionesIndicador.setAnimate(true);
        devolucionesIndicador.setMouseoverHighlight(false);
        Axis yAxis = devolucionesIndicador.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(mayor+10);
        yAxis.setLabel("Rango de satisfacción");

        return devolucionesIndicador;
    }
}
