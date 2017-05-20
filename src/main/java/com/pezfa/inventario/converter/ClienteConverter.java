package com.pezfa.inventario.converter;

import com.pezfa.inventario.database.ClienteDB;
import com.pezfa.inventario.models.Cliente;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.Location;

@FacesConverter(forClass = Location.class, value = "cliente")
public class ClienteConverter implements Converter
{

    public static List<Cliente> clientes;

    public ClienteConverter()
    {
        ClienteDB db = new ClienteDB();
        clientes = db.read("from Cliente");
    }

    public Object getAsObject(FacesContext fc, UIComponent uic, String value)
    {
        Integer code = Integer.parseInt(value);
        Cliente obj = buscar(code);
        return obj;

    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o)
    {
        if (o == null || o.equals(""))
        {
            return "";
        } else
        {
            return String.valueOf(((Cliente) o).getId());
        }
    }

    public Cliente buscar(int id)
    {
        for (Cliente obj : clientes)
        {
            if (obj.getId() == id)
            {
                return obj;
            }
        }
        return null;
    }

}
