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
public class DueñosDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Dueños u = new Dueños();
    public int agregarDueño(Dueños due) {
        int r = 0;
        String sql = "INSERT INTO Dueños (NombreDueño,Ape1,Ape2,Cedula,Genero,Email,Telefono,Direccion) VALUES(?,?,?,?,?,?,?,?)";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, due.getNombre());
            ps.setString(2, due.getApe1());
            ps.setString(3, due.getApe2());
            ps.setInt(4, due.getCedula());
            ps.setString(5, due.getGenero());
            ps.setString(6, due.getEmail());         
            ps.setInt(7, due.getTelefono());
            ps.setString(8, due.getDireccion());
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
     public int actualizarDueño(Dueños due) {
        int r = 0;
        String sql = "UPDATE Dueños set  NombreDueño=?,Ape1=?,Ape2=?,Cedula=?,Genero=?,Email=?,Telefono=?,Direccion=? WHERE IdDueño=?";
        try {
           con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, due.getNombre());
            ps.setString(2, due.getApe1());
            ps.setString(3, due.getApe2());
            ps.setInt(4, due.getCedula());
            ps.setString(5, due.getGenero());
            ps.setString(6, due.getEmail());         
            ps.setInt(7, due.getTelefono());
            ps.setString(8, due.getDireccion());
            ps.setInt(9, due.getIdDueño());
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
     public int eliminarDueño(int id) {
        int r = 0;
        String sql = "DELETE FROM Dueños WHERE IdDueño=" + id;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();

        } catch (SQLException e) {
        }
        return r;
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        String[] titulos = {"IdDueño", "NombreDueño", "Ape1","Ape2","Cedula","Genero","Email","Telefono","Direccion"};
        String[] registros = new String[9];
        String sql = "SELECT * FROM Dueños WHERE NombreDueño LIKE  '*" + filtro + "*'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Como se llama en la BD
                registros[0] = rs.getString("IdDueño");
                registros[1] = rs.getString("NombreDueño");
                registros[2] = rs.getString("Ape1");
                registros[3] = rs.getString("Ape2");
                registros[4] = rs.getString("Cedula");
                registros[5] = rs.getString("Genero");
                registros[6] = rs.getString("Email");     
                registros[7] = rs.getString("Telefono");
                registros[8] = rs.getString("Direccion");
               
                
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS:" + e.getMessage());
        }
    }
     public void cargarComboDueños(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("SELECCIONAR DUEÑO");
        String sql = "SELECT NombreDueño FROM Dueños";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("NombreDueño"));
            }
        } catch (SQLException e) {
        }
    }

    public void cargarIdDueños(JComboBox combo, JTextField texto) {

        String sql = "SELECT IdDueño FROM Dueños WHERE NombreDueño = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("IdDueño")));
        } catch (SQLException e) {
        }
    }
    public void cargarCedulaDueños(JComboBox combo, JTextField texto) {

        String sql = "SELECT Cedula FROM Dueños WHERE NombreDueño = '" + combo.getSelectedItem() + "'";
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            texto.setText(String.valueOf(rs.getInt("Cedula")));
        } catch (SQLException e) {
        }
    }



}
