/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Conexion {
    //Hola esto es un cambio en Git hub desde la web
    

    private String sql;
    private boolean resultadoEspe;
    private ResultSet resultado;

    public Conexion() {
    }

    public Conexion(String sql, boolean resultadoEspe) {
        this.sql = sql;
        this.resultadoEspe = resultadoEspe;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean isResultadoEspe() {
        return resultadoEspe;
    }

    public void setResultadoEspe(boolean resultadoEspe) {
        this.resultadoEspe = resultadoEspe;
    }

    public ResultSet getResultado() {
        return resultado;
    }

    public void setResultado(ResultSet resultado) {
        this.resultado = resultado;
    }

    //Metodos personalizados
    public void EjecutarSQL() {
        try {
            //Crea la conexion a la base de datos
            Connection cnn;
            Statement st ;
            Class.forName("com.mysql.jdbc.Driver");
            //String de conxion
            cnn = DriverManager.getConnection("jdbc:mysql://localhost/progra3?user=progra3&password=12345&useSSL=false");
            st = cnn.createStatement();
            if (resultadoEspe) {
                resultado = st.executeQuery(sql);
            } else {
                st.executeUpdate(sql);
                 st.close();
                cnn.close();
            }
           

        } catch (Exception e) {
            String error = e.getMessage();
            System.out.println(error);
        }
    }

}
