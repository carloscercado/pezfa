package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Carlos Cercado
 * @email cercadocarlos@gmail.com
 */
public interface Crud<T>
{

    default boolean create(T obj)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            sesion.save(obj);//guardar el objeto
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
        }
        return state;//state variable q va a tener un valor verdadero o falso
    }

    default boolean delete(T obj)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            sesion.delete(obj);//elimina el objeto 
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
        }
        return state;//state variable q va a tener un valor verdadero o falso
    }

    default boolean update(T obj)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            sesion.update(obj);//actualiza el objeto 
            sesion.getTransaction().commit();//finalizar la transaccion
            state = true;
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            sesion.getTransaction().rollback();//si ocurre un problema al guardar quitar todo y dejar la base de datos como estaba
            state = false;
        } finally
        {
            if (sesion != null)
            {
                sesion.close();
            }
        }
        return state;//state variable q va a tener un valor verdadero o falso
    }

    default List<T> read(String hql)
    {
        Session sesion = null;//
        List<T> lista = null;
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
            sesion.beginTransaction();//abrir la transaccion
            lista = sesion.createQuery(hql).list();//crea la consulta de la base de datos mediante el llamado de la clase y la muestra en forma de lista
            sesion.getTransaction().commit();//terminado de la transaccion
        } catch (Exception ex)
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
}
