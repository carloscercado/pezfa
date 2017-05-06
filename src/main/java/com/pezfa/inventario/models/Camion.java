package com.pezfa.inventario.models;
/**
 *
 * @author Romario Guerrero
 * @email romarioguerreroh@gmail.com
 */
public class Camion implements java.io.Serializable {

    private int id;
    private String marca;
    private String modelo;
    private String placa;

    public Camion()
    {
    }

    
    
    public Camion(int id, String marca, String modelo, String placa)
    {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }
    
    public void toUppercase ()
    {
        this.marca = this.marca.toUpperCase();
        this.modelo = this.modelo.toUpperCase();
        this.placa = this.placa.toUpperCase();
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Camion other = (Camion) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
    
    
    
}

