package PdvGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conecta{
    Connection con;
    public boolean conecta(String local, String banco, String usuario,String senha){
        boolean retorno = false;
        try
        {
            
            con=DriverManager.getConnection("jdbc:postgresql://"+local+"/"+banco+"?useTimezone=true&serverTimezone=UTC",usuario,senha);
            retorno=true;
        }catch(SQLException e){
        System.err.println("Erro de conexão:\n"+e);
    }
        return retorno;
    }
    public boolean insere(String tabela, String nome, String idade){
        boolean retorno = false;
        try{
            PreparedStatement stmt = (PreparedStatement)this.con.prepareStatement("insert into "+tabela+" (nome,idade) values ('"+nome+"',"+idade+");");
            stmt.execute();
            stmt.close();
            retorno=true;
        }catch (SQLException ex){
            retorno = false;
            System.err.println("Erro INSERT:"+ ex);
        }
        return retorno;
    }
    public boolean deleta(String tabela, String nome, String idade){
        boolean retorno = false;
        try{
            PreparedStatement stmt = (PreparedStatement)this.con.prepareStatement("delete from "+tabela+" where nome = '"+nome+"' and idade = "+idade+";");
            stmt.execute();
            stmt.close();
            retorno=true;
        }catch (SQLException ex){
            retorno = false;
            System.err.println("Erro DELETE:"+ ex);
        }
        return retorno;
    }
    public ResultSet consulta(String consulta){
        ResultSet rs = null;
        try{
            PreparedStatement stmt = (PreparedStatement)this.con.prepareStatement(consulta);
            rs = stmt.executeQuery();
        }catch (SQLException ex){
            System.err.println("Erro SELECT:"+ ex);
        }
        return rs;
    
    }
    public boolean altera(String tabela, String id, String nome, String idade){
        boolean retorno = false;
        try{
            PreparedStatement stmt = (PreparedStatement)this.con.prepareStatement("update "+tabela+" set nome = '"+nome+"', idade = "+idade+" where id = "+id+";");
            stmt.execute();
            stmt.close();
            retorno=true;
        }catch (SQLException ex){
            retorno = false;
            System.err.println("Erro UPDATE:"+ ex);
        }
        return retorno;
    }
}
