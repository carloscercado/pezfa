package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import java.util.List;
import com.pezfa.inventario.models.Cava;
import org.hibernate.Session;
public class CavaDB implements Crud<Cava>
{
    //HQL from Cava cavita join fetch cavita.almacen
    
    public static List<Cava> read(int almacen)
    {
       Session sesion = null;//
       List<Cava> lista = null;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
           sesion.beginTransaction();//abrir la transaccion
           lista=sesion.createQuery("from Cava cavita join fetch cavita.almacen alma where alma.id="+almacen+"").list();//crea la consulta de la base de datos mediante el llamado de la clase y la muestra en forma de lista
           sesion.getTransaction().commit();//terminado de la transaccion
       }catch(Exception hi)
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
}
