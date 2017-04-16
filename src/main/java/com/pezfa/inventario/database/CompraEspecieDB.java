package com.pezfa.inventario.database;

/**
 *
 * @author yulitza
 */
import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Compra;
import java.util.List;
import com.pezfa.inventario.models.CompraEspecie;
import java.math.BigDecimal;
import java.util.Set;
import org.hibernate.Session;

public class CompraEspecieDB implements Crud<CompraEspecie>
{

    public static boolean createList(Set<CompraEspecie> objs)
    {
        Session sesion = null;//inicializa la session
        boolean state = false;//inicializa la variable state
        try
        {
            sesion = HibernateUtil.getSesion().openSession(); //Hibernanteutil clase pertenenciente a la base de datos... abrir y obtener conexion en la base de datos
            sesion.beginTransaction(); //iniciar la transaccion... transaccion: son las operaciones crud
            Compra compra = null;
            for(CompraEspecie obj : objs)
                compra = obj.getCompra(); 
            compra.setEstado("Comprado");
            compra.setGasto(BigDecimal.ZERO);
            sesion.save(compra);
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

    public static List<CompraEspecie> findBy(int id)
    {
       Session sesion = null;//
       List<CompraEspecie> lista = null;
       try
       {
           sesion=HibernateUtil.getSesion().openSession(); //abrir conexion en la base de datos
           sesion.beginTransaction();//abrir la transaccion
           lista= sesion.createQuery("from CompraEspecie esp join fetch esp.compra com join fetch esp.especie where com.id="+id+" and esp.cantidad != esp.ubicados").list();//crea la consulta de la base de datos mediante el llamado de la clase y la muestra en forma de lista
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
       }
       return lista;
    }
    
}
