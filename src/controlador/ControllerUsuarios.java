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
import modelo.Usuarios;
import modelo.UsuariosDAO;
import vista.frmUsuarios;

/**
 *
 * @author edu5m
 */
public class ControllerUsuarios implements ActionListener {

    UsuariosDAO dao = new UsuariosDAO();
    Usuarios u = new Usuarios();
    frmUsuarios vistaUsuario = new frmUsuarios();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControllerUsuarios(frmUsuarios frm) {
        this.vistaUsuario = frm;
        this.vistaUsuario.btnGuardar.addActionListener(this);
        this.vistaUsuario.btnCancelar.addActionListener(this);
        this.vistaUsuario.btnEliminar.addActionListener(this);
        this.vistaUsuario.btnRefrescar.addActionListener(this);
        this.vistaUsuario.btnBuscar.addActionListener(this);
        this.vistaUsuario.btnEditar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaUsuario.btnGuardar) {
            if (vistaUsuario.txtNombreUsuario.getText().isEmpty() || vistaUsuario.txtNombre.getText().isEmpty() || vistaUsuario.txtApe1.getText().isEmpty() || vistaUsuario.txtPassword.getText().isEmpty() || vistaUsuario.txtApe2.getText().isEmpty() || vistaUsuario.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe rellenar todos los espacios");
            } else if (vistaUsuario.cbTipoUsuario.getSelectedItem().equals("SELECCIONE UN TIPO DE USUARIO")) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un tipo de usuario");

            } else if (vistaUsuario.txtPassword.getText().length() < 5) {
                JOptionPane.showMessageDialog(vistaUsuario, "La contraseña debe tener por lo menos 5 caracteres");

            } else {
                agregarUsuario();
            }

        }
        if (e.getSource() == vistaUsuario.btnEditar) {
            if (vistaUsuario.txtNombre.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe selecionar un registro en la tabla para editar");

            }
            if (vistaUsuario.txtNombreUsuario.getText().isEmpty() || vistaUsuario.txtNombre.getText().isEmpty() || vistaUsuario.txtApe1.getText().isEmpty() || vistaUsuario.txtPassword.getText().isEmpty() || vistaUsuario.txtApe2.getText().isEmpty() || vistaUsuario.txtEmail.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe rellenar todos los espacios");
            } else if (vistaUsuario.cbTipoUsuario.getSelectedItem().equals("SELECCIONE UN TIPO DE USUARIO")) {
                JOptionPane.showMessageDialog(vistaUsuario, "Debe seleccionar un tipo de usuario");

            } else if (vistaUsuario.txtPassword.getText().length() < 5) {
                JOptionPane.showMessageDialog(vistaUsuario, "La contraseña debe tener por lo menos 5 caracteres");

            } else {
                actualizarUsuario();
            }

        }
        if (e.getSource() == vistaUsuario.btnCancelar) {
            limpiarCampos();

        }
        if (e.getSource() == vistaUsuario.btnRefrescar) {
            filtrarTablaPorNombre(vistaUsuario.tableUsuarios, "");
        }
        if (e.getSource() == vistaUsuario.btnEliminar) {
            eliminarUsuario();

        }
        if (e.getSource() == vistaUsuario.btnBuscar) {
            filtrarTablaPorNombre(vistaUsuario.tableUsuarios, vistaUsuario.txtBuscar.getText());
        }

    }

    public void agregarUsuario() {
        String nombre = vistaUsuario.txtNombre.getText();
        String ape1 = vistaUsuario.txtApe1.getText();
        String ape2 = vistaUsuario.txtApe2.getText();
        String email = vistaUsuario.txtEmail.getText();
        String nombreUsuario = vistaUsuario.txtNombreUsuario.getText();
        String password = vistaUsuario.txtPassword.getText();
        String tipoUsuario = vistaUsuario.cbTipoUsuario.getSelectedItem().toString();
        Boolean activo = vistaUsuario.chbSiNo.isSelected();
        u.setNombre(nombre);
        u.setApe1(ape1);
        u.setApe2(ape2);
        u.setEmail(email);
        u.setNombreUsuario(nombreUsuario);
        u.setPassword(password);
        u.setTipoUsuario(tipoUsuario);
        u.setActivo(activo);
        int r = dao.agregarUsuario(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario registrado con exito");
            limpiarCampos();
            filtrarTablaPorNombre(vistaUsuario.tableUsuarios, "");
        } else {
            JOptionPane.showMessageDialog(vistaUsuario, "Usuario no fue registrado");
        }
    }

    /*
    *Actualizar Usuarios
     */
    public void actualizarUsuario() {
        if (vistaUsuario.txtIdUsuario.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaUsuario, "Por favor seleccione un registro en la tabla");
        } else {
            int idUsuario = Integer.parseInt(vistaUsuario.txtIdUsuario.getText());
            String nombre = vistaUsuario.txtNombre.getText();
            String ape1 = vistaUsuario.txtApe1.getText();
            String ape2 = vistaUsuario.txtApe2.getText();
            String email = vistaUsuario.txtEmail.getText();
            String nombreUsuario = vistaUsuario.txtNombreUsuario.getText();
            String password = vistaUsuario.txtPassword.getText();
            String tipoUsuario = vistaUsuario.cbTipoUsuario.getSelectedItem().toString();
            Boolean activo = vistaUsuario.chbSiNo.isSelected();
            u.setIdUsuario(idUsuario);
            u.setNombre(nombre);
            u.setApe1(ape1);
            u.setApe2(ape2);
            u.setEmail(email);
            u.setNombreUsuario(nombreUsuario);
            u.setPassword(password);
            u.setTipoUsuario(tipoUsuario);
            u.setActivo(activo);
            int r = dao.actualizarUsuario(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaUsuario, "Usuario actualizado con exito");
                limpiarCampos();
                filtrarTablaPorNombre(vistaUsuario.tableUsuarios, "");
            } else {
                JOptionPane.showMessageDialog(vistaUsuario, "Usuario NO fue actualizado");
            }
        }
    }

    /*
    *Elimiar Usuario
     */
    public void eliminarUsuario() {
        int fila = vistaUsuario.tableUsuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaUsuario.tableUsuarios, "Debe seleccionar un registro en la tabla");
        } else {
            int id = Integer.parseInt(vistaUsuario.tableUsuarios.getValueAt(fila, 0).toString());
            dao.eliminarUsuario(id);
            JOptionPane.showMessageDialog(vistaUsuario.tableUsuarios, "Usuario eliminado con exito");
            filtrarTablaPorNombre(vistaUsuario.tableUsuarios, "");
            limpiarCampos();
        }
    }

    /*
    * Listar Usuarios
     */

 /*
    *Metodo limpia la tabla 
     */
    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i--;
            //i=i-1;
        }
    }

    /**
     * filtrar por nombre de usuario
     *
     * @param table
     * @param filtro
     */
    public void filtrarTablaPorNombre(JTable table, String filtro) {
        dao.filtrarTablaPorNombre(table, filtro);
    }

    public void limpiarCampos() {
        vistaUsuario.txtIdUsuario.setText("");
        vistaUsuario.txtNombre.setText("");
        vistaUsuario.txtApe1.setText("");
        vistaUsuario.txtApe2.setText("");
        vistaUsuario.txtEmail.setText("");
        vistaUsuario.txtNombreUsuario.setText("");
        vistaUsuario.txtPassword.setText("");
        vistaUsuario.cbTipoUsuario.setSelectedIndex(0);
        vistaUsuario.chbSiNo.setSelected(false);
    }

    /*
    * Metodo para iniciar 
     */
    public void iniciar() {
        limpiarCampos();
        filtrarTablaPorNombre(vistaUsuario.tableUsuarios, "");
    }
}
