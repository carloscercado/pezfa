package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Romario Guerrero
 */
public class UsuarioDB implements Crud<Usuario>
{

    public static Usuario validateUser(String userName, String clave)
    {
        Usuario usuario = null;
        Session sesion = null;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Usuario usu join fetch usu.empleado  where usuario= :usuario and clave= :clave ");
            consulta.setParameter("usuario", userName);
            consulta.setParameter("clave", clave);
            usuario = (Usuario) consulta.list().get(0);

            sesion.getTransaction().commit();

        } catch (Exception e)
        {
            System.out.println("ERROR: " + e.getMessage());
            sesion.getTransaction().rollback();

        } finally
        {
            return usuario;
        }
    }

}
