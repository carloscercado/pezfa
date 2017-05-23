package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Empleado;
import org.hibernate.Query;
import org.hibernate.Session;

public class EmpleadoDB implements Crud<Empleado>
{
    public boolean validarCedula(String cedula) {
        Empleado empleado = null;
        Session sesion = null;
        boolean state = false;
        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Empleado emple where emple.cedula= :cedula");
            consulta.setParameter("cedula", cedula);
            empleado = (Empleado) consulta.list().get(0);
            if (empleado != null) {
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
