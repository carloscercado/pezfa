/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.database;

/**
 *
 * @author yulitza
 */
import com.pezfa.inventario.hibernate.HibernateUtil;
import java.util.List;
import com.pezfa.inventario.models.CompraEspecie;
import org.hibernate.Session;

public class CompraEspecieDB //registrar las funciones osea los crud de la base de datos
{

    public static boolean createList(List<CompraEspecie> objs)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            sesion.save(objs.get(0).getCompra());
            for (CompraEspecie obj : objs)
            {
                sesion.save(obj);//guardar el objeto cliente
            }
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception hi)
        {
            System.out.println(hi.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;//state variable q va a tener un valor verdadero o falso
        }
    }

    public static boolean create(CompraEspecie obj)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            sesion.save(obj);//guardar el objeto cliente
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception hi)
        {
            System.out.println(hi.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return state;//state variable q va a tener un valor verdadero o falso
        }
    }

    public static List<CompraEspecie> read()
    {
        Session sesion = null;//
        List<CompraEspecie> lista = null;
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
            sesion.beginTransaction();//abrir la transaccion
            lista = sesion.createQuery("from CompraEspecie com join fetch com.especie").list();//crea la consulta de la base de datos mediante el llamado de la clase y la muestra en forma de lista
            sesion.getTransaction().commit();//terminado de la transaccion
        } catch (Exception hi)
        {
            sesion.getTransaction().rollback();
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
            return lista;
        }
    }

    public static boolean update(CompraEspecie obj)
    {
        Session sesion = null;
        boolean state = false;
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion
            sesion.update(obj);//modifica el objeto cliente
            sesion.getTransaction().commit();//terminado la transaccion
            state = true;
        } catch (Exception hi)
        {
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
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

    public static boolean delete(CompraEspecie obj)
    {
        Session sesion = null;
        boolean state = false;
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion
            sesion.delete(obj);//elimina el objeto cliente
            sesion.getTransaction().commit();//terminado la transaccion
            state = true;
        } catch (Exception hi)
        {
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
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
