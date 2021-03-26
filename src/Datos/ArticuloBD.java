/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datos;

import Negocio.Articulo;
import java.sql.ResultSet;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ArticuloBD {
    public Articulo articulo;

    public ArticuloBD() {
    }

    public ArticuloBD(Articulo articulo) {
        this.articulo = articulo;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }
    
    public ResultSet ConsultaCodigo(){
        String sql = "SELECT IdArticulo, CodigoBarras, NombreArticulo, Precio, IVA, Saldo FROM Articulos WHERE CodigoBarras = '"+ this.getArticulo().getCodigoBarras() +"';";
        Conexion cn = new Conexion(sql, true);
        cn.EjecutarSQL();
        return cn.getResultado();
    }

}
