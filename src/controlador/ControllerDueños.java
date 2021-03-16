/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Dueños;
import modelo.DueñosDAO;
import vista.frmDueños;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerDueños implements ActionListener {

    DueñosDAO dao = new DueñosDAO();
    Dueños u = new Dueños();
    frmDueños vistaDueño = new frmDueños();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerDueños(frmDueños frm) {
        this.vistaDueño = frm;
        this.vistaDueño.btnGuardar.addActionListener(this);
        this.vistaDueño.btnCancelar.addActionListener(this);
        this.vistaDueño.btnEliminar.addActionListener(this);
        this.vistaDueño.btnRefrescar.addActionListener(this);
        this.vistaDueño.btnBuscar.addActionListener(this);
        this.vistaDueño.btnEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaDueño.btnGuardar) {
            if (vistaDueño.txtCedula.getText().isEmpty() || vistaDueño.txtNombreDueño.getText().isEmpty() || vistaDueño.txtApe1.getText().isEmpty() || vistaDueño.txtApe2.getText().isEmpty() || vistaDueño.txtEmail.getText().isEmpty() || vistaDueño.txtDireccion.getText().isEmpty() || vistaDueño.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueño, "Debe rellenar todos los espacios");
            }
            if (vistaDueño.cbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaDueño, "Debe seleccionar una categoria");

            }
            if (vistaDueño.txtCedula.getText().length() < 9) {
                JOptionPane.showMessageDialog(vistaDueño, "La cedula debe tener por lo menos 9 numeros");
            } else {
                agregarDueño();
            }
        }
        if (e.getSource() == vistaDueño.btnEditar) {
            if (vistaDueño.txtNombreDueño.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueño, "Debe selecionar un registro en la tabla para editar");

            }
            if (vistaDueño.txtCedula.getText().isEmpty() || vistaDueño.txtNombreDueño.getText().isEmpty() || vistaDueño.txtApe1.getText().isEmpty() || vistaDueño.txtApe2.getText().isEmpty() || vistaDueño.txtEmail.getText().isEmpty() || vistaDueño.txtDireccion.getText().isEmpty() || vistaDueño.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaDueño, "Debe rellenar todos los espacios");
            }
            if (vistaDueño.cbGenero.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(vistaDueño, "Debe seleccionar una categoria");

            }
            if (vistaDueño.txtCedula.getText().length() < 9) {
                JOptionPane.showMessageDialog(vistaDueño, "La cedula debe tener por lo menos 9 numeros");
            } else {
                actualizarDueño();
            }
        }
        if (e.getSource() == vistaDueño.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaDueño.btnRefrescar) {
            filtrarTablaPorNombre(vistaDueño.tableDueños, "");
        }
        if (e.getSource() == vistaDueño.btnEliminar) {
            eliminarDueño();
        }
        if (e.getSource() == vistaDueño.btnBuscar) {
            filtrarTablaPorNombre(vistaDueño.tableDueños, vistaDueño.txtBuscar.getText());
        }

    }

    public void agregarDueño() {
        String nombre = vistaDueño.txtNombreDueño.getText();
        String ape1 = vistaDueño.txtApe1.getText();
        String ape2 = vistaDueño.txtApe2.getText();
        int cedula = Integer.parseInt(vistaDueño.txtCedula.getText());
        String genero = vistaDueño.cbGenero.getSelectedItem().toString();
        String email = vistaDueño.txtEmail.getText();
        int telefono = Integer.parseInt(vistaDueño.txtTelefono.getText());
        String direccion = vistaDueño.txtDireccion.getText();
        u.setNombre(nombre);
        u.setApe1(ape1);
        u.setApe2(ape2);
        u.setCedula(cedula);
        u.setGenero(genero);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setDireccion(direccion);
        int r = dao.agregarDueño(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaDueño, "Dueño registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaDueño.tableDueños, "");
        } else {
            JOptionPane.showMessageDialog(vistaDueño, "Dueño no fue registrado");
        }
    }

    public void actualizarDueño() {
        if (vistaDueño.txtIdDueño.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaDueño, "Por favor seleccione un registro en la tabla");
        } else {
            int idDueño = Integer.parseInt(vistaDueño.txtIdDueño.getText());
            String nombre = vistaDueño.txtNombreDueño.getText();
            String ape1 = vistaDueño.txtApe1.getText();
            String ape2 = vistaDueño.txtApe2.getText();
            int cedula = Integer.parseInt(vistaDueño.txtCedula.getText());
            String genero = vistaDueño.cbGenero.getSelectedItem().toString();
            String email = vistaDueño.txtEmail.getText();
            int telefono = Integer.parseInt(vistaDueño.txtTelefono.getText());
            String direccion = vistaDueño.txtDireccion.getText();
            u.setIdDueño(idDueño);
            u.setNombre(nombre);
            u.setApe1(ape1);
            u.setApe2(ape2);
            u.setCedula(cedula);
            u.setGenero(genero);
            u.setEmail(email);
            u.setTelefono(telefono);
            u.setDireccion(direccion);

            int r = dao.actualizarDueño(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaDueño, "Dueño actualizado con exito");
                limpiarCampos();
                filtrarTablaPorNombre(vistaDueño.tableDueños, "");
            } else {
                JOptionPane.showMessageDialog(vistaDueño, "Dueño NO fue actualizado");
            }
        }
    }

    public void eliminarDueño() {
        int fila = vistaDueño.tableDueños.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaDueño.tableDueños, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaDueño.tableDueños.getValueAt(fila, 0).toString());
            dao.eliminarDueño(id);
            JOptionPane.showMessageDialog(vistaDueño.tableDueños, "Dueño eliminado con exito");
            filtrarTablaPorNombre(vistaDueño.tableDueños, "");
            limpiarCampos();
        }
    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i--;
            //i=i-1;
        }
    }

    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {
        vistaDueño.txtIdDueño.setText("");
        vistaDueño.txtNombreDueño.setText("");
        vistaDueño.txtApe1.setText("");
        vistaDueño.txtApe2.setText("");
        vistaDueño.txtCedula.setText("");
        vistaDueño.cbGenero.setSelectedIndex(0);
        vistaDueño.txtEmail.setText("");
        vistaDueño.txtTelefono.setText("");
        vistaDueño.txtDireccion.setText("");

    }

    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaDueño.tableDueños, "");
    }

}
