
import com.pezfa.inventario.models.Cliente;
import javax.swing.JOptionPane;
import com.pezfa.inventario.database.ClienteDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adela Hernandez
 */
public class Main
{
    public static void main (String arg[])
    {
        Cliente obj= new Cliente(0, "123456789", "Andres", "Santa Elena", "04248708820", null, null);
        ClienteDB.delete(obj);
        if (ClienteDB.delete(obj)){
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente");
        }
        else{
            JOptionPane.showMessageDialog(null, "Eliminación fallida");
        }
    }
}

/* alterar el archivo de configuración en com.pezfa.inventario.models
y colocar el incremento en el*/