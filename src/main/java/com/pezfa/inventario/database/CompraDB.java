package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Compra;
import org.hibernate.Query;
import org.hibernate.Session;

public class CompraDB implements Crud<Compra>
{
    public boolean validarOrden(String orden) {
        Compra compra = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Compra comp where comp.orden= :orden");
            consulta.setParameter("orden", orden);
            compra = (Compra) consulta.list().get(0);
            if (compra != null) {
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
