package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.models.Auditoria;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AuditoriaController implements Serializable
{

    private Auditoria auditoria = null;
    private List<Auditoria> auditorias = null;
    private AuditoriaDB db;
    private Date fecha = new Date();
    private String consulta = "from Auditoria audi join fetch audi.usuario user join fetch user.empleado where"
            + " month(audi.fecha)=" + (fecha.getMonth() + 1) + " and year(audi.fecha)=" + (fecha.getYear() + 1900)
            + " and day(audi.fecha)=" + (fecha.getDate());

    public AuditoriaController()
    {
        auditoria = new Auditoria();
        db = new AuditoriaDB();
    }

    public String getConsulta()
    {
        return consulta;
    }

    public Date getFecha()
    {
        return fecha;
    }

    public void setFecha(Date fecha)
    {
        this.fecha = fecha;
    }

    public void setConsulta(String consulta)
    {
        this.consulta = consulta;
    }

    public Auditoria getAuditoria()
    {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria)
    {
        this.auditoria = auditoria;
    }

    public List<Auditoria> getAuditorias()
    {
        auditorias = db.read(consulta+" order by audi.fecha");
        return auditorias;
    }

    public void setAuditorias(List<Auditoria> auditorias)
    {
        this.auditorias = auditorias;
    }

    public void register()
    {
        if (db.create(auditoria))
        {
            System.out.println("Registrado Correctamente");
        } else
        {
            System.out.println("No Se Ha Registrado");
        }
    }

    public void update()
    {
        if (db.update(auditoria))
        {
            System.out.println("Actualizado Correctamente");
        } else
        {
            System.out.println("No Se Ha Actualizado");
        }
    }

    public void delete()
    {
        if (db.delete(auditoria))
        {
            System.out.println("Eliminado Correctamente");
        } else
        {
            System.out.println("No Se Ha Eliminado");
        }
    }
}
