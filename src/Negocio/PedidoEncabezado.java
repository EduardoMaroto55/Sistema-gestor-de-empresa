/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Negocio;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PedidoEncabezado {
    private int IdPedido;
    private int IdUsuario;
    private int IdCliente;
    private double Subtotal;
    private double IVA;
    private double Total;

    public PedidoEncabezado() {
    }

    public PedidoEncabezado(int IdPedido, int IdUsuario, int IdCliente, double Subtotal, double IVA, double Total) {
        this.IdPedido = IdPedido;
        this.IdUsuario = IdUsuario;
        this.IdCliente = IdCliente;
        this.Subtotal = Subtotal;
        this.IVA = IVA;
        this.Total = Total;
    }

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    

}
