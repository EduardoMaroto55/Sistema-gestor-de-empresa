/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

import Negocio.PedidoEncabezado;
import java.sql.ResultSet;


/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PedidoEncabezadoBD {
    PedidoEncabezado encabezado;

    public PedidoEncabezadoBD() {
    }

    public PedidoEncabezadoBD(PedidoEncabezado encabezado) {
        this.encabezado = encabezado;
    }

    public PedidoEncabezado getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(PedidoEncabezado encabezado) {
        this.encabezado = encabezado;
    }
    
    
    public void Insertar(){
        PedidoEncabezado p = this.getEncabezado();
        String sql = "INSERT INTO PedidoEncabezado (IdUsuario, IdCliente, Fecha, Subtotal, IVA, Total) VALUES ("+ p.getIdUsuario() +", "+ p.getIdCliente() +", Now(), 0, 0, 0);";
        Conexion con = new Conexion(sql, false);
        con.EjecutarSQL();
    }
    
    public ResultSet ObtenerPedido(){
        String sql = "SELECT MAX(IdPedidoEncabezado) AS IdPedidoEncabezado FROM PedidoEncabezado WHERE IdUsuario = "+ this.getEncabezado().getIdUsuario() +";";
        Conexion con = new Conexion(sql, true);
        con.EjecutarSQL();
        return con.getResultado();
    }
    
    public void Actualizar(){
        PedidoEncabezado p = this.getEncabezado();
        String sql = "UPDATE PedidoEncabezado SET IdCliente = "+ p.getIdCliente() +" WHERE IdPedidoEncabezado = "+ p.getIdPedido() +";";
        Conexion con = new Conexion(sql, false);
        con.EjecutarSQL();
    }
    

}
