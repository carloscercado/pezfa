package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Compra;
import com.pezfa.inventario.models.CompraEspecie;
import com.pezfa.inventario.models.Venta;
import com.pezfa.inventario.models.VentaDetalle;
import com.pezfa.inventario.models.VentaTerminado;
import com.pezfa.inventario.models.VentaUnidad;
import java.math.BigDecimal;
import java.util.Set;
import org.hibernate.Session;

/**
 *
 * @author Romario Guerrero
 */
public class VentaDB implements Crud<Venta>
{

    public static boolean createList(Set<VentaDetalle> objs)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            Venta venta = null;
            for (VentaDetalle obj : objs)
            {
                venta = obj.getVenta();
            }
            venta.setIngreso(BigDecimal.ZERO);
            sesion.save(venta);
            for (VentaDetalle obj : objs)
            {
                if (obj instanceof VentaTerminado)
                {
                    System.err.println("Es instancia de Terminado");
                    sesion.save((VentaTerminado) obj);
                } else if (obj instanceof VentaUnidad)
                {
                    System.err.println("Es instancia de Terminado");
                    sesion.save((VentaUnidad) obj);
                } else
                {
                    System.err.println("INSTANCIA DE NADIE");
                    throw new Exception("No es instancia de nadie");

                }
            }
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception hi)
        {
            System.out.println(hi.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;//state variable q va a tener un valor verdadero o falso
        }
    }
}
