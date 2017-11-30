package com.pezfa.inventario.models;
// Generated 22/04/2017 02:11:01 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Empleado  implements java.io.Serializable {


     private int id;
     private int educacion; 
     private String cedula;
     private String primerNombre;
     private String segundoNombre;
     private String primerApellido;
     private String segundoApellido;
     private Date nacimiento;
     private String sexo;
     private String departamento;
     private String cargo;
     private int satisfecho;
     private Set usuarios = new HashSet(0);

    public Empleado() {
    }

    public Empleado(int id, int educacion, String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date nacimiento, String sexo, String departamento, String cargo, int satisfecho)
    {
        this.id = id;
        this.educacion = educacion;
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.nacimiento = nacimiento;
        this.sexo = sexo;
        this.departamento = departamento;
        this.cargo = cargo;
        this.satisfecho = satisfecho;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getSatisfecho()
    {
        return satisfecho;
    }

    public void setSatisfecho(int satisfecho)
    {
        this.satisfecho = satisfecho;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getPrimerNombre() {
        return this.primerNombre;
    }

    public int getEducacion() {
        return educacion;
    }

    public void setEducacion(int educacion) {
        this.educacion = educacion;
    }
    
    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }
    public String getSegundoNombre() {
        return this.segundoNombre;
    }
    
    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }
    public String getPrimerApellido() {
        return this.primerApellido;
    }
    
    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }
    public String getSegundoApellido() {
        return this.segundoApellido;
    }
    
    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    public Date getNacimiento() {
        return this.nacimiento;
    }
    
    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getCargo() {
        return this.cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    public void toUpperCase()
    {
       this.primerNombre = this.primerNombre.toUpperCase();
       this.primerApellido = this.primerApellido.toUpperCase();
       this.cargo = this.cargo.toUpperCase();
       this.sexo = this.sexo.toUpperCase();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + this.id;
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
        final Empleado other = (Empleado) obj;
        if (this.id != other.id)
        {
            return false;
        }
        return true;
    }
    
    

}


