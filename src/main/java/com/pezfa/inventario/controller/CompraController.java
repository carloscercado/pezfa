package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CompraDB;
import com.pezfa.inventario.models.Camion;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.Empleado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@ViewScoped
public class CompraController implements Serializable
{

    private Compra compra = null;
    private List<Compra> compras = null;
    private List<Compra> historico = null;
    private CompraDB db;
    private Date fecha;

    public CompraController()
    {
        compra = new Compra();
        compra.setCamion(new Camion());
        compra.setOrden(this.getCodigoOrdenCompra());
        compra.setChofer(new Empleado());
        db = new CompraDB();
    }
    
     public String getCodigoOrdenCompra()
    {
        //El 8 es el tamaño de el codigo, maximo 32
        return (String) UUID.randomUUID().toString().subSequence(0, 6);
    }

    public List<Compra> getHistorico()
    {
        historico = db.read("from Compra comprita join fetch comprita.proveedor "
                + "join fetch comprita.chofer join fetch comprita.camion order by comprita.fecha");
        return historico;
    }
     public void validarOrden()
    {
        if (db.validarOrden(this.compra.getOrden()))
        {
            this.compra.setOrden("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya esta registrada esta compra", null);
            FacesContext.getCurrentInstance().addMessage("mensaje", mensaje);
        }
    }

    public Date getFecha()
    {
        return new Date();
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    
    public void setHistorico(List<Compra> historico)
    {
        this.historico = historico;
    }

    public Compra getCompra()
    {
        return compra;
    }

    public void setCompra(Compra compra)
    {
        this.compra = compra;
    }

    public List<Compra> getCompras()
    {
        compras = db.read("from Compra comprita join fetch comprita.proveedor join fetch comprita.usuario where comprita.estado = 'COMPRADO'");
        return compras;
    }

    public void onRowSelect(SelectEvent event)
    {
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('detalles').show();");
    }

    public void setCompras(List<Compra> compras)
    {
        this.compras = compras;
    }

    public void register()
    {
        if (db.create(compra))
        {
            System.out.println("Su Compra Ha Sido Registrada");
        } else
        {

        }
    }

}
