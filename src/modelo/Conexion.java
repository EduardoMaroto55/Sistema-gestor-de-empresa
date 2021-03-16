/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
 import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu5m
 */
public class Conexion {

    Connection ccn = null;
    Statement st = null;

    public Conexion() {
        try {
            String rutaFile;
            rutaFile = "./DB/SisMiMascotaDB.accdb";
            String url = "jdbc:ucanaccess://" + rutaFile;
            ccn = DriverManager.getConnection(url);
            st = ccn.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE CONEXIÓN " + e);
        }

    }

    // Metodo que devuelva la conexion actual
    public Connection getConnection() {
        return ccn;
    }

    public void desconexion() {
        try {
            ccn.close();
            System.exit(0);
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
