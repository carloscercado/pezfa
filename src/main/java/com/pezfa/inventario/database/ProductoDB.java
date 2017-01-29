package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Producto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
public class ProductoDB
{
    public static List<Producto> getLista()
    {
        Session sesion = null;
        List<Producto> lista = null;
        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            lista = sesion.createQuery("from Producto").getResultList();
            sesion.beginTransaction().commit();
        }catch(Exception e)
        {
            sesion.getTransaction().rollback();
        }finally
        {
            if(sesion != null)
            {
                sesion.close();
            }
        }
        return lista;
    }
}
