package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Especie;
import org.hibernate.Query;
import org.hibernate.Session;

public class EspecieDB implements Crud<Especie>
{
    public boolean validarCodigo(String codigo) {
        Especie especie = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Especie espec where espec.codigo= :codigo");
            consulta.setParameter("codigo", codigo);
            especie = (Especie) consulta.list().get(0);
            if (especie != null) {
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
