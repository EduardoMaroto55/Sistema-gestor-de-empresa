/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Negocio.Cliente;
import java.sql.ResultSet;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ClienteBD {

    private Cliente cliente;

    public ClienteBD() {
    }

    public ClienteBD(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ResultSet Listado() {
        // CONSULTA SQL 
        String sql = "SELECT IdCliente, NombreCliente, Telefono, Correo, Latitud, Longitud FROM Clientes ORDER BY NombreCliente;";
        //CREA LA CONEXION
        Conexion con = new Conexion(sql, true);
        //EJECUTA LA SENTENCIA 
        con.EjecutarSQL();
        //RETORNA RESULTSET CON LOS DATOS
        return con.getResultado();

    }

}
