package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;



public class TransacoesDAO extends ConnectDAO {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public TransacoesDAO() throws Exception{
    }
    
    
    public void readDB() throws SQLException{
        try{
            resultSet= statement.executeQuery("select * from mydb.transações");

            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                int morador = resultSet.getInt ("Morador");
                int apartamento = resultSet.getInt ("Apartamento");
                float valor = resultSet.getFloat ("Valor");
                Date data = resultSet.getDate ("Data"); 
                
                
                System.out.println("Id: " +id);
                System.out.println("Morador: " + morador);
                System.out.println("Apartamento: " + apartamento);
                System.out.println("Valor: " + valor);
                System.out.println("Data: " + data);
            }
        }catch( SQLException e){
        
        }
    
    
    }
    
    
    public void writeDB(int id, int morador, int apartamento, float valor, Date data) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.transações values (?,?,?,?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,morador);
            preparedStatement.setInt(3,apartamento);
            preparedStatement.setFloat(4,valor);
            preparedStatement.setDate(5, (java.sql.Date) data);
            preparedStatement.executeUpdate();
        
        }catch(SQLException e){
        
        }
    }
    
    public void deleteById(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.transações where Id = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByMorador(int res) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.transações where Morador = ? ; ");
            preparedStatement.setInt(1,res);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public void deleteByData(Date data) throws SQLException{
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.transações where Data = ? ; ");
            preparedStatement.setDate(1, (java.sql.Date) data);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
        
}
