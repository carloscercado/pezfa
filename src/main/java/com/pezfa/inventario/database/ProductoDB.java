package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Producto;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductoDB implements Crud<Producto>
{
    public boolean validarCodigo(String codigo) {
        Producto producto = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Producto produc where produc.codigo= :codigo");
            consulta.setParameter("codigo", codigo);
            producto = (Producto) consulta.list().get(0);
            if (producto != null) {
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