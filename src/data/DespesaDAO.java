package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class DespesaDAO extends ConnectDAO {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public DespesaDAO() throws Exception{
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.despesa");

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                int categoria = resultSet.getInt ("Categoria");
                int apartamento = resultSet.getInt ("Apartamento");
                float valor = resultSet.getFloat ("Valor");
                Date data = resultSet.getDate ("Data");
                String descricao = resultSet.getString("Descrição");
                
                
                System.out.println("Id: " +id);
                System.out.println("Categoria: " + categoria);
                System.out.println("Apartamento: " + apartamento);
                System.out.println("Valor: " + valor);
                System.out.println("Data: " + data);
                System.out.println("Descricao: "+ descricao);
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int id, int categoria, int apartamento, float valor, Date data, String descricao) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.despesa values (?,?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,categoria);
            preparedStatement.setInt(3,apartamento);
            preparedStatement.setFloat(4,valor);
            preparedStatement.setDate(5, (java.sql.Date) data);
            preparedStatement.setString(6, descricao);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteById(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.despesa where Id = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByCategoria(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.despesa where Categoria = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByData(Date data) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.despesa where Data = ? ; ");
            preparedStatement.setDate(1, (java.sql.Date) data);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
        
}
