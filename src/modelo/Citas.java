/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.sql.Date;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Citas {
    private int idCita;
    private String asunto;
    private Date fecha;
    private String hora;
    private Date fechaCreación;
    private Dueños dueño;
    private Veterinarios veterinario;
    private Usuarios usuario;

    public Citas() {
    }

    public Citas(int idCita, String asunto, Date fecha, String hora, Date fechaCreación, Dueños dueño, Veterinarios veterinario, Usuarios usuario) {
        this.idCita = idCita;
        this.asunto = asunto;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaCreación = fechaCreación;
        this.dueño = dueño;
        this.veterinario = veterinario;
        this.usuario = usuario;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Date getFechaCreación() {
        return fechaCreación;
    }

    public void setFechaCreación(Date fechaCreación) {
        this.fechaCreación = fechaCreación;
    }

    public Dueños getDueño() {
        return dueño;
    }

    public void setDueño(Dueños dueño) {
        this.dueño = dueño;
    }

    public Veterinarios getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinarios veterinario) {
        this.veterinario = veterinario;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    

}