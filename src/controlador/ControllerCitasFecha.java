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
import vista.frmCitasFecha;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerCitasFecha implements ActionListener {

    CitasDAO citasDAO = new CitasDAO();
    Helpers help = new Helpers();
    frmCitasFecha vistaCitasFecha = new frmCitasFecha();

    public ControllerCitasFecha(frmCitasFecha frm) {
        this.vistaCitasFecha = frm;
        this.vistaCitasFecha.btnBuscar.addActionListener(this);
        this.vistaCitasFecha.btnImprimirR.addActionListener(this);
        this.vistaCitasFecha.btnRefrescar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaCitasFecha.btnBuscar) {
            Date fecha1 = vistaCitasFecha.dcInicial.getDate();
            java.sql.Date sqlF1 = new java.sql.Date(fecha1.getTime());
            filtrarTableFechaCitas(vistaCitasFecha.tableFechaCitas, sqlF1);
        }
        if (e.getSource() == vistaCitasFecha.btnImprimirR) {
            try {
                help.toExcel(vistaCitasFecha.tableFechaCitas, "exel");
            } catch (IOException ex) {
                Logger.getLogger(ControllerCitasFecha.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == vistaCitasFecha.btnRefrescar) {
            filtrarTableCitas(vistaCitasFecha.tableFechaCitas, "");

        }

    }

    public void filtrarTableFechaCitas(JTable table, Date fecha1) {
        citasDAO.filtrarTableFechaCitas(table, fecha1);
        help.centrarCeldas(table);
    }

    public void filtrarTableCitas(JTable table, String filtro) {
        citasDAO.filtrarTableCitas(table, filtro);
        help.centrarCeldas(table);
    }

    public void iniciar() {
        filtrarTableCitas(vistaCitasFecha.tableFechaCitas, "");

    }
}
