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
public class Veterinarios {
    private int idVeterinario;
    private String nombre;
    private String ape1;
    private String ape2;
    private int cedula;
    private int codProfesional;  
    private String email;
    private int telefono;
    private String direccion;
    private Boolean activo;

    public Veterinarios() {
    }

    public Veterinarios(int idVeterinario, String nombre, String ape1, String ape2, int cedula, int codProfesional, String email, int telefono, String direccion, Boolean activo) {
        this.idVeterinario = idVeterinario;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.cedula = cedula;
        this.codProfesional = codProfesional;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
    }
    

    public int getIdVeterinario() {
        return idVeterinario;
    }

    public void setIdVeterinario(int idVeterinario) {
        this.idVeterinario = idVeterinario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getCodProfesional() {
        return codProfesional;
    }

    public void setCodProfesional(int codProfesional) {
        this.codProfesional = codProfesional;
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

    public Boolean isActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    

}
