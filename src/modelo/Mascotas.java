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
public class Mascotas {
    private int idMascota;
    private String nombre;
    private String genero;
    private String tipo;
    private String raza;
    private boolean activo;
    private Dueños dueño;

    public Mascotas() {
    }

    public Mascotas(int idMascota, String nombre, String genero, String tipo, String raza, boolean activo, Dueños dueño) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.raza = raza;
        this.activo = activo;
        this.dueño = dueño;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Dueños getDueño() {
        return dueño;
    }

    public void setDueño(Dueños dueño) {
        this.dueño = dueño;
    }
    
}
