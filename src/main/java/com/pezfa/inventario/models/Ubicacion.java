package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Ubicacion extends ProductoSalida  implements java.io.Serializable {


     private int id;
     private Cava cava;
     private CompraEspecie compraEspecie;
     private double peso;
     private String codigo;
     private Boolean estado;
     private Set detalleProduccions = new HashSet(0);
     private Set ventaEspecies = new HashSet(0);

    public Ubicacion() {
    }

	
    public Ubicacion(Cava cava, CompraEspecie compraEspecie, double peso, String codigo) {
        this.cava = cava;
        this.compraEspecie = compraEspecie;
        this.peso = peso;
        this.codigo = codigo;
    }
    public Ubicacion(Cava cava, CompraEspecie compraEspecie, double peso, String codigo, Boolean estado, Set detalleProduccions, Set ventaEspecies) {
       this.cava = cava;
       this.compraEspecie = compraEspecie;
       this.peso = peso;
       this.codigo = codigo;
       this.estado = estado;
       this.detalleProduccions = detalleProduccions;
       this.ventaEspecies = ventaEspecies;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Cava getCava() {
        return this.cava;
    }
    
    public void setCava(Cava cava) {
        this.cava = cava;
    }
    public CompraEspecie getCompraEspecie() {
        return this.compraEspecie;
    }
    
    public void setCompraEspecie(CompraEspecie compraEspecie) {
        this.compraEspecie = compraEspecie;
    }
    public double getPeso() {
        return this.peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    public Set getDetalleProduccions() {
        return this.detalleProduccions;
    }
    
    public void setDetalleProduccions(Set detalleProduccions) {
        this.detalleProduccions = detalleProduccions;
    }
    public Set getVentaEspecies() {
        return this.ventaEspecies;
    }
    
    public void setVentaEspecies(Set ventaEspecies) {
        this.ventaEspecies = ventaEspecies;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.compraEspecie.getEspecie());
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Ubicacion other = (Ubicacion) obj;
        if (!Objects.equals(this.compraEspecie.getEspecie(), other.compraEspecie.getEspecie()))
        {
            return false;
        }
        return true;
    }
    
    
    

}


