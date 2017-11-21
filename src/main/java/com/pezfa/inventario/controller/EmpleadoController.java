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
    private PieChartModel SatisfaccionEmpleado;
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

 
    public PieChartModel getCapacitacionProfesionales() {
        capacitacionProfesionales = new PieChartModel();
        List<Empleado> profesionales;
        profesionales = db.read("from Empleado");
        float universitarios = profesionales.stream().filter(x -> x.getEducacion() == 3).count();
        float primaria = profesionales.stream().filter(x -> x.getEducacion() == 2).count();
        float sinEducacion = profesionales.stream().filter(x -> x.getEducacion() == 1).count();
        capacitacionProfesionales.set("Sin estudios", sinEducacion);
        capacitacionProfesionales.set("Educación basica y media", primaria);
        capacitacionProfesionales.set("Educación universitaria", universitarios);
        capacitacionProfesionales.setTitle("Educación de los empleados");
        capacitacionProfesionales.setLegendPosition("w");
        capacitacionProfesionales.setShowDataLabels(true);
        capacitacionProfesionales.setSeriesColors("ef6868, efde68, 79ef68");
        return capacitacionProfesionales;
    }
    public double getTotalUniver()
    {
       long numero = getEmpleadores().stream().filter(x -> x.getEducacion() == 3).count();
        if (numero == 3)
        {
          double univer = getEmpleadores().stream().mapToDouble(x -> x.getEducacion()).sum();
              
        }
        return numero;
    }
    public double getTotalPrimaria()
    {
       long numero = getEmpleadores().stream().filter(x -> x.getEducacion() == 2).count();
        if (numero == 2)
        {
          double basica = getEmpleadores().stream().mapToDouble(x -> x.getEducacion()).sum();
              
        }
        return numero;
    }
    public double getTotalSinEstudios()
    {
       long numero = getEmpleadores().stream().filter(x -> x.getEducacion() == 1).count();
        if (numero == 1)
        {
          double sineducacion = getEmpleadores().stream().mapToDouble(x -> x.getEducacion()).sum();
              
        }
        return numero;
    }
    
   public PieChartModel getSatisfaccionEmpleado()
    {
        SatisfaccionEmpleado = new PieChartModel();
        double muySatisfecho = (double) this.empleadores.stream().filter(x -> x.getSatisfecho().equals("MUY SATISFECHO")).count();
        double pocoSatisfecho = (double) this.empleadores.stream().filter(x -> x.getSatisfecho().equals("POCO SATISFECHO")).count();
        double noSatisfecho = (double) this.empleadores.stream().filter(x -> x.getSatisfecho().equals("NO SATISFECHO")).count();
        SatisfaccionEmpleado.set("Muy satisfechos", muySatisfecho);
        SatisfaccionEmpleado.set("Poco satisfechos", pocoSatisfecho);
        SatisfaccionEmpleado.set("No satisfechos", noSatisfecho);
        SatisfaccionEmpleado.setTitle("Satisfacción de los empleados");
        SatisfaccionEmpleado.setLegendPosition("w");
        SatisfaccionEmpleado.setShowDataLabels(true);
        SatisfaccionEmpleado.setSeriesColors("79ef68,efde68,ef6868");
        return SatisfaccionEmpleado;
    }
   
   public void setSatisfaccionEmpleado(PieChartModel SatisfaccionEmpleado)
    {
        this.SatisfaccionEmpleado = SatisfaccionEmpleado;
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
        empleadores = db.read("From Empleado order by primer_nombre, primer_apellido");
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

    private Object Empleadores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
