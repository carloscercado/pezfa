package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.CamionDB;
import com.pezfa.inventario.models.Camion;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.Location;

/**
 *
 * @author Romario Guerrero
 */
@FacesConverter(forClass = Location.class, value = "camion")
public class CamionConverter implements Converter {

    public static List<Camion> camions;

    public CamionConverter() {
        CamionDB db = new CamionDB();
        camions = db.read("From Camion");
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Integer code =  Integer.parseInt(value);
        Camion obj = buscar(code);
        return obj;
        
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null||o.equals(""))
        {
            return "";
        }else
        {
            return String.valueOf(((Camion)o).getId());
        }
    }
    
    public Camion buscar(int id)
    {
        for (Camion obj : camions)
        {
            if(obj.getId()==id)
            {
                return obj;
            }
        }
        return null;
    }

}
