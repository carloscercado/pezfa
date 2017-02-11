package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.AuditoriaDB;
import com.pezfa.inventario.models.Auditoria;
import java.io.Serializable;
import java.util.List;

public class AuditoriaController implements Serializable
{
    private Auditoria auditoria = null;
    private List<Auditoria> auditorias = null;
    
    public AuditoriaController()
    {
        auditoria = new Auditoria ();
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
       auditorias = AuditoriaDB.read();
       return auditorias;
    }      
    public void setAuditorias(List<Auditoria> auditorias)
    {
        this.auditorias = auditorias;
    }
    
    public void register()
    {
        if(AuditoriaDB.create(auditoria))
        {
            System.out.println("Registrado Correctamente");
        }else
        {
            System.out.println("No Se Ha Registrado");
        }
    }
    
    public void update()
    {
        if(AuditoriaDB.update(auditoria))
        {
            System.out.println("Actualizado Correctamente");
        }else
        {
            System.out.println("No Se Ha Actualizado");
        }
    }
    
    public void delete()
    {
        if(AuditoriaDB.delete(auditoria))
        {
            System.out.println("Eliminado Correctamente");
        }else
        {
            System.out.println("No Se Ha Eliminado");
        }
    }
}