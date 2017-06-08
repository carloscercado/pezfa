package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.EmpleadoDB;
import com.pezfa.inventario.models.Empleado;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.Location;

@FacesConverter(forClass = Location.class, value = "empleadores")
public class EmpleadoConverter2 implements Converter {

    public static List<Empleado> empleados;

    public EmpleadoConverter2() {
        EmpleadoDB db = new EmpleadoDB();
        empleados = db.read("From Empleado emp");
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        Integer code =  Integer.parseInt(value);
        Empleado obj = buscar(code);
        return obj;
        
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o==null||o.equals(""))
        {
            return "";
        }else
        {
            return String.valueOf(((Empleado)o).getId());
        }
    }
    
    public Empleado buscar(int id)
    {
        for (Empleado obj : empleados)
        {
            if(obj.getId()==id)
            {
                return obj;
            }
        }
        return null;
    }

}
