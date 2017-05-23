/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.CavaDB;
import com.pezfa.inventario.models.Cava;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.Location;

@FacesConverter(forClass = Location.class, value = "cava")
public class CavaConverter implements Converter {

    public static List<Cava> cavas;

    public CavaConverter() {
        CavaDB db = new CavaDB();
        cavas = db.read("from Cava cavita join fetch cavita.almacen");
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Integer code =  Integer.parseInt(value);
        Cava obj = buscar(code);
        return obj;
        
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null||o.equals(""))
        {
            return "";
        }else
        {
            return String.valueOf(((Cava)o).getId());
        }
    }
    
    public Cava buscar(int id)
    {
        for (Cava obj : cavas)
        {
            if(obj.getId()==id)
            {
                return obj;
            }
        }
        return null;
    }

}
