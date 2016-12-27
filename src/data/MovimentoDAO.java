package data;

import Main.Movimento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class MovimentoDAO extends ConnectDAO implements Map<Integer,Movimento> {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public MovimentoDAO() throws Exception{
    }

    @Override 
    public void clear(){
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.movimento; ");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public boolean cointainsKey(int key) throws NullPointerException {
        boolean r = false;
        
        try{
            String sql = "select id from mydb.movimento where Id ='"+Integer.toString(key)+"'";
            resultSet = statement.executeQuery(sql);
            r=resultSet.next();
        
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    
    public boolean cointainsValue(Movimento value){
        Movimento a = (Movimento) value;
        return containsKey(a.getKey());
        }
    
    @Override
    public Movimento get(Object key){
        Movimento a = null;
        try{
        
            preparedStatement = connect.prepareStatement("select * from mybd.movimento where id=?");
            preparedStatement.setInt(1, (Integer)key);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                a = new Movimento(resultSet.getInt("Id"), resultSet.getInt("Apartamento"),resultSet.getInt("Morador"),resultSet.getFloat("Valor"),resultSet.getDate("Data"),resultSet.getBoolean("Transacao"));
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
    public Movimento put(Integer id,Movimento movimento){
        Movimento a = null;
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.movimento values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Apartamento=VALUES(Apartamento), Morador = VALUES(Morador), Valor = VALUES(Valor), Data = VALUES(Data), Transacao = VALUES(Transacao), statement.RETURN_GENERATED_KEYS");
            
            preparedStatement.setInt(1,movimento.getId());
            preparedStatement.setInt(2,movimento.getApartamento());
            preparedStatement.setInt(3,movimento.getMorador());
            preparedStatement.setFloat(4,movimento.getValor());
            preparedStatement.setDate(5,movimento.getData());
            preparedStatement.setBoolean(6,movimento.getTransacao());
            preparedStatement.executeUpdate();
            
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                movimento.setId(newId);
            }
            a = movimento;
        }catch(SQLException e){
        
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Movimento> t) {
        for(Movimento a : t.values()) {
            put(a.getId(), a);
        }
    }

    @Override
    public Movimento remove(Object key){
        Movimento a = this.get(key);
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.movimento where Id = ? ; ");
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
            
            resultSet = statement.executeQuery("select * form mydb.movimento");
            
            while(resultSet.next()){
                i++;
            }
       
        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }
        
        return i;
    
    }
    
    @Override
    public Collection<Movimento> values(){
        Collection<Movimento> mov = new HashSet<>();
        try{
            resultSet = statement.executeQuery("select * from mydb.movimento");
            while(resultSet.next()){
                mov.add(new Movimento(resultSet.getInt("Id"),resultSet.getInt("Apartamento"),resultSet.getInt("Morador"),resultSet.getFloat("Valor"),resultSet.getDate("Data"),resultSet.getBoolean("Transacao")));
            }
        
        
        } catch (SQLException e) {
        }
        
        return mov;
    }  
}