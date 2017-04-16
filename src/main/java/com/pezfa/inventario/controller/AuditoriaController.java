package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.models.Auditoria;
import java.io.Serializable;
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
    
    public AuditoriaController()
    {
        auditoria = new Auditoria ();
        db = new AuditoriaDB();
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
       auditorias = db.read("from Auditoria");
       return auditorias;
    }      
    public void setAuditorias(List<Auditoria> auditorias)
    {
        this.auditorias = auditorias;
    }
    
    public void register()
    {
        if(db.create(auditoria))
        {
            System.out.println("Registrado Correctamente");
        }else
        {
            System.out.println("No Se Ha Registrado");
        }
    }
    
    public void update()
    {
        if(db.update(auditoria))
        {
            System.out.println("Actualizado Correctamente");
        }else
        {
            System.out.println("No Se Ha Actualizado");
        }
    }
    
    public void delete()
    {
        if(db.delete(auditoria))
        {
            System.out.println("Eliminado Correctamente");
        }else
        {
            System.out.println("No Se Ha Eliminado");
        }
    }
}