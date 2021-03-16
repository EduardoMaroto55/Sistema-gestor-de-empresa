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
public class MascotasDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Mascotas u = new Mascotas();

    public int agregarMascota(Mascotas mas) {
        int r = 0;
        String sql = "INSERT INTO Mascotas (Nombre,Genero,Tipo,Raza,Activo,IdDueño) VALUES(?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, mas.getNombre());
            ps.setString(2, mas.getGenero());
            ps.setString(3, mas.getTipo());
            ps.setString(4, mas.getRaza());
            ps.setBoolean(5, mas.isActivo());
            ps.setInt(6, mas.getDueño().getIdDueño());
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

    public int actualizarMascota(Mascotas mas) {
        int r = 0;
        String sql = "UPDATE Mascotas set  Nombre=?,Genero=?,Tipo=?,Raza=?,Activo=?,IdDueño=? WHERE IdMascota=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, mas.getNombre());
            ps.setString(2, mas.getGenero());
            ps.setString(3, mas.getTipo());
            ps.setString(4, mas.getRaza());
            ps.setBoolean(5, mas.isActivo());
            ps.setInt(6, mas.getDueño().getIdDueño());
            ps.setInt(7, mas.getIdMascota());
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
     public int eliminarMascota(int id) {
        int r = 0;
        String sql = "DELETE FROM Mascotas WHERE IdMascota=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IdMascota", "Nombre", "Genero","Tipo","Raza","Activo","IdDueño"};
        String[] registros = new String[7];
        String sql = "SELECT *FROM Mascotas WHERE Nombre LIKE  '*" + filtro + "*'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Como se llama en la BD
                registros[0] = rs.getString("IdMascota");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Genero");
                registros[3] = rs.getString("Tipo");
                registros[4] = rs.getString("Raza");
                registros[5] = rs.getString("Activo");
                registros[6] = rs.getString("IdDueño");     
                
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS:" + e.getMessage());
        }
    }
   


}
