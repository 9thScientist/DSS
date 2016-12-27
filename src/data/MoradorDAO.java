package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MoradorDAO extends ConnectDAO {
    
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public MoradorDAO() throws Exception{
        statement = connect.createStatement();
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.morador");

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String nome = resultSet.getString ("Nome");
                String contacto = resultSet.getString ("Contacto");
                float saldo = resultSet.getFloat ("Saldo");
                String imagem = resultSet.getString ("Imagem");
                
                
                System.out.println("Id: " +id);
                System.out.println("Nome: " + nome);
                System.out.println("Contacto: " + contacto);
                System.out.println("Saldo: " + saldo);
                System.out.println("Imagem: " + imagem);
                
            
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int id,String nome, String contacto, float saldo, String imagem) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.morador values (?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,nome);
            preparedStatement.setString(3,contacto);
            preparedStatement.setFloat(4,saldo);
            preparedStatement.setString(5,imagem);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteById(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.morador where Id = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByNome(String nome) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento where Nome = ? ; ");
            preparedStatement.setString(1,nome);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByContacto(String contacto) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento where Nome = ? ; ");
            preparedStatement.setString(1,contacto);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
        
}
