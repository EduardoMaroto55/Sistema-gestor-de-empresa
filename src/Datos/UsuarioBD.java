/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

import Negocio.Usuario;
import java.sql.ResultSet;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsuarioBD {

    Usuario Usuario = new Usuario();

    private Usuario user;

    public UsuarioBD(Usuario usuario) {
        this.user = usuario;
    }

    public UsuarioBD() {
    }

    public Usuario getUsuario() {
        return user;
    }

    public void setUsuario(Usuario usuario) {
        this.user = usuario;
    }
    //Metodo que crea un usuario en la base de datos
    public void crearUsuario() {
        String sql = "INSERT INTO Usuarios (Usuario, Correo, NombreUsuario, Contrasena, Activo) VALUES ('" + user.getUsuario() + "', '" + user.getCorreo() + "', '" + user.getNombre() + "', '" + user.getContrasena() + "', " + user.isActivo() + ")";
        Conexion cnn = new Conexion(sql, false);
        cnn.EjecutarSQL();
    }
   public ResultSet Listado(){
       // CONSULTA SQL 
       String sql = "SELECT IdUsuario, Usuario, NombreUsuario, Activo FROM Usuarios ORDER BY NombreUsuario";
       //CREA LA CONEXION
       Conexion con = new Conexion(sql,true);
       //EJECUTA LA SENTENCIA 
       con.EjecutarSQL();
       //RETORNA RESULTSET CON LOS DATOS
       return con.getResultado();
       
   
   }
   
   public ResultSet Consulta(){
       String sql = "SELECT IdUsuario, Usuario, Correo, NombreUsuario, Contrasena, Activo FROM Usuarios WHERE IdUsuario = " + this.Usuario.getIdUsuario() + ";";
       Conexion cnn = new Conexion(sql,false);
       cnn.EjecutarSQL();
       return cnn.getResultado();
   }
   
  public void Editar(){
  Usuario u = this.Usuario;
  String sql = "UPDATE Usuarios SET Usuario='" + u.getUsuario() + "', Correo='" + u.getCorreo() + "', NombreUsuario='" + u.getNombre() + "', Contrasena='" + u.getContrasena() + "', Activo=" + u.isActivo() + " WHERE IdUsuario = " + u.getIdUsuario() + ";";
  Conexion cnn = new Conexion(sql,false);
  cnn.EjecutarSQL();
  }

}
