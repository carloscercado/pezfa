package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.ProductoSalida;
import com.pezfa.inventario.models.Unidad;
import com.pezfa.inventario.models.Ubicacion;
import com.pezfa.inventario.models.Venta;
import com.pezfa.inventario.models.VentaDetalle;
import com.pezfa.inventario.models.VentaEspecie;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

public class VentaDB implements Crud<Venta> {

    public List<ProductoSalida> create_venta(Set<VentaDetalle> objs, Venta venta) {
        List<ProductoSalida> lista = new ArrayList<>();
        VentaDB dbVenta = new VentaDB();
        venta.setIngreso(BigDecimal.ZERO);
        venta.setDevuelta(false);
        dbVenta.create(venta);

        for (VentaDetalle obj : objs) {
            /* if (obj instanceof VentaUnidad) {
             String codigo = ((VentaUnidad) obj).getUnidad().getProducto().getCodigo();
             Unidad terminado = this.getUnidad(codigo);
             ((VentaUnidad) obj).setUnidad(terminado);
             ((VentaUnidad) obj).setVenta(venta);
             sesion.save((VentaUnidad) obj);
             lista.add(terminado);
             } else if (obj instanceof VentaEspecie) {*/
            String codigo = ((VentaEspecie) obj).getUbicacion().getCompraEspecie().getEspecie().getCodigo();

            Ubicacion unidad = this.getUbicacion(codigo, obj.getCantidad());
            ((VentaEspecie) obj).setUbicacion(unidad);
            ((VentaEspecie) obj).setVenta(venta);
            VentaEspecieDB ventaEsDB = new VentaEspecieDB();
            ventaEsDB.create((VentaEspecie) obj);
            lista.add(unidad);
            /* } else {
             System.err.println("INSTANCIA DE NADIE");
             throw new Exception("No es instancia de nadie");
             }*/
        }

        return lista;
    }

    public Ubicacion getUbicacion(String codigo, double cantidad) {
        UbicacionDB db = new UbicacionDB();
        List<Ubicacion> lista = db.read("from Ubicacion uni join fetch uni.compraEspecie com join fetch com.especie esp "
                + "join fetch com.compra comp join fetch uni.cava cav join fetch cav.almacen"
                + " where esp.codigo ='" + codigo + "' and "
                + "uni.estado=true order by comp.fecha");
        try {
            Ubicacion unidad = lista.get(0);
            if (unidad.getPeso() <= cantidad) {

                double peso = unidad.getPeso();
                unidad.setPeso(0);
                unidad.setEstado(Boolean.FALSE);
                db.update(unidad);
                this.getUbicacion(codigo, (cantidad - peso));
            } else {
                unidad.setPeso(unidad.getPeso() - cantidad);
                db.update(unidad);
            }
            return unidad;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    public Unidad getUnidad(String codigo) {
        UnidadDB db = new UnidadDB();
        List<Unidad> lista = db.read("from Unidad ter join fetch ter.produccion pro join fetch ter.producto produc "
                + "where produc.codigo ='" + codigo + "' and ter.estado = true order by ter.vencimiento");
        return lista.get(0);
    }

    public boolean validarFactura(String factura) {
        Venta venta = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Venta vent where vent.factura= :factura");
            consulta.setParameter("factura", factura);
            venta = (Venta) consulta.list().get(0);
            if (venta != null) {
                state = true;
            }
            sesion.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            sesion.getTransaction().rollback();
            state = false;

        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return state;
    }
}
