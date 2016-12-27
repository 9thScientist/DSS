package data;

import Main.Apartamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class ApartamentoDAO extends ConnectDAO implements Map<Integer,Apartamento> {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public ApartamentoDAO() throws Exception{
    }

    @Override 
    public void clear(){
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento; ");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public boolean cointainsKey(int key) throws NullPointerException {
        boolean r = false;
        
        try{
            String sql = "select id from mydb.apartamento where Id ='"+Integer.toString(key)+"'";
            resultSet = statement.executeQuery(sql);
            r=resultSet.next();
        
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    
    public boolean cointainsValue(Apartamento value){
        Apartamento a = (Apartamento) value;
        return containsKey(a.getKey());
        }
    
    @Override
    public Apartamento get(Object key){
        Apartamento a = null;
        try{
        
            preparedStatement = connect.prepareStatement("select * from mybd.apartamento where id=?");
            preparedStatement.setInt(1, (Integer)key);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                a = new Apartamento(resultSet.getInt("Id"), resultSet.getFloat("Saldo"));
            }
            
        }catch(SQLException e){
        }
        
        return a;
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public Apartamento put(Integer id,Apartamento apartamento){
        Apartamento a = null;
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.apartamento values (?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Saldo=VALUES(Saldo), statement.RETURN_GENERATED_KEYS");
            
            preparedStatement.setInt(1,apartamento.getId());
            preparedStatement.setFloat(2,apartamento.getSaldo());
            preparedStatement.executeUpdate();
            
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                apartamento.setId(newId);
            }
            a = apartamento;
        }catch(SQLException e){
        
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Apartamento> t) {
        for(Apartamento a : t.values()) {
            put(a.getId(), a);
        }
    }

    @Override
    public Apartamento remove(Object key){
        Apartamento a = this.get(key);
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.apartamento where Id = ? ; ");
            preparedStatement.setInt(1,(int)key);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
        return a;
    }
    
    @Override
    public int size(){
        int i=0;
        try{
            
            resultSet = statement.executeQuery("select * form mydb.apartamento");
            
            while(resultSet.next()){
                i++;
            }
       
        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }
        
        return i;
    
    }
    
    @Override
    public Collection<Apartamento> values(){
        Collection<Apartamento> cat = new HashSet<>();
        try{
            resultSet = statement.executeQuery("select * from mydb.apartamento");
            while(resultSet.next()){
                cat.add(new Apartamento(resultSet.getInt("Id"),resultSet.getFloat("Saldo")));
            }
        
        
        } catch (SQLException e) {
        }
        
        return cat;
    }  
}