
package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Cliente;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 *
 * @author Romario Guerrero
 */
public class ClienteDB implements Crud<Cliente>
{
     public boolean validarRif(String rif) {
        Cliente cliente = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Cliente clien where clien.rif= :rif");
            consulta.setParameter("rif", rif);
            cliente = (Cliente) consulta.list().get(0);
            if (cliente != null) {
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