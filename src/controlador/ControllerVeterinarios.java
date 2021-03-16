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
import modelo.Veterinarios;
import modelo.VeterinariosDAO;
import vista.frmVeterinarios;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ControllerVeterinarios implements ActionListener {

    VeterinariosDAO dao = new VeterinariosDAO();
    Veterinarios u = new Veterinarios();
    frmVeterinarios vistaVeterinario = new frmVeterinarios();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerVeterinarios(frmVeterinarios frm) {
        this.vistaVeterinario = frm;
        this.vistaVeterinario.btnGuardar.addActionListener(this);
        this.vistaVeterinario.btnCancelar.addActionListener(this);
        this.vistaVeterinario.btnEliminar.addActionListener(this);
        this.vistaVeterinario.btnRefrescar.addActionListener(this);
        this.vistaVeterinario.btnBuscar.addActionListener(this);
        this.vistaVeterinario.btnEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaVeterinario.btnGuardar) {
            if (vistaVeterinario.txtCedula.getText().isEmpty() || vistaVeterinario.txtNombre.getText().isEmpty() || vistaVeterinario.txtApe1.getText().isEmpty() || vistaVeterinario.txtCodProfesional.getText().isEmpty()|| vistaVeterinario.txtApe2.getText().isEmpty()||vistaVeterinario.txtEmail.getText().isEmpty()||vistaVeterinario.txtDireccion.getText().isEmpty()||vistaVeterinario.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe rellenar todos los espacios");
            } else {
                agregarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinario.btnEditar) {
             if (vistaVeterinario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe selecionar un registro en la tabla para editar");

            } if (vistaVeterinario.txtCedula.getText().isEmpty() || vistaVeterinario.txtNombre.getText().isEmpty() || vistaVeterinario.txtApe1.getText().isEmpty() || vistaVeterinario.txtCodProfesional.getText().isEmpty()|| vistaVeterinario.txtApe2.getText().isEmpty()||vistaVeterinario.txtEmail.getText().isEmpty()||vistaVeterinario.txtDireccion.getText().isEmpty()||vistaVeterinario.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Debe rellenar todos los espacios");
            } else {
                actualizarVeterinario();
            }
        }
        if (e.getSource() == vistaVeterinario.btnCancelar) {
            limpiarCampos();
        }
        if (e.getSource() == vistaVeterinario.btnRefrescar) {
            filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios,"");
        }
        if (e.getSource() == vistaVeterinario.btnEliminar) {
            eliminarVeterinario();
        }
        if (e.getSource() == vistaVeterinario.btnBuscar) {
            filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios, vistaVeterinario.txtBuscar.getText());
        }
    }

    public void agregarVeterinario() {
        String nombre = vistaVeterinario.txtNombre.getText();
        String ape1 = vistaVeterinario.txtApe1.getText();
        String ape2 = vistaVeterinario.txtApe2.getText();
        int cedula = Integer.parseInt(vistaVeterinario.txtCedula.getText());
        int codProfesional = Integer.parseInt(vistaVeterinario.txtCodProfesional.getText());
        String email = vistaVeterinario.txtEmail.getText();
        int telefono = Integer.parseInt(vistaVeterinario.txtTelefono.getText());
        String direccion = vistaVeterinario.txtDireccion.getText();
        Boolean activo = vistaVeterinario.chbSiNo.isSelected();
        u.setNombre(nombre);
        u.setApe1(ape1);
        u.setApe2(ape2);
        u.setCedula(cedula);
        u.setCodProfesional(codProfesional);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setDireccion(direccion);
        u.setActivo(activo);
        int r = dao.agregarVeterinario(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios, "");
        } else {
            JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario no fue registrado");
        }
    }

    public void actualizarVeterinario() {
        if (vistaVeterinario.txtIdVeterinario.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaVeterinario, "Por favor seleccione un registro en la tabla");
        } else {
            int idVeterinario = Integer.parseInt(vistaVeterinario.txtIdVeterinario.getText());
            String nombre = vistaVeterinario.txtNombre.getText();
            String ape1 = vistaVeterinario.txtApe1.getText();
            String ape2 = vistaVeterinario.txtApe2.getText();
            int cedula = Integer.parseInt(vistaVeterinario.txtCedula.getText());
            int codProfesional = Integer.parseInt(vistaVeterinario.txtCodProfesional.getText());
            String email = vistaVeterinario.txtEmail.getText();
            int telefono = Integer.parseInt(vistaVeterinario.txtTelefono.getText());
            String direccion = vistaVeterinario.txtDireccion.getText();
            Boolean activo = vistaVeterinario.chbSiNo.isSelected();
            u.setIdVeterinario(idVeterinario);
            u.setNombre(nombre);
            u.setApe1(ape1);
            u.setApe2(ape2);
            u.setCedula(cedula);
            u.setCodProfesional(codProfesional);
            u.setEmail(email);
            u.setTelefono(telefono);
            u.setDireccion(direccion);
            u.setActivo(activo);
            int r = dao.actualizarVeterinario(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario actualizado con exito");
                limpiarCampos();
                filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios, "");
            } else {
                JOptionPane.showMessageDialog(vistaVeterinario, "Veterinario NO fue actualizado");
            }
        }
    }

    public void eliminarVeterinario() {
        int fila = vistaVeterinario.tableVeterinarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaVeterinario.tableVeterinarios, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaVeterinario.tableVeterinarios.getValueAt(fila, 0).toString());
            dao.eliminarVeterinario(id);
            JOptionPane.showMessageDialog(vistaVeterinario.tableVeterinarios, "Usuario eliminado con exito");
            filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios, "");
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
        vistaVeterinario.txtIdVeterinario.setText("");
        vistaVeterinario.txtNombre.setText("");
        vistaVeterinario.txtApe1.setText("");
        vistaVeterinario.txtApe2.setText("");
        vistaVeterinario.txtCedula.setText("");
        vistaVeterinario.txtCodProfesional.setText("");
        vistaVeterinario.txtEmail.setText("");
        vistaVeterinario.txtTelefono.setText("");
        vistaVeterinario.txtDireccion.setText("");
        vistaVeterinario.chbSiNo.setSelected(false);
    }

    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaVeterinario.tableVeterinarios, "");
    }

}
