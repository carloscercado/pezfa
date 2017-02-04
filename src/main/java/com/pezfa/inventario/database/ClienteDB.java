/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.database;

import com.pezfa.inventario.models.Cliente;
import java.util.List;
import org.hibernate.Session;
import com.pezfa.inventario.hibernate.HibernateUtil;

/**
 *
 * @author Adela Hernandez
 */
public class ClienteDB
{
    public static boolean create(Cliente obj)
    {
        Session sesion=null;
        boolean state=false;
       
        
        try{
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.save(obj);
            sesion.getTransaction().commit();
            state=true;
            
        } catch (Exception e){
            sesion.getTransaction().rollback();
            state=false;
            System.out.println(e.getMessage());  
           
        } finally{
            if (sesion != null){
                sesion.close();
            }
            return state;
        }        
    }

    public static List<Cliente> read()    
    {
        Session sesion=null;
        List<Cliente> lista=null;
        
        try{
            sesion=HibernateUtil.getSesion().openSession();
            sesion.beginTransaction().commit();
            lista=sesion.createQuery("from Cliente").list();
            
        } catch(Exception e){
           sesion.getTransaction().rollback();
           
        } finally{
            return lista;
        }
    }
    
    public static boolean update(Cliente obj)
    {
        Session sesion=null;
        boolean state=false;
        
        try{ 
            sesion=HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.update(obj);
            sesion.getTransaction().commit();
            state=true;
            
        } catch (Exception e){
            sesion.getTransaction().rollback();
            state=false;
            
        } finally{
            if(sesion!=null){
                sesion.close();
            }            
            return state;
        }
    }
    
    public static boolean delete(Cliente obj)
    {
        Session sesion=null;
        boolean state=false;
       
        try{
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.delete(obj);
            sesion.getTransaction().commit();
            state=true;
            
        } catch (Exception e){
            sesion.getTransaction().rollback();
            state=false;
            
        } finally{
            if (sesion != null){
                sesion.close();
            }
            return state;
        }        
    }
}
    

