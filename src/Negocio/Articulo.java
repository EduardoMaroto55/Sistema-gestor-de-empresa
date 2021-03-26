/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;
import Datos.ArticuloBD;
import java.sql.ResultSet;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Articulo {
    private  int idArticulo;
    private String  codigoBarras;
    private String  nombreArticulo;
    private double precio;
    private double IVA;
    private int saldo;

    public Articulo() {
    }

    public Articulo(int idArticulo, String codigoBarras, String nombreArticulo, double precio, double IVA, int saldo) {
        this.idArticulo = idArticulo;
        this.codigoBarras = codigoBarras;
        this.nombreArticulo = nombreArticulo;
        this.precio = precio;
        this.IVA = IVA;
        this.saldo = saldo;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    
    public void ConsultaCodigo(){
        ArticuloBD articulo = new ArticuloBD();
        articulo.setArticulo(this);
        ResultSet rs = articulo.ConsultaCodigo();
        try {
            if (rs.first()) {
                this.setIdArticulo(rs.getInt("IdArticulo"));
                this.setCodigoBarras(rs.getString("CodigoBarras"));
                this.setNombreArticulo(rs.getString("NombreArticulo"));
                this.setPrecio(rs.getDouble("Precio"));
                this.setIVA(rs.getDouble("IVA"));
                this.setSaldo(rs.getInt("Saldo"));
            }
        } catch (Exception e) {
            String error = e.getMessage();
                  
        }
    
    }

}
