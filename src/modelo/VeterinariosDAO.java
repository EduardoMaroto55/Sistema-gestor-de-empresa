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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class VeterinariosDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Veterinarios u = new Veterinarios();
    public int agregarVeterinario(Veterinarios vet) {
        int r = 0;
        String sql = "INSERT INTO Veterinarios (Nombre,Ape1,Ape2,Cedula,CodProfesional,Email,Telefono,Direccion,Activo) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getApe1());
            ps.setString(3, vet.getApe2());
            ps.setInt(4, vet.getCedula());
            ps.setInt(5, vet.getCodProfesional());
            ps.setString(6, vet.getEmail());         
            ps.setInt(7, vet.getTelefono());
            ps.setString(8, vet.getDireccion());
            ps.setBoolean(9,vet.isActivo());
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
     public int actualizarVeterinario(Veterinarios vet) {
        int r = 0;
        String sql = "UPDATE Veterinarios set  Nombre=?,Ape1=?,Ape2=?,Cedula=?,CodProfesional=?,Email=?,Telefono=?,Direccion=?,Activo=? WHERE IdVeterinario=?";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, vet.getNombre());
            ps.setString(2, vet.getApe1());
            ps.setString(3, vet.getApe2());
            ps.setInt(4, vet.getCedula());
            ps.setInt(5, vet.getCodProfesional());
            ps.setString(6, vet.getEmail());         
            ps.setInt(7, vet.getTelefono());
            ps.setString(8, vet.getDireccion());
            ps.setBoolean(9,vet.isActivo());
            ps.setInt(10, vet.getIdVeterinario());
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
     public int eliminarVeterinario(int id) {
        int r = 0;
        String sql = "DELETE FROM Veterinarios WHERE IdVeterinario=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IdVeterinario", "Nombre", "Ape1","Ape2","Cedula","CodProfesional","Email","Telefono","Direccion", "Activo"};
        String[] registros = new String[10];
        String sql = "SELECT *FROM Veterinarios WHERE Nombre LIKE  '*" + filtro + "*'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Como se llama en la BD
                registros[0] = rs.getString("IdVeterinario");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Ape1");
                registros[3] = rs.getString("Ape2");
                registros[4] = rs.getString("Cedula");
                registros[5] = rs.getString("CodProfesional");
                registros[6] = rs.getString("Email");     
                registros[7] = rs.getString("Telefono");
                registros[8] = rs.getString("Direccion");
                registros[9] = rs.getString("Activo");
                
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS:" + e.getMessage());
        }
    }
     public void cargarComboVeterinarios(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("SELECCIONAR VETERINARIO");
        String sql = "SELECT Nombre FROM Veterinarios WHERE Activo=TRUE";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
        }
    }

    public void cargarIdVeterinarios(JComboBox combo, JTextField texto) {

        String sql = "SELECT IdVeterinario FROM Veterinarios WHERE Nombre = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("IdVeterinario")));
        } catch (SQLException e) {
        }
    }

}
