/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pezfa.inventario.database;
import com.pezfa.inventario.hibernate.HibernateUtil;
import java.util.List;
import com.pezfa.inventario.models.Especie;
import org.hibernate.Session;

/**
 *
 * @author Efrain Serrano
 */

public class EspecieDB 
{
    public static boolean create(Especie obj)
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
    public static List<Especie> read()
    {
       Session sesion = null;
       List<Especie> lista = null;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); 
           sesion.beginTransaction();
           lista=sesion.createQuery("from Especie").list();
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
    public static boolean update(Especie obj)
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
    public static boolean delete(Especie obj)
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
       }catch(Exception haga)
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
