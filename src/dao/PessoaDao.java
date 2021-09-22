/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Contato;
import entity.Endereco;
import entity.Pessoa;
import java.sql.SQLException;

/**
 *
 * @author Fernando Kramer
 */
public class PessoaDao extends ConexaoDao{
    
     public static final String INSERT_PESSOA = "INSERT INTO pessoa(cpf,nome,data_nascimento,sexo)"
            + " values(?,?,?,?);";
    
    public static final String INSERT_CONTATO = "INSERT INTO contato(email,celular,tipo_celular,whatsapp,telefone,tipo_telefone,id_pessoa_fk)"
            + "VALUES(?,?,?,?,?,?,(SELECT id_pessoa from pessoa where cpf = ?));";
    
    public static final String INSERT_ENDERECO = "INSERT INTO endereco(cep,pais,estado,uf,cidade,bairro,rua,numero,complemento,id_pessoa_fk)"
            + "VALUES(?,?,?,?,?,?,?,?,?,(SELECT id_pessoa from pessoa where cpf = ?));";
    
    public static final String SELECT_PESSOA = "SELECT * FROM pessoa "
            + "INNER JOIN contato ON (pessoa.id_pessoa = contato.id_pessoa_fk) "
            + "INNER JOIN endereco ON (pessoa.id_pessoa = endereco.id_pessoa_fk) "
            + "where cpf = ?;";
    
    public static final String UPDATE_ENDERECO = "UPDATE endereco "
            + "SET cep = ?, pais = ?, estado = ?, uf = ?, cidade = ?, bairro = ?, rua = ?, numero = ?, complemento = ? "
            + "WHERE id_pessoa_fk = (SELECT id_pessoa FROM PESSOA WHERE cpf = ? );";
    
    public static final String UPDATE_CONTATO = "UPDATE contato "
            + "SET email = ?, celular = ?, tipo_celular = ?, whatsapp = ?, telefone = ?, tipo_telefone = ? "
            + "WHERE id_pessoa_fk = (SELECT id_pessoa FROM PESSOA WHERE cpf = ? );";
    
    public static final String UPDATE_PESSOA = "UPDATE pessoa SET nome = ? WHERE cpf = ?;";
    
    public void salvarCadastroPessoa(Pessoa ps){   
        try {
            conectar();
            stmt = con.prepareStatement(INSERT_PESSOA);
            stmt.setString(1, ps.getCpf() );
            stmt.setString(2, ps.getNome());
            stmt.setString(3, ps.getDataNascimento());
            stmt.setString(4, ps.getSexo());
            stmt.execute();
            
            stmt = con.prepareStatement(INSERT_CONTATO);
            stmt.setString(1, ps.getContato().getEmail());
            stmt.setString(2, ps.getContato().getCelular());
            stmt.setString(3, ps.getContato().getTipoCelular());
            stmt.setBoolean(4, ps.getContato().isTemWhatsApp());
            stmt.setString(5, ps.getContato().getTelefone());
            stmt.setString(6, ps.getContato().getTipoTelefone());
            stmt.setString(7, ps.getCpf());
            stmt.execute();
            
            stmt = con.prepareStatement(INSERT_ENDERECO);
            stmt.setString(1, ps.getEndereco().getCep());
            stmt.setString(2, ps.getEndereco().getPais());
            stmt.setString(3, ps.getEndereco().getEstado());
            stmt.setString(4, ps.getEndereco().getUf());
            stmt.setString(5, ps.getEndereco().getCidade());
            stmt.setString(6, ps.getEndereco().getBairro());
            stmt.setString(7, ps.getEndereco().getRua());
            stmt.setInt(8, ps.getEndereco().getNumeroCasa());
            stmt.setString(9, ps.getEndereco().getComplemento());
            stmt.setString(10, ps.getCpf());
            stmt.execute();
            
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar cadastro de Pessoa!!!");
            System.out.println(e);
        }finally{
            desconectar(con);
        }       
    }
    
    public Pessoa retornarPessoa(String cpf){
        Endereco end = new Endereco();
        Contato cont = new Contato();
        Pessoa ps = new Pessoa();
        try {
            conectar();
            stmt = con.prepareStatement(SELECT_PESSOA);
            System.out.println(SELECT_PESSOA);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
                      
            if(rs.isBeforeFirst()){
                while(rs.next()){
                end.setCep(rs.getString("cep"));
                end.setPais(rs.getString("pais"));
                end.setEstado(rs.getString("estado"));
                end.setUf(rs.getString("uf"));
                end.setCidade(rs.getString("cidade"));
                end.setBairro(rs.getString("bairro"));
                end.setRua(rs.getString("rua"));
                end.setNumeroCasa(rs.getInt("numero"));
                end.setComplemento(rs.getString("complemento"));
                
                cont.setEmail(rs.getString("email"));
                cont.setCelular(rs.getString("celular"));
                cont.setTipoCelular(rs.getString("tipo_celular"));
                cont.setTemWhatsApp(rs.getBoolean("whatsapp"));
                cont.setTelefone(rs.getString("telefone"));
                cont.setTipoTelefone(rs.getString("tipo_telefone"));
                
                ps.setId(rs.getInt("id_pessoa"));
                ps.setCpf(rs.getString("cpf"));
                ps.setNome(rs.getString("nome"));
                ps.setDataNascimento(rs.getString("data_nascimento"));
                ps.setSexo(rs.getString("sexo"));
                ps.setEndereco(end);
                ps.setContato(cont);
            }
            }else{
                ps = null; 
            }
  
            return ps;           
        } catch (SQLException e) {
            System.out.println("Erro ao Buscar Pessoa!!!");
            System.out.println(e);
            return null;            
        }finally{
            desconectar(con);
        }  
    }
    
    public void atualizarPessoa(Pessoa ps){
            try {
            conectar();
            stmt = con.prepareStatement(UPDATE_PESSOA);
            stmt.setString(1, ps.getNome());
            stmt.setString(2, ps.getCpf());
            stmt.execute();
            
            stmt = con.prepareStatement(UPDATE_CONTATO);
            stmt.setString(1, ps.getContato().getEmail());
            stmt.setString(2, ps.getContato().getCelular());
            stmt.setString(3, ps.getContato().getTipoCelular());
            stmt.setBoolean(4, ps.getContato().isTemWhatsApp());
            stmt.setString(5, ps.getContato().getTelefone());
            stmt.setString(6, ps.getContato().getTipoTelefone());
            stmt.setString(7, ps.getCpf());
            stmt.execute();
            
            stmt = con.prepareStatement(UPDATE_ENDERECO);
            stmt.setString(1, ps.getEndereco().getCep());
            stmt.setString(2, ps.getEndereco().getPais());
            stmt.setString(3, ps.getEndereco().getEstado());
            stmt.setString(4, ps.getEndereco().getUf());
            stmt.setString(5, ps.getEndereco().getCidade());
            stmt.setString(6, ps.getEndereco().getBairro());
            stmt.setString(7, ps.getEndereco().getRua());
            stmt.setInt(8, ps.getEndereco().getNumeroCasa());
            stmt.setString(9, ps.getEndereco().getComplemento());
            stmt.setString(10, ps.getCpf());
            stmt.execute();
            
        } catch (SQLException e) {
            System.out.println("Erro ao Salvar cadastro de Pessoa!!!");
            System.out.println(e);
        }finally{
            desconectar(con);
        } 
    }
}
