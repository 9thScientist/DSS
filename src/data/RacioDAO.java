package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class RacioDAO extends ConnectDAO {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public RacioDAO() throws Exception{
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.racio");

            while(resultSet.next()){
                int morador = resultSet.getInt("Morador");
                int despesa = resultSet.getInt ("Despesa");
                float racio = resultSet.getFloat ("Racio");
                
                
                System.out.println("Morador: " + morador);
                System.out.println("Despesa: " + despesa);
                System.out.println("Racio: " + racio);
                
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int morador, int despesa, float racio) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.racio values (?,?,?)");
            preparedStatement.setInt(1,morador);
            preparedStatement.setInt(2,despesa);
            preparedStatement.setFloat(3,racio);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteByMorador(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.racio where Morador = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByDespesa(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.transações where Despesa = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
        
}
