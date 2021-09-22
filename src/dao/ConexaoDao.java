/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando Kramer
 */
public class ConexaoDao{
    
    private static final String URL = "jdbc:mysql://localhost:3306/projeto?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    
    public Connection con;
    public PreparedStatement stmt;
    public ResultSet rs;
    
    public Connection conectar(){
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectou-se!!!");
            return con;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Conectar-se ao banco de dados!!!");
            System.out.println(e);
            return null;
        }
    }
    
    public void desconectar(Connection con){
        try {
            if(rs != null && !rs.isClosed()){
                rs.close();
            }
            if(stmt != null && !stmt.isClosed()){
                stmt.close();
            }
            if(con != null && !con.isClosed()){
                con.close();
                System.out.println("Desconectou-se!!!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar-se do banco de dados!!!");
            System.out.println(e);
        }
    }
    
}
