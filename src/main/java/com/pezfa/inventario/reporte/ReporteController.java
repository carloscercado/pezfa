package com.pezfa.inventario.reporte;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author Carlos Cercado
 * @email cercadocarlos@gmail.com
 */
@ManagedBean
@ApplicationScoped
public class ReporteController implements Serializable
{
    private Date desde;
    private Date hasta;

    public ReporteController()
    {
    }    
    
    public Date getDesde()
    {
        return desde;
    }

    public void setDesde(Date desde)
    {
        this.desde = desde;
    }

    public Date getHasta()
    {
        return hasta;
    }

    public void setHasta(Date hasta)
    {
        this.hasta = hasta;
    }
    
    

}
