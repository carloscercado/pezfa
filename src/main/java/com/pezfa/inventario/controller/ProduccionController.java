package com.pezfa.inventario.controller;

import com.pezfa.inventario.database.ProduccionDB;
import com.pezfa.inventario.database.UbicacionDB;
import com.pezfa.inventario.database.UnidadDB;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.Produccion;
import com.pezfa.inventario.models.ProductoSalida;
import com.pezfa.inventario.models.Ubicacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean
public class ProduccionController implements Serializable
{

    Produccion produccion = null; // objeto a controlar
    List<Produccion> producciones = null; // lista de objetos de tipo almace
    private ProduccionDB db;

    public ProduccionController()
    {
        produccion = new Produccion(); //instancio el objeto almacen
        db = new ProduccionDB();
    }
    
    public List<ProductoSalida> getInventario()
    {
        List<ProductoSalida> productos = new ArrayList<>();
        UbicacionDB tdb = new UbicacionDB();
        tdb.read("from Ubicacion uni join fetch uni.compraEspecie deta join"
                + " fetch deta.especie esp where esp.cantidad > 0").stream()
                    .distinct().forEach(x -> {
                        x.setNombre(x.getCompraEspecie().getEspecie().getNombre());
                        x.setCodigo(x.getCompraEspecie().getEspecie().getCodigo());
                        x.setPrecio(x.getCompraEspecie().getEspecie().getPrecio());
                        productos.add(x);
                    });
        return productos;
    }

    public Produccion getProduccion()
    {
        return produccion;
    }

    public void setProducciones(Produccion produccion)
    {
        this.produccion = produccion;
    }

    public List<Produccion> getProducciones()
    {
        producciones = db.read("from Produccion");
        return producciones;
    }

    public void register()
    {
        produccion.toUpperCase();
        if (db.create(produccion))
        {
            System.out.println("Registrado");
        } else
        {
            System.out.println("No Registrado");
        }
    }

    public void onRowSelect(SelectEvent event)
    {
        RequestContext con = RequestContext.getCurrentInstance();
        con.execute("PF('detalles').show();");
    }

    public void delete()
    {
        if (db.delete(produccion))
        {
            System.out.println("Eliminado");
        } else
        {
            System.out.println("No Eliminado");
        }
    }

    //logica para actualizar un almacen
    public void update()
    {
        produccion.toUpperCase();
        if (db.update(produccion))
        {
            System.out.println("Actualizador");
        } else
        {
            System.out.println("No Actualizado");
        }
    }

}
