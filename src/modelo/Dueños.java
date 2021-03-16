/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Dueños {
    private int idDueño;
    private String nombreDueño;
    private String ape1;
    private String ape2;
    private int cedula;
    private String genero;  
    private String email;
    private int telefono;
    private String direccion;

    public Dueños() {
    }
    

    public Dueños(int idDueño, String nombreDueño, String ape1, String ape2, int cedula, String genero, String email, int telefono, String direccion) {
        this.idDueño = idDueño;
        this.nombreDueño = nombreDueño;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.cedula = cedula;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(int idDueño) {
        this.idDueño = idDueño;
    }

    public String getNombre() {
        return nombreDueño;
    }

    public void setNombre(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
}
