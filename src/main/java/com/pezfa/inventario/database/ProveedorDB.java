package com.pezfa.inventario.database;

/**
 *
 * @author yulitza
 */
import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Proveedor;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProveedorDB implements Crud<Proveedor> {

    public boolean validarRif(String rif) {
        Proveedor proveedor = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Proveedor provee where provee.rif= :rif");
            consulta.setParameter("rif", rif);
            proveedor = (Proveedor) consulta.list().get(0);
            if (proveedor != null) {
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
