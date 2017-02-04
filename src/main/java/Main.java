/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adela Hernandez
 */
import java.util.*;
import com.pezfa.inventario.database.AlmacenDB;
import com.pezfa.inventario.models.Almacen;
import javax.swing.JOptionPane;
public class Main
{
    public static void main (String arg[])
    {
        Almacen obj = new Almacen (0, "Santa Rosa", "Santa Rosa Cumaná", "0293-41629053", null);
        if(AlmacenDB.create(obj))
        {
            JOptionPane.showMessageDialog(null, "Registrado Correctamente");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Problemas Al Registrar");
        }
        
    }
}
