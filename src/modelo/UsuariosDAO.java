/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class UsuariosDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Usuarios u = new Usuarios();

    public int agregarUsuario(Usuarios user) {
        int r = 0;
        String sql = "INSERT INTO Usuarios (Nombre,Ape1,Ape2,Email,NombreUsuario,Password,TipoUsuario,Activo) VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApe1());
            ps.setString(3, user.getApe2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getTipoUsuario());
            ps.setBoolean(8, user.getActivo());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {

        }
        return r;
    }
//"UPDATE Usuarios SET  Nombre=?,NombreUsuario=?,Password=?,TipoUsuario=? WHERE IdUsuario=?"

    public int actualizarUsuario(Usuarios user) {
        int r = 0;
        String sql = "UPDATE Usuarios set  Nombre=?,Ape1=?,Ape2=?,Email=?,NombreUsuario=?,Password=?,TipoUsuario=?,Activo=? WHERE IdUsuario=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getApe1());
            ps.setString(3, user.getApe2());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getNombreUsuario());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getTipoUsuario());
            ps.setBoolean(8, user.getActivo());
            ps.setInt(9, user.getIdUsuario());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {

        }
        return r;
    }

    public int eliminarUsuario(int id) {
        int r = 0;
        String sql = "DELETE FROM Usuarios WHERE IdUsuario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IdUsuario", "Nombre", "Ape1", "Ape2", "Email", "NombreUsuario", "Password", "TipoUsuario", "Activo"};
        String[] registros = new String[9];
        String sql = "SELECT *FROM Usuarios WHERE Nombre LIKE  '*" + filtro + "*'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Como se llama en la BD
                registros[0] = rs.getString("IdUsuario");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Ape1");
                registros[3] = rs.getString("Ape2");
                registros[4] = rs.getString("Email");
                registros[5] = rs.getString("NombreUsuario");
                registros[6] = rs.getString("Password");
                registros[7] = rs.getString("TipoUsuario");
                registros[8] = rs.getString("Activo");

                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS:" + e.getMessage());
        }
    }

    public boolean login(Usuarios user) {
        String sql = "SELECT IdUsuario,NombreUsuario,Password,TipoUsuario FROM  Usuarios WHERE NombreUsuario=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombreUsuario());
            rs = ps.executeQuery();
            if (rs.next()) {
                if (user.getPassword().equals(rs.getString(3)) && user.getTipoUsuario().equals(rs.getString(4))) {
                    user.setIdUsuario(rs.getInt(1));
                    user.setNombreUsuario(rs.getString(2));
                    user.setTipoUsuario(rs.getString(4));
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    public int cargarIdUsuario(String nombreUsuario) {
        int idUsuario = 0;
        String sql = "SELECT IdUsuario FROM Usuarios WHERE NombreUsuario = '" + nombreUsuario + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            idUsuario = rs.getInt("idUsuario");
        } catch (Exception e) {
        }

        return idUsuario;
    }

}
