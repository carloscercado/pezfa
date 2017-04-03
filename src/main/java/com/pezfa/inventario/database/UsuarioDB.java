/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Usuario;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Romario Guerrero
 */
public class UsuarioDB
{

    public static boolean create(Usuario obj)
    {
        Session sesion = null;
        boolean state = false;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.save(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e)
        {
            sesion.getTransaction().rollback();
            state = false;
            System.out.println(e.getMessage());

        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;
        }
    }

    public static List<Usuario> read()
    {
        Session sesion = null;
        List<Usuario> lista = null;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            lista = sesion.createQuery("from Usuario user join fetch user.empleado").list();
            sesion.getTransaction().commit();

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();

        } finally
        {
            if (sesion != null){
                sesion.close();
            }
            return lista;
        }
    }

    public static Usuario validateUser(String userName, String clave)
    {
        Usuario usuario = null ;
        Session sesion = null;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            Query consulta = sesion.createQuery("from Usuario usu join fetch usu.empleado  where usuario= :usuario and clave= :clave ");
            consulta.setParameter("usuario", userName);
            consulta.setParameter("clave", clave);
            usuario =(Usuario) consulta.list().get(0);
            
            sesion.getTransaction().commit();

        } catch (Exception e)
        {
            System.out.println("ERROR: "+e.getMessage());
            sesion.getTransaction().rollback();

        } finally
        {
            return usuario;
        }
    }

    public static boolean update(Usuario obj)
    {
        Session sesion = null;
        boolean state = false;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.update(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e)
        {
            sesion.getTransaction().rollback();
            state = false;

        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;
        }
    }

    public static boolean delete(Usuario obj)
    {
        Session sesion = null;
        boolean state = false;

        try
        {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.delete(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e)
        {
            sesion.getTransaction().rollback();
            state = false;

        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;
        }
    }
}
