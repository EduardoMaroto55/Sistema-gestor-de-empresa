/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import modelo.Citas;
import modelo.CitasDAO;
import modelo.Dueños;
import modelo.DueñosDAO;
import modelo.Usuarios;
import modelo.Veterinarios;
import modelo.VeterinariosDAO;
import vista.frmCitas;
import vista.frmVentanaPrincipal;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerCitas implements ActionListener {

    Citas cita = new Citas();
    CitasDAO citaDAO = new CitasDAO();
    Dueños dueño = new Dueños();
    Usuarios usuario = new Usuarios();
    Veterinarios veterinario = new Veterinarios();
    frmCitas vistaCitas = new frmCitas();
    VeterinariosDAO veterinarioDAO = new VeterinariosDAO();
    DueñosDAO dueñosDAO = new DueñosDAO();
    Helpers help = new Helpers();

    public ControllerCitas(frmCitas frm) {
        this.vistaCitas = frm;
        this.vistaCitas.btnAgregar.addActionListener(this);
        this.vistaCitas.btnCancelar.addActionListener(this);
        this.vistaCitas.btneEliminar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCitas.btnAgregar) {
            if (vistaCitas.cbIdDueño.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe seleccionar un dueño");
            } else if (help.Validar_CampoHora(vistaCitas.txtHoraCita.getText(), vistaCitas.cbAMPM.getSelectedItem().toString()) == false) {
                JOptionPane.showMessageDialog(vistaCitas, "La hora ingresada no es valida");
            } else if (vistaCitas.cbIdVeterinario.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaCitas, "Debe seleccionar un veterinario");
            } else {
                try {
                    agregarCita();
                    limpiarCampos();
                } catch (ParseException ex) {

                }
            }

        }
        if (e.getSource() == vistaCitas.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaCitas.btneEliminar) {
            eliminarCita();
            limpiarCampos();
        }

    }

    public void agregarCita() throws ParseException {
        String asunto = vistaCitas.txtAsunto.getText();
        Date fecha = vistaCitas.dcFechaCita.getDate();
        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
        String hora = vistaCitas.txtHoraCita.getText();
        Integer idDueño = Integer.valueOf(vistaCitas.txtIdDueño.getText());
        Integer idVeterinario = Integer.valueOf(vistaCitas.txtVeterinario.getText());;
        Integer idUsuario = Integer.valueOf(frmVentanaPrincipal.lblIdUsuario.getText());
        dueño.setIdDueño(idDueño);
        veterinario.setIdVeterinario(idVeterinario);
        usuario.setIdUsuario(idUsuario);
        cita.setAsunto(asunto);
        cita.setFecha(sqlFecha);
        cita.setHora(hora);
        cita.setFechaCreación(help.fechaActual());
        cita.setDueño(dueño);
        cita.setVeterinario(veterinario);
        cita.setUsuario(usuario);

        int r = citaDAO.agregarCita(cita);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaCitas, "Cita registrada con exito");

        } else {
            JOptionPane.showMessageDialog(vistaCitas, "Cita no fue registrada ");
        }
    }

    public void cargarComboDueños() {
        dueñosDAO.cargarComboDueños(vistaCitas.cbIdDueño);

    }

    public void cargarComboVeterinarios() {
        veterinarioDAO.cargarComboVeterinarios(vistaCitas.cbIdVeterinario);
    }

    public void limpiarCampos() {
        vistaCitas.txtAsunto.setText("");
        vistaCitas.txtIdDueño.setText("");
        vistaCitas.txtVeterinario.setText("");
        vistaCitas.dcFechaCita.setDate(null);
        vistaCitas.txtHoraCita.setText("");
        cargarComboDueños();
        cargarComboVeterinarios();

    }

    public void filtrarTableCitas(JTable table, String filtro) {
        citaDAO.filtrarTableCitas(table, filtro);
        help.centrarCeldas(table);
    }

    public void eliminarCita() {
        int fila = vistaCitas.tableAnularCitas.getSelectedRow();
        if (fila < -1) {
            JOptionPane.showMessageDialog(vistaCitas, "Debe seleccionar un registro");

        } else {
            int idCita = Integer.parseInt(vistaCitas.tableAnularCitas.getValueAt(fila, 0).toString());
            int r = citaDAO.eliminarCita(idCita);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaCitas, "Cita anulada con exito");

                filtrarTableCitas(vistaCitas.tableAnularCitas, "");
            }
        }
    }

    public void iniciar() {
        filtrarTableCitas(vistaCitas.tableAnularCitas, "");
        limpiarCampos();

    }

}
