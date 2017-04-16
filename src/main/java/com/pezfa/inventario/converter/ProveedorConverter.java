/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.ProveedorDB;
import com.pezfa.inventario.models.Proveedor;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.Location;

/**
 *
 * @author Alicia
 */
@FacesConverter(forClass = Location.class, value = "proveedor")
public class ProveedorConverter implements Converter
{

    public static List<Proveedor> proveedors;

    public ProveedorConverter()
    {
        ProveedorDB db = new ProveedorDB();
        proveedors = db.read("from Proveedor");
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value)
    {
        Integer code = Integer.parseInt(value);
        Proveedor obj = buscar(code);
        return obj;

    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o)
    {
        if (o == null || o.equals(""))
        {
            return "";
        } else
        {
            return String.valueOf(((Proveedor) o).getId());
        }
    }

    public Proveedor buscar(int id)
    {
        for (Proveedor obj : proveedors)
        {
            if (obj.getId() == id)
            {
                return obj;
            }
        }
        return null;
    }

}
