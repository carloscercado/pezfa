
import com.pezfa.inventario.database.ProductoDB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Carlos Cercado
 */
public class Main
{
    public static void main(String args[])
    {
        System.out.println(ProductoDB.getLista().get(0));
    }
}
