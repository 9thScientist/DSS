package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ApartamentoDAO extends ConnectDAO {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public ApartamentoDAO() throws Exception{
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.apartamento");

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                float saldo = resultSet.getFloat ("Saldo");

                System.out.println("Id: " +id);
                System.out.println("Saldo: " + saldo);
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int id, float saldo) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.apartamento values (?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setFloat(2,saldo);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteById(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento where Id = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteBySaldo(float res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento where Id = ? ; ");
            preparedStatement.setFloat(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
        
}
