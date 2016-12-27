package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class CategoriaDAO extends ConnectDAO {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public CategoriaDAO() throws Exception{
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.categoria");

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String categoria = resultSet.getString ("Categoria");
                boolean regular = resultSet.getBoolean ("Regular");
                
                
                System.out.println("Morador: " + id);
                System.out.println("Despesa: " + categoria);
                System.out.println("Racio: " + regular);
                
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int id, String categoria, boolean regular) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.categoria values (?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,categoria);
            preparedStatement.setBoolean(3,regular);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteById(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.categoria where Id = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByCategoria(String res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.categorias where Categoria = ? ; ");
            preparedStatement.setString(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByRegular(boolean res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.categorias where Regular = ? ; ");
            preparedStatement.setBoolean(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
        
}
