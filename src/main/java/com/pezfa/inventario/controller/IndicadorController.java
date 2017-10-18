package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CompraDB;
import com.pezfa.inventario.database.CompraEspecieDB;
import com.pezfa.inventario.database.IndicadorDB;
import com.pezfa.inventario.database.VentaEspecieDB;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.Indicador;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@ViewScoped
public class IndicadorController implements Serializable {

    private Indicador indicador = null;
    private Indicador capacidad = null;
    private Indicador satisfaccion = null;
    private List<Indicador> indicadores = null;
    private IndicadorDB db;
    private CompraEspecieDB db1;
    private VentaEspecieDB db2;
    private CompraDB db3;
    private Indicador ventasMensual;

    public IndicadorController() {
        indicador = new Indicador();
        db = new IndicadorDB();
        db1 = new CompraEspecieDB();
        db2 = new VentaEspecieDB();
        db3 = new CompraDB();
        capacidad = this.getCapacidad();
        satisfaccion = this.getSatisfaccion();

    }

    public Indicador getVentasMensual() {
        ventasMensual = db.read("from Indicador indi where indi.id=3").get(0);
        return ventasMensual;
    }

    public void setVentasMensual(Indicador ventasMensual) {
        this.ventasMensual = ventasMensual;
    }
    
    

    public Indicador getSatisfaccion() {
        satisfaccion = db.read("from Indicador indi where indi.id=2").get(0);
        return satisfaccion;
    }

    public void setSatisfaccion(Indicador satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    
    public Date getFechaInicial() {
        Compra compra = db3.read("from Compra cmp order by cmp.fecha").get(0);
        return compra.getFecha();
    }

    public int getDiasFuncionando(Date menor) {
        Date hoy = new Date();
        long diferencia = hoy.getTime() - menor.getTime();
        long dias = diferencia / (1000 * 60 * 60 * 24);
        return (int) dias;
    }

    public Indicador getCapacidad() {
        capacidad = db.read("from Indicador indi where indi.id=1").get(0);
        return capacidad;
    }

    public void setCapacidad(Indicador capacidad) {
        this.capacidad = capacidad;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public double getTotalInventarioActual() {
        return db1.read("from CompraEspecie cp join fetch cp.especie").stream()
                .mapToDouble(x -> x.getEspecie().getCantidad() * x.getCosto().doubleValue()).sum();
    }

    public double getTotalVendido() {
        return db2.read("from VentaEspecie ve join fetch ve.ubicacion ubi join fetch ubi.compraEspecie").stream()
                .mapToDouble(x -> x.getCantidad() * x.getUbicacion().getCompraEspecie().getCosto().doubleValue()).sum();
    }

    public double getCostoVendido() {
        return db2.read("from VentaEspecie ve join fetch ve.ubicacion ubi join fetch ubi.compraEspecie cp join fetch cp.especie").stream()
                .mapToDouble(x -> x.getCantidad() * x.getUbicacion().getCompraEspecie().getEspecie().getPrecio().doubleValue()).sum();
    }

    public int getRotacion() {
        double actual = this.getTotalInventarioActual();
        double inventarioPromedio = ((actual+ this.getTotalVendido()) / 2);
        double rotacion = (this.getCostoVendido() / inventarioPromedio);

        double resultado = (this.getDiasFuncionando(this.getFechaInicial()) / rotacion);
        if (resultado>0)
            return (int) resultado;
        else
            return 0;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public List<Indicador> getIndicadores() {
        indicadores = db.read("from Indicador");
        return indicadores;
    }

    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }

    public void reset() {
        indicador = new Indicador();
    }

    public void update() {
        if (db.update(indicador)) {
            indicador = new Indicador();
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
