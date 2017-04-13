package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.CompraEspecieDB;
import com.pezfa.inventario.models.CompraEspecie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class CompraEspecieController implements Serializable
{

   private CompraEspecie compraEspecie = null;
   private List<CompraEspecie> compraEspecies = null;
   private List<CompraEspecie> detalleCompra = null;
   @ManagedProperty(value="#{compraController}")
   private CompraController compraController;

    private CompraEspecie compraEspecie = null;
    private List<CompraEspecie> compraEspecies = null;
    private List<CompraEspecie> miLista = null;
    @ManagedProperty(value = "#{compraController}")
    private CompraController compraController;
    private List<CompraEspecie> detalleCompra = null;


    public CompraEspecieController()
    {
        compraEspecie = new CompraEspecie();
        miLista = new ArrayList<CompraEspecie>();
    }

    public CompraController getCompraController()
    {
        return compraController;
    }

    public void setCompraController(CompraController compraController)
    {
        this.compraController = compraController;
    }

    
    public List<CompraEspecie> getMiLista()
    {
        return miLista;
    }

    public void setMiLista(List<CompraEspecie> miLista)
    {
        this.miLista = miLista;
    }

    public CompraController getCompraController() {
        return compraController;
    }

    public void setCompraController(CompraController compraController) {
        this.compraController = compraController;
    }

    
    public CompraEspecie getCompraEspecie()
    {
        return compraEspecie;
    }

    public void setCompraEspecie(CompraEspecie compraEspecie)
    {
        this.compraEspecie = compraEspecie;
    }

    public List<CompraEspecie> getCompraEspecies()
    {
        compraEspecies = CompraEspecieDB.read();
        return compraEspecies;
    }

    public void setCompraEspecies(List<CompraEspecie> compraEspecies)
    {
        this.compraEspecies = compraEspecies;
    }

    public List<CompraEspecie> getDetalleCompra()
    { 
        int id = compraController.getCompra().getId();
        detalleCompra = CompraEspecieDB.findBy(id);
        return detalleCompra;
    }

    public void setDetalleCompra(List<CompraEspecie> detalleCompra)
    {
        this.detalleCompra = detalleCompra;
    }
    
    public void register()
    {
        if (CompraEspecieDB.createList(miLista))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }
<<<<<<< HEAD
    
    public void add()
    {
        compraEspecie.setCompra(compraController.getCompra());
        miLista.add(compraEspecie);
        compraEspecie = new CompraEspecie();
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('agregar').hide(); PF('registrar').hide();");
        
    }
    
    public void remove()
    {
        miLista.remove(compraEspecie);
        compraEspecie = new CompraEspecie();        
    }

=======
 
>>>>>>> f0aa74b23f4d2b66e70021af33fb0b8731ec3b98
    public void delete()
    {
        if (CompraEspecieDB.delete(compraEspecie))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    public void update()
    {
        if (CompraEspecieDB.update(compraEspecie))
        {
            System.out.println("Actualizado");
        } else
        {
            System.out.println("No Actualizado");
        }
    }
}
