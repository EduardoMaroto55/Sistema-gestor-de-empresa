/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

import Datos.ClienteBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Cliente {

    private int idCliente;
    private String nombreCliente;
    private String telefono;
    private String correo;
    private double latitud;
    private double longitud;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombreCliente, String telefono, String correo, double latitud, double longitud) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.correo = correo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public ArrayList<Cliente> Listado() {
        ArrayList<Cliente> lista = new ArrayList<>();
        ClienteBD cliente = new ClienteBD();
        ResultSet rs = cliente.Listado();
        try {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("IdCliente"));
                c.setNombreCliente(rs.getString("NombreCliente"));
                c.setTelefono(rs.getString("Telefono"));
                c.setLatitud(rs.getDouble("Latitud"));
                c.setLatitud(rs.getDouble("Latitud"));

                lista.add(c);
            }

        } catch (SQLException e) {
            String error = e.getMessage();

        }
        return lista;

    }

}
