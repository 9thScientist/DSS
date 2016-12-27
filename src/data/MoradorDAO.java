package data;

import Main.Morador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class MoradorDAO extends ConnectDAO implements Map<Integer,Morador> {
    
   
    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;
    
    public MoradorDAO() throws Exception{
    }

    @Override 
    public void clear(){
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.morador; ");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
        
        }
    }
    
    public boolean cointainsKey(int key) throws NullPointerException {
        boolean r = false;
        
        try{
            String sql = "select id from mydb.morador where Id ='"+Integer.toString(key)+"'";
            resultSet = statement.executeQuery(sql);
            r=resultSet.next();
        
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }
    
    public boolean cointainsValue(Morador value){
        Morador a = (Morador) value;
        return containsKey(a.getKey());
        }
    
    @Override
    public Morador get(Object key){
        Morador a = null;
        try{
        
            preparedStatement = connect.prepareStatement("select * from mybd.morador where id=?");
            preparedStatement.setInt(1, (Integer)key);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                a = new Morador(resultSet.getInt("Id"), resultSet.getInt("Apartamento"), resultSet.getString("Nome"), resultSet.getString("Contacto"), resultSet.getFloat("Saldo"), resultSet.getString("Imagem"));
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
    public Morador put(Integer id,Morador morador){
        Morador a = null;
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.Morador values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Apartamento=VALUES(Apartamento), Nome = VALUES(Nome), Contacto = VALUES(Contacto), Saldo = VALUES(Saldo), Imagem = VALUES(Imagem), statement.RETURN_GENERATED_KEYS");
            
            preparedStatement.setInt(1,morador.getId());
            preparedStatement.setInt(2,morador.getApartamento());
            preparedStatement.setString(3,morador.getNome());
            preparedStatement.setString(4,morador.getContacto());
            preparedStatement.setFloat(5,morador.getSaldo());
            preparedStatement.setString(6,morador.getImagem());
            preparedStatement.executeUpdate();
            
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                morador.setId(newId);
            }
            a = morador;
        }catch(SQLException e){
        
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Morador> t) {
        for(Morador a : t.values()) {
            put(a.getId(), a);
        }
    }

    @Override
    public Morador remove(Object key){
        Morador a = this.get(key);
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.morador where Id = ? ; ");
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
            
            resultSet = statement.executeQuery("select * form mydb.morador");
            
            while(resultSet.next()){
                i++;
            }
       
        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }
        
        return i;
    
    }
    
    @Override
    public Collection<Morador> values(){
        Collection<Morador> cat = new HashSet<>();
        try{
            resultSet = statement.executeQuery("select * from mydb.morador");
            while(resultSet.next()){
                cat.add(new Morador(resultSet.getInt("Id"),resultSet.getInt("Apartamento"),resultSet.getString("Nome"),resultSet.getString("Contacto"),resultSet.getFloat("Saldo"),resultSet.getString("Imagem")));
            }
        
        
        } catch (SQLException e) {
        }
        
        return cat;
    }  
}