/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.AlmacenDB;
import com.pezfa.inventario.models.Almacen;
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
@FacesConverter(forClass = Location.class, value = "almacen")
public class AlmacenConverter implements Converter {

    public static List<Almacen> almacenes;

    public AlmacenConverter() {
        almacenes = AlmacenDB.read();
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Integer code =  Integer.parseInt(value);
        Almacen obj = buscar(code);
        return obj;
        
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null||o.equals(""))
        {
            return "";
        }else
        {
            return String.valueOf(((Almacen)o).getId());
        }
    }
    
    public Almacen buscar(int id)
    {
        for (Almacen obj : almacenes)
        {
            if(obj.getId()==id)
            {
                return obj;
            }
        }
        return null;
    }

}
