/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import helpers.Helpers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import modelo.CitasDAO;
import modelo.DueñosDAO;
import vista.frmCitasDueño;


/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerCitasDueño implements ActionListener {

    CitasDAO citasDAO = new CitasDAO();
    Helpers help = new Helpers();
    frmCitasDueño vistaCitasDueño = new frmCitasDueño();
    DueñosDAO dueñosDAO = new DueñosDAO();

    public ControllerCitasDueño(frmCitasDueño frm) {
        this.vistaCitasDueño = frm;
        this.vistaCitasDueño.btnBuscar.addActionListener(this);
        this.vistaCitasDueño.btnImprimirR.addActionListener(this);
        this.vistaCitasDueño.btnRefrescar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCitasDueño.btnBuscar) {                  
           filtrarTableDueñoCitas(vistaCitasDueño.tableDueñoCitas, vistaCitasDueño.txtCDueño.getText());
        }
        if (e.getSource() == vistaCitasDueño.btnImprimirR) {
            try {
                help.toExcel(vistaCitasDueño.tableDueñoCitas, "exel");
            } catch (IOException ex) {
                Logger.getLogger(ControllerCitasDueño.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource()==vistaCitasDueño.btnRefrescar) {
            filtrarTableCitas(vistaCitasDueño.tableDueñoCitas, "");
        }

    }

    public void cargarComboDueños() {
        dueñosDAO.cargarComboDueños(vistaCitasDueño.cbCedulaDueño);

    }
    public void filtrarTableDueñoCitas(JTable table, String cedula) {
        citasDAO.filtrarTableCedulasCitas(table, cedula);
        help.centrarCeldas(table);
    }

    public void filtrarTableCitas(JTable table, String filtro) {
        citasDAO.filtrarTableCitas(table, filtro);
        help.centrarCeldas(table);
    }

    public void iniciar() {
        filtrarTableCitas(vistaCitasDueño.tableDueñoCitas, "");
        cargarComboDueños();

    }
}
