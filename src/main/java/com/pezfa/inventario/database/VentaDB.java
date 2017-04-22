package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Producto;
import com.pezfa.inventario.models.ProductoSalida;
import com.pezfa.inventario.models.Unidad;
import com.pezfa.inventario.models.Ubicacion;
import com.pezfa.inventario.models.Venta;
import com.pezfa.inventario.models.VentaDetalle;
import com.pezfa.inventario.models.VentaUnidad;
import com.pezfa.inventario.models.VentaEspecie;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.FlushMode;
import org.hibernate.Session;

/**
 *
 * @author Romario Guerrero
 */
public class VentaDB implements Crud<Venta>
{

    public List<ProductoSalida> create_venta(Set<VentaDetalle> objs)
    {
        Session sesion = null;
        List<ProductoSalida> lista = new ArrayList<>();
        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Venta venta = null;
            for (VentaDetalle obj : objs)
            {
                venta = obj.getVenta();
            }
            venta.setIngreso(BigDecimal.ZERO);
            sesion.save(venta);
            sesion.getTransaction().commit();
            sesion.close();
            for (VentaDetalle obj : objs)
            {
                for (int i = 1; i <= obj.getCantidad(); i++)
                {
                    sesion = HibernateUtil.getSesion().openSession();
                    sesion.beginTransaction();
                    System.out.println("Vuelta " + i);
                    if (obj instanceof VentaUnidad)
                    {
                        String codigo = ((VentaUnidad) obj).getUnidad().getProducto().getCodigo();
                        Unidad terminado = this.getTerminado(codigo);
                        ((VentaUnidad) obj).setUnidad(terminado);
                        sesion.save((VentaUnidad) obj);
                        lista.add(terminado);
                    } else if (obj instanceof VentaEspecie)
                    {
                        String codigo = ((VentaEspecie) obj).getUbicacion().getCompraEspecie().getEspecie().getCodigo();
                        Ubicacion unidad = this.getUnidad(codigo);
                        ((VentaEspecie) obj).setUbicacion(unidad);
                        sesion.save((VentaEspecie) obj);
                        lista.add(unidad);
                    } else
                    {
                        System.err.println("INSTANCIA DE NADIE");
                        throw new Exception("No es instancia de nadie");
                    }
                    sesion.getTransaction().commit();
                    sesion.close();
                }
            }
        } catch (Exception hi)
        {
            System.out.println("Aqui " + hi.getMessage());
            sesion.getTransaction().rollback();
            lista = null;
        }
        return lista;
    }

    public Ubicacion getUnidad(String codigo)
    {
        UbicacionDB db = new UbicacionDB();
        List<Ubicacion> lista = db.read("from Unidad uni join fetch uni.compraEspecie com join fetch com.especie esp "
                + "join fetch com.compra comp join fetch uni.cava cav join fetch cav.almacen"
                + " where esp.codigo ='" + codigo + "' and "
                + "uni.estado=true order by comp.fecha");
        Ubicacion unidad = lista.get(0);
        unidad.setEstado(Boolean.FALSE);
        db.update(unidad);
        return unidad;
    }

    public Unidad getTerminado(String codigo)
    {
        System.out.println("Este es el codigo Terminado " + codigo);
        UnidadDB db = new UnidadDB();
        List<Unidad> lista = db.read("from Terminado ter join fetch ter.produccion pro join fetch ter.producto produc "
                + "where produc.codigo ='" + codigo + "' and ter.estado = true order by ter.vencimiento");
        return lista.get(0);
    }
}
