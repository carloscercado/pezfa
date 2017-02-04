/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.database;
import com.pezfa.inventario.hibernate.HibernateUtil;
import java.util.List;
import com.pezfa.inventario.models.Produccion;
import org.hibernate.Session;

/**
 *
 * @author Efrain Serrano
 */

public class ProduccionDB 
{
    public static boolean create(Produccion obj)
    {
       Session sesion = null;
       boolean state = false;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); 
           sesion.beginTransaction(); 
           sesion.save(obj);
           sesion.getTransaction().commit();
           state = true;
       }catch(Exception hi)
       {
           System.out.println(hi.getMessage());
           sesion.getTransaction().rollback();
           state = false;
       }
       finally
       {
           if (sesion != null)
           {
               sesion.close();
           }
           return state;
       }
    }
    public static List<Produccion> read()
    {
       Session sesion = null;
       List<Produccion> lista = null;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); 
           sesion.beginTransaction();
           lista=sesion.createQuery("from Produccion").list();
           sesion.getTransaction().commit();
       }catch(Exception haga)
       {
           sesion.getTransaction().rollback();
       }
       finally
       {
           if (sesion != null)
           {
               sesion.close();
           }
            return lista;
       }
    }
    public static boolean update(Produccion obj)
    {
       Session sesion = null;
       boolean state = false;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); 
           sesion.beginTransaction(); 
           sesion.update(obj);
           sesion.getTransaction().commit();
           state = true;
       }catch(Exception haga)
       {
           sesion.getTransaction().rollback(); 
       }
       finally
       {
           if (sesion != null)
           {
               sesion.close();
           }
           return state;
       }
    }
    public static boolean delete(Produccion obj)
    {
       Session sesion = null;
       boolean state = false;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); 
           sesion.beginTransaction(); 
           sesion.delete(obj);
           sesion.getTransaction().commit();
           state = true;
       }catch(Exception hi)
       {
           sesion.getTransaction().rollback();
           state = false;
       }
       finally
       {
           if (sesion != null)
           {
               sesion.close();
           }
           return state;
       }
    }
    
}
