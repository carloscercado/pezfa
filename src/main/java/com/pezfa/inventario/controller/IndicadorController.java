package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.IndicadorDB;
import com.pezfa.inventario.models.Indicador;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class IndicadorController implements Serializable
{
    private Indicador indicador = null;
    private List<Indicador> indicadores = null;
    private IndicadorDB db;
    
    public IndicadorController()
    {
        indicador = new Indicador(); 
        db = new IndicadorDB();
        
    }

    
    public Indicador getIndicador() {
        return indicador;
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
    
   
    
    public void reset()
    {
        indicador = new Indicador();
    }
    
    public void update()
    {
        if(db.update(indicador))
        {
            indicador = new Indicador();
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro modificado exitosamente", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
            RequestContext con = RequestContext.getCurrentInstance();
            con.execute("PF('modificar').hide();");
        }else
        {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro no puede ser modificado", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }
}