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
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class CitasDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Citas u = new Citas();

    public int agregarCita(Citas cita) {
        int r = 0;
        String sql = "INSERT INTO Citas(Asunto,Fecha,Hora,FechaCreacion,IdDueño,IdVeterinario,IdUsuario) VALUES(?,?,?,?,?,?,?)";

        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cita.getAsunto());
            ps.setDate(2, cita.getFecha());
            ps.setString(3, cita.getHora());
            ps.setDate(4, cita.getFechaCreación());
            ps.setInt(5, cita.getDueño().getIdDueño());
            ps.setInt(6, cita.getVeterinario().getIdVeterinario());
            ps.setInt(7, cita.getUsuario().getIdUsuario());

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

    public void filtrarTableCitas(JTable table, String filtro) {
        String[] titulos = {"IdCita", "Asunto", "Fecha", "Hora", "FechaCreacion", "IdDueño", "NombreDueño", "IdVeterinario", "Nombre", "IdUsuario", "NombreUsuario"};
        String[] registros = new String[11];
        String sql = "SELECT Citas.IdCita , Citas.Asunto,Citas.Fecha,Citas.Hora,Citas.FechaCreacion,Citas.IdDueño , Dueños.NombreDueño , Citas.IdVeterinario, Veterinarios.Nombre,Citas.IdUsuario ,Usuarios.NombreUsuario "
                + "FROM (((Citas "
                + "INNER JOIN Dueños ON Citas.IdDueño = Dueños.IdDueño)  "
                + "INNER JOIN Veterinarios ON Citas.IdVeterinario = Veterinarios.IdVeterinario)  "
                + "INNER JOIN Usuarios ON Citas.IdUsuario = Usuarios.IdUsuario) WHERE Asunto LIKE '*" + filtro + "*'";
        DefaultTableModel model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("Asunto");
                registros[2] = rs.getString("Fecha");
                registros[3] = rs.getString("Hora");
                registros[4] = rs.getString("FechaCreacion");
                registros[5] = rs.getString("IdDueño");
                registros[6] = rs.getString("NombreDueño");
                registros[7] = rs.getString("IdVeterinario");
                registros[8] = rs.getString("Nombre");
                registros[9] = rs.getString("IdUsuario");
                registros[10] = rs.getString("NombreUsuario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
        }
    }

    public void filtrarTableFechaCitas(JTable table, Date fecha1) {
        String[] titulos = {"IdCita", "Asunto", "Fecha", "Hora", "FechaCreacion", "IdDueño", "NombreDueño", "IdVeterinario", "Nombre", "IdUsuario", "NombreUsuario"};
        String[] registros = new String[11];
        String sql = "SELECT Citas.IdCita , Citas.Asunto,Citas.Fecha,Citas.Hora,Citas.FechaCreacion,Citas.IdDueño , Dueños.NombreDueño , Citas.IdVeterinario, Veterinarios.Nombre,Citas.IdUsuario ,Usuarios.NombreUsuario "
                + "FROM (((Citas "
                + "INNER JOIN Dueños ON Citas.IdDueño = Dueños.IdDueño)  "
                + "INNER JOIN Veterinarios ON Citas.IdVeterinario = Veterinarios.IdVeterinario)  "
                + "INNER JOIN Usuarios ON Citas.IdUsuario = Usuarios.IdUsuario)  WHERE Fecha  LIKE  '*" + fecha1 + "*'";

        DefaultTableModel model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("Asunto");
                registros[2] = rs.getString("Fecha");
                registros[3] = rs.getString("Hora");
                registros[4] = rs.getString("FechaCreacion");
                registros[5] = rs.getString("IdDueño");
                registros[6] = rs.getString("NombreDueño");
                registros[7] = rs.getString("IdVeterinario");
                registros[8] = rs.getString("Nombre");
                registros[9] = rs.getString("IdUsuario");
                registros[10] = rs.getString("NombreUsuario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
        }
    }

    public void filtrarTableCedulasCitas(JTable table, String cedula) {
        String[] titulos = {"IdCita", "Asunto", "Fecha", "Hora", "FechaCreacion", "IdDueño", "NombreDueño","Cedula", "IdVeterinario", "Nombre", "IdUsuario", "NombreUsuario"};
        String[] registros = new String[12];
        String sql = "SELECT Citas.IdCita , Citas.Asunto,Citas.Fecha,Citas.Hora,Citas.FechaCreacion,Citas.IdDueño , Dueños.NombreDueño ,Dueños.Cedula, Citas.IdVeterinario, Veterinarios.Nombre,Citas.IdUsuario ,Usuarios.NombreUsuario "
                + "FROM (((Citas "
                + "INNER JOIN Dueños ON Citas.IdDueño = Dueños.IdDueño)  "
                + "INNER JOIN Veterinarios ON Citas.IdVeterinario = Veterinarios.IdVeterinario)  "
                + "INNER JOIN Usuarios ON Citas.IdUsuario = Usuarios.IdUsuario)  WHERE Cedula  LIKE  '*" + cedula+"*'";

        DefaultTableModel model = new DefaultTableModel(null, titulos);
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("IdCita");
                registros[1] = rs.getString("Asunto");
                registros[2] = rs.getString("Fecha");
                registros[3] = rs.getString("Hora");
                registros[4] = rs.getString("FechaCreacion");
                registros[5] = rs.getString("IdDueño");
                registros[6] = rs.getString("NombreDueño");
                registros[7] = rs.getString("Cedula");
                registros[8] = rs.getString("IdVeterinario");
                registros[9] = rs.getString("Nombre");
                registros[10] = rs.getString("IdUsuario");
                registros[11] = rs.getString("NombreUsuario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e) {
        }
    }

    public int eliminarCita(int idCita) {
        int r = 0;
        String sql = "DELETE FROM Citas WHERE IdCita=" + idCita;
        try {
            con = conectar.getConnection();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
        } catch (SQLException e) {
        }
        return r;
    }

}
