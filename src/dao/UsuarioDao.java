/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.Pessoa;
import entity.Usuario;
import java.sql.SQLException;

/**
 *
 * @author Fernando Kramer
 */
public class UsuarioDao extends ConexaoDao{
    
    public static final String SELECT_USUARIO_ID_PESSOA = "SELECT * FROM usuario WHERE id_pessoa_fk = ?";
    
    public static final String INSERT_USUARIO = "INSERT INTO usuario(nome_usuario, senha, id_pessoa_fk)VALUES(?,?,(SELECT id_pessoa FROM pessoa WHERE cpf = ?));";
    
    public static final String UPDATE_USUARIO = "UPDATE usuario SET nome_usuario = ?, senha = ? WHERE id_pessoa_fk = (SELECT id_pessoa FROM pessoa WHERE cpf = ?);";
    
    public static final String SELECT_USUARIO = "SELECT * FROM usuario WHERE nome_usuario = ? AND senha = ?";
    
    public Usuario buscarUsuarioPeloIdPessoa(Pessoa psa){
        Pessoa ps = psa;
        Usuario us = new Usuario();
        try {
            conectar();
            stmt = con.prepareStatement(SELECT_USUARIO_ID_PESSOA);
            stmt.setInt(1, ps.getId());
            rs = stmt.executeQuery();
                      
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    us.setNomeUsuario(rs.getString("nome_usuario"));
                    us.setSenha(rs.getString("senha"));
                    us.setPessoa(ps);
                }
            }else{
                us = null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar Usuário!!!");
            System.out.println(e);
            return null;            
        }finally{
            desconectar(con);
        } 
        return us;
    }
    
    public void salvarUsuario(Usuario us){
        System.out.println("1");
        try {
            System.out.println("2");
            conectar();
            stmt = con.prepareStatement(INSERT_USUARIO);
            stmt.setString(1, us.getNomeUsuario());
            stmt.setString(2, us.getSenha());
            stmt.setString(3, us.getPessoa().getCpf());
            stmt.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar cadastro de Usuário!!!");
            System.out.println(e);
        }finally{
            System.out.println("3");
            desconectar(con);
        }
    }
    
    public void atualizarUsuario(Usuario us){
        try {
            conectar();
            stmt = con.prepareStatement(UPDATE_USUARIO);
            stmt.setString(1, us.getNomeUsuario());
            stmt.setString(2, us.getSenha());
            stmt.setString(3, us.getPessoa().getCpf());
            stmt.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao Atualizar cadastro de Usuário!!!");
            System.out.println(e);
        }finally{
            desconectar(con);
        } 
    }
    
    public boolean fazerLogin(Usuario us){
        boolean logou = false;
        try {
            conectar();
            stmt = con.prepareStatement(SELECT_USUARIO);
            stmt.setString(1, us.getNomeUsuario());
            stmt.setString(2, us.getSenha());
            rs = stmt.executeQuery();
                      
            if(rs.isBeforeFirst()){
                while(rs.next()){
                    logou = true;
                }
            }else{
                logou = false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar Usuário!!!");
            System.out.println(e);
            return false;            
        }finally{
            desconectar(con);
        } 
        return logou;
    }
    
}
