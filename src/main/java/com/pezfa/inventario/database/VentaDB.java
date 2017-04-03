/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.database;

import com.pezfa.inventario.hibernate.HibernateUtil;
import com.pezfa.inventario.models.Venta;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Romario Guerrero
 */
public class VentaDB {

    public static boolean create(Venta obj) {
        Session sesion = null;
        boolean state = false;

        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.save(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            state = false;
            System.out.println(e.getMessage());

        } finally {
            if (sesion != null) {
                sesion.close();
            }
            return state;
        }
    }

    public static List<Venta> read() {
        Session sesion = null;
        List<Venta> lista = null;

        try {
            sesion = HibernateUtil.getSesion().openSession();
            lista = sesion.createQuery("from Venta").list();
        } catch (Exception e) {
            sesion.getTransaction().rollback();

        } finally {
            return lista;
        }
    }

    public static boolean update(Venta obj) {
        Session sesion = null;
        boolean state = false;

        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.update(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            state = false;

        } finally {
            if (sesion != null) {
                sesion.close();
            }
            return state;
        }
    }

    public static boolean delete(Venta obj) {
        Session sesion = null;
        boolean state = false;

        try {
            sesion = HibernateUtil.getSesion().openSession();
            sesion.beginTransaction();
            sesion.delete(obj);
            sesion.getTransaction().commit();
            state = true;

        } catch (Exception e) {
            sesion.getTransaction().rollback();
            state = false;

        } finally {
            if (sesion != null) {
                sesion.close();
            }
            return state;
        }
    }
}
