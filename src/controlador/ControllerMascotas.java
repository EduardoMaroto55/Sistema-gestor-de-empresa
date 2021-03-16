/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.DueñosDAO;
import modelo.Dueños;
import vista.frmMascotas;
import modelo.Mascotas;
import modelo.MascotasDAO;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerMascotas implements ActionListener {

    Mascotas m = new Mascotas();
    MascotasDAO mascotasDAO = new MascotasDAO();
    frmMascotas vistaMascota = new frmMascotas();
    Dueños dueño = new Dueños();
    DueñosDAO dueñoDAO = new DueñosDAO();

    public ControllerMascotas(frmMascotas frm) {
        this.vistaMascota = frm;
        this.vistaMascota.btnGuardar.addActionListener(this);
        this.vistaMascota.btnEditar.addActionListener(this);
        this.vistaMascota.btnEliminar.addActionListener(this);
        this.vistaMascota.btnRefrescar.addActionListener(this);
        this.vistaMascota.btnBuscar.addActionListener(this);
        this.vistaMascota.btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaMascota.btnGuardar) {
            if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else if (vistaMascota.cbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un genero");
            } else if (vistaMascota.cbTipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un tipo");
            } else if (vistaMascota.txtRaza.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar una raza");
            } else if (vistaMascota.txtIdDueño.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un id dueño");
            } else {
                agregarMascota();               
                limpiarCampos();
            }
        }
        if (e.getSource() == vistaMascota.btnEditar) {
            if (vistaMascota.txtIdMascota.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un registro en la tabla");
            } else if (vistaMascota.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar un nombre");
            } else if (vistaMascota.cbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un genero");
            } else if (vistaMascota.cbTipo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un tipo");
            } else if (vistaMascota.txtRaza.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe ingresar una raza");
            } else if (vistaMascota.txtIdDueño.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaMascota, "Debe seleccionar un id dueño");
            } else {
                actualizarMascota();
               
            }
        }
        if (e.getSource() == vistaMascota.btnEliminar) {
            eliminarMascota();
        }
        if (e.getSource() == vistaMascota.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaMascota.btnBuscar) {
           filtrarTablaPorNombre(vistaMascota.tableMascotas, vistaMascota.txtBuscar.getText());
            
        }
        if (e.getSource() == vistaMascota.btnRefrescar) {
            filtrarTablaPorNombre(vistaMascota.tableMascotas, "");
        }

    }

    public void agregarMascota() {
        String nombre = vistaMascota.txtNombre.getText();
        String genero = vistaMascota.cbGenero.getSelectedItem().toString();
        String tipo = vistaMascota.cbTipo.getSelectedItem().toString();
        String raza = vistaMascota.txtRaza.getText();
        Boolean activo = vistaMascota.chbSiNo.isSelected();
        Integer idDueño = Integer.valueOf(vistaMascota.txtIdDueño.getText());
        dueño.setIdDueño(idDueño);

        m.setNombre(nombre);
        m.setGenero(genero);
        m.setTipo(tipo);
        m.setRaza(raza);
        m.setActivo(activo);
        m.setDueño(dueño);
        int r = mascotasDAO.agregarMascota(m);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota registrado con exito");
            filtrarTablaPorNombre(vistaMascota.tableMascotas, "");
        } else {
            JOptionPane.showMessageDialog(vistaMascota, "Mascota no fue registrado");
        }
    }

    public void actualizarMascota() {
        if (vistaMascota.txtIdMascota.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaMascota, "Por favor seleccione un registro en la tabla");
        } else {
            int idMascota = Integer.parseInt(vistaMascota.txtIdMascota.getText());
            String nombre = vistaMascota.txtNombre.getText();
            String genero = vistaMascota.cbGenero.getSelectedItem().toString();
            String tipo = vistaMascota.cbTipo.getSelectedItem().toString();
            String raza = vistaMascota.txtRaza.getText();
            Boolean activo = vistaMascota.chbSiNo.isSelected();
            Integer idDueño = Integer.valueOf(vistaMascota.txtIdDueño.getText());
            dueño.setIdDueño(idDueño);
            m.setIdMascota(idMascota);
            m.setNombre(nombre);
            m.setGenero(genero);
            m.setTipo(tipo);
            m.setRaza(raza);
            m.setActivo(activo);
            m.setDueño(dueño);
            int r = mascotasDAO.actualizarMascota(m);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaMascota, "Mascotas actualizado con exito");
                limpiarCampos();
                filtrarTablaPorNombre(vistaMascota.tableMascotas, "");
            } else {
                JOptionPane.showMessageDialog(vistaMascota, "Mascotas NO fue actualizado");
            }
        }
    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i--;
            //i=i-1;
        }
    }
    public void eliminarMascota(){
         int fila = vistaMascota.tableMascotas.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaMascota.tableMascotas, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaMascota.tableMascotas.getValueAt(fila, 0).toString());
            mascotasDAO.eliminarMascota(id);
            JOptionPane.showMessageDialog(vistaMascota.tableMascotas, "Mascota eliminada con exito");
            filtrarTablaPorNombre(vistaMascota.tableMascotas, "");
            limpiarCampos();
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        mascotasDAO.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {
        vistaMascota.txtRaza.setText("");
        vistaMascota.txtIdDueño.setText("");
        vistaMascota.txtIdMascota.setText("");
        vistaMascota.txtNombre.setText("");
        vistaMascota.cbGenero.setSelectedIndex(0);
        vistaMascota.cbTipo.setSelectedIndex(0);
        vistaMascota.chbSiNo.setSelected(false);
        cargarComboMascotas();
    }

    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaMascota.tableMascotas, "");
    }

    public void cargarComboMascotas() {
        dueñoDAO.cargarComboDueños(vistaMascota.cbIdDueño);

    }

}
