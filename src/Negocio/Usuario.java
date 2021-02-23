/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.UsuarioBD;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Usuario {

    private int idUsuario;
    private String usuario;
    private String nombre;
    private String contrasena;
    private String correo;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int idUsuario, String usuario, String nombre, String contrasena, String correo, boolean activo) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.activo = activo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void Crear() {
        UsuarioBD user = new UsuarioBD();
        user.setUsuario(this);
        user.crearUsuario();

    }

    public ArrayList<Usuario> Listado() {
        ArrayList<Usuario> lista = new ArrayList<>();
        UsuarioBD user = new UsuarioBD();
        ResultSet rs = user.Listado();
        try {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("IdUsuario"));
                u.setNombre(rs.getString("NombreUsuario"));
                u.setUsuario(rs.getString("Usuario"));
                u.setActivo(rs.getBoolean("Activo"));
                lista.add(u);
            }

        } catch (SQLException e) {
            String error = e.getMessage();

        }
        return lista;
    }

    public void Editar() {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.setUsuario(this);
        usuarioBD.Editar();

    }

    public void Consulta() {
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.setUsuario(this);

        try {
            ResultSet rs = usuarioBD.Consulta();
            
            if (rs.first()) {
                this.usuario = rs.getString("Usuario");
                this.correo = rs.getString("Correo");
                this.nombre = rs.getString("NombreUsuario");
                this.contrasena = rs.getString("Contraseña");
                this.activo = rs.getBoolean("Activo");

            }
        } catch (SQLException e) {
            String error = e.getMessage();
        }

    }

}
