package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.EmpleadoDB;
import com.pezfa.inventario.models.Empleado;
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
public class EmpleadoController implements Serializable {

    private PieChartModel capacitacionProfesionales;
    private PieChartModel capacitacionNoProfesionales;
    private Empleado empleado = null;
    private List<Empleado> empleadores = null;
    private List<Empleado> choferes = null;
    private EmpleadoDB db;
    private Date fecha = new Date();

    //constructor
    public EmpleadoController() {
        db = new EmpleadoDB();
        empleado = new Empleado();
    }

    public void setCapacitacionProfesionales(PieChartModel capacitacionProfesionales) {
        this.capacitacionProfesionales = capacitacionProfesionales;
    }

    public void setCapacitacionNoProfesionales(PieChartModel capacitacionNoProfesionales) {
        this.capacitacionNoProfesionales = capacitacionNoProfesionales;
    }

    public PieChartModel getCapacitacionProfesionales() {
        capacitacionProfesionales = new PieChartModel();
        List<Empleado> profesionales;
        profesionales = db.read("from Empleado emp where emp.cargo!='CHOFER'");
        float universitarios = profesionales.stream().filter(x -> x.getEducacion() == 4).count();
        float secundaria = profesionales.stream().filter(x -> x.getEducacion() == 3).count();
        float primaria = profesionales.stream().filter(x -> x.getEducacion() == 2).count();
        float sinEducacion = profesionales.stream().filter(x -> x.getEducacion() == 1).count();
        capacitacionProfesionales.set("Sin estudios", sinEducacion);
        capacitacionProfesionales.set("Primaria", primaria);
        capacitacionProfesionales.set("Secundaria", secundaria);
        capacitacionProfesionales.set("Universitaria", universitarios);
        capacitacionProfesionales.setTitle("Profesionales");
        capacitacionProfesionales.setLegendPosition("w");
        capacitacionProfesionales.setShowDataLabels(true);
        capacitacionProfesionales.setSeriesColors("ef6868, 79ef68, 68c8ef, efde68");
        return capacitacionProfesionales;
    }

    public PieChartModel getCapacitacionNoProfesionales() {
        capacitacionNoProfesionales = new PieChartModel();
        List<Empleado> noProfesionales;
        noProfesionales = db.read("from Empleado emp where emp.cargo='CHOFER'");
        float universitarios = noProfesionales.stream().filter(x -> x.getEducacion() == 3).count();
        float secundaria = noProfesionales.stream().filter(x -> x.getEducacion() == 2).count();
        float primaria = noProfesionales.stream().filter(x -> x.getEducacion() == 1).count();
        float sinEducacion = noProfesionales.stream().filter(x -> x.getEducacion() == 0).count();
        capacitacionNoProfesionales.set("Sin estudios", sinEducacion);
        capacitacionNoProfesionales.set("Primaria", primaria);
        capacitacionNoProfesionales.set("Secundaria", secundaria);
        capacitacionNoProfesionales.set("Universitaria", universitarios);
        capacitacionNoProfesionales.setTitle("No Profesionales");
        capacitacionNoProfesionales.setLegendPosition("w");
        capacitacionNoProfesionales.setShowDataLabels(true);
        capacitacionNoProfesionales.setSeriesColors("ef6868, 79ef68, 68c8ef, efde68");
        return capacitacionNoProfesionales;
    }

    public List<Empleado> getChoferes() {
        choferes = db.read("from Empleado emp where emp.cargo='CHOFER'");
        return choferes;
    }

    public void setChoferes(List<Empleado> choferes) {
        this.choferes = choferes;
    }

    //getter y setter
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Empleado> getEmpleadores() {
        empleadores = db.read("From Empleado");
        return empleadores;
    }

    public void setEmpleadores(List<Empleado> empleadores) {
        this.empleadores = empleadores;
    }

    public void reset() {
        System.out.println("Sin limpiar");
        empleado = new Empleado();
        System.out.println("Reseteado");
    }

    public void validarCedula() {
        if (db.validarCedula(this.empleado.getCedula())) {
            this.empleado.setCedula("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrado este empleado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public void register() {
        empleado.toUpperCase();
        if (db.create(empleado)) {
            empleado = new Empleado();
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
        empleado.toUpperCase();
        if (db.update(empleado)) {
            empleado = new Empleado();
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
        if (db.delete(empleado)) {
            empleado = new Empleado();
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
