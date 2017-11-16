package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.UbicacionDB;
import com.pezfa.inventario.models.Ubicacion;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class UnidadController implements Serializable {

    private Ubicacion unidad = null; // objeto a controlar
    private List<Ubicacion> unidades = null; // lista de objetos de tipo unidades
    @ManagedProperty(value = "#{compraEspecieController}")
    private CompraEspecieController compraEspecieController;
    @ManagedProperty(value = "#{usuarioController}")
    private UsuarioController usuarioController;
    @ManagedProperty(value = "#{especieController}")
    private EspecieController especieController;
    private UbicacionDB db;

    public UnidadController() {
        unidad = new Ubicacion(); //instancio el objeto unidad
        db = new UbicacionDB();
    }

    public EspecieController getEspecieController() {
        return especieController;
    }

    public Set<Ubicacion> getEspecies(int id) {
        List<Ubicacion> lista;
        lista = db.read("from Ubicacion ubi join fetch ubi.compraEspecie cp join fetch cp.especie esp where ubi.cava="+id);
        Set<Ubicacion> resultado = new HashSet<>();
        for(Ubicacion obj: lista)
        {
            resultado.add(obj);
        }
        
        return resultado;
        
    }
    
    public List<Ubicacion> getEspeciesDetalles(int id, int especie) {
        return db.read("from Ubicacion ubi join fetch ubi.compraEspecie cp join fetch cp.especie esp join fetch cp.compra where ubi.peso != 0 and esp.id="+especie+" and ubi.cava="+id);

    }
    
    public double getCantidadCava(int id, int especie) {
        List<Ubicacion> lista = db.read("from Ubicacion ubi join fetch ubi.compraEspecie cp join fetch cp.especie esp join fetch cp.compra where ubi.peso != 0 and esp.id="+especie+" and ubi.cava="+id);
        return lista.stream().mapToDouble(x -> x.getPeso()).sum();

    }
   

    public void setEspecieController(EspecieController especieController) {
        this.especieController = especieController;
    }

    public Ubicacion getUnidad() {
        return unidad;
    }

    public void setUnidad(Ubicacion unidad) {
        this.unidad = unidad;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public CompraEspecieController getCompraEspecieController() {
        return compraEspecieController;
    }

    public void setCompraEspecieController(CompraEspecieController compraEspecieController) {
        this.compraEspecieController = compraEspecieController;
    }

    public List<Ubicacion> getUnidades() {
        int id = especieController.getEspecie().getId();
        unidades = db.read("from Ubicacion ubi join fetch ubi.compraEspecie cp"
                + " join fetch cp.especie esp join fetch ubi.cava cav join fetch cp.compra comp"
                + " join fetch comp.proveedor where esp.id=" + id);

        return unidades;
    }

    public void setUnidades(List<Ubicacion> unidades) {
        this.unidades = unidades;
    }

    //logica para registrar un unidad
    public void register() {
        if (unidad.getCava().getCapacidadDisponible() <= unidad.getPeso()) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Capacidad de la cava seleccionada es insuficiente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        } else {
            unidad.setCompraEspecie(compraEspecieController.getCompraEspecie());
            unidad.setEstado(Boolean.TRUE);
            unidad.setCodigo(UUID.randomUUID().toString());
            if (db.create(unidad)) {
                /*Auditoria auditoria = new Auditoria();
                AuditoriaDB auditoDB = new AuditoriaDB();
                auditoria.setUsuario(usuarioController.getUsuario());
                Date fecha = new Date();
                auditoria.setFecha(fecha);
                auditoria.setHora(fecha);
                auditoria.setTipo("REGISTRO EN INVENTARIO");
                auditoria.setDescripcion("REGISTRO DE " + unidad.getPeso() + " KG DE " + unidad.getNombre()
                        + " DE ORDEN " + unidad.getCompraEspecie().getCompra().getOrden() + " EN LA CAVA " + unidad.getCava().getNombre());
                auditoDB.create(auditoria);*/
                unidad = new Ubicacion();
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unidad ubicada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
                RequestContext con = RequestContext.getCurrentInstance();
                con.execute("PF('registrar').hide(); PF('registrar').hide();");
            } else {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al ubicar insumo", null);
                FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            }
        }
    }

}
