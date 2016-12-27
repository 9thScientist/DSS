package data;

import Main.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoriaDAO extends ConnectDAO implements Map<Integer,Categoria> {


    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;

    public CategoriaDAO() throws Exception{
    }

    @Override
    public void clear(){
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.categoria; ");
            preparedStatement.executeUpdate();
        }catch (SQLException e){

        }
    }

    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;

        try{
            String sql = "select id from mydb.categoria where Id ='"+(int)key+"'";
            resultSet = statement.executeQuery(sql);
            r=resultSet.next();

        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean containsValue(Object value){
        Categoria a = (Categoria) value;
        return containsKey(a.getKey());
        }

    @Override
    public Categoria get(Object key){
        Categoria a = null;
        try{

            preparedStatement = connect.prepareStatement("select * from mybd.categoria where id=?");
            preparedStatement.setInt(1, (Integer)key);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                a = new Categoria(resultSet.getInt("Id"), resultSet.getString("Categoria"), resultSet.getBoolean("Regular"));
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
    public Categoria put(Integer id,Categoria categoria){
        Categoria a = null;
        try{
            preparedStatement = connect.prepareStatement("insert into mydb.categoria values (?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Categoria=VALUES(Categoria), Regular = VALUES(Regular), statement.RETURN_GENERATED_KEYS");

            preparedStatement.setInt(1,categoria.getId());
            preparedStatement.setString(2,categoria.getCategoria());
            preparedStatement.setBoolean(3,categoria.getRegular());
            preparedStatement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                categoria.setId(newId);
            }
            a = categoria;
        }catch(SQLException e){

        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Categoria> t) {
        for(Categoria a : t.values()) {
            put(a.getId(), a);
        }
    }

    @Override
    public Categoria remove(Object key){
        Categoria a = this.get(key);
        try{
            preparedStatement = connect.prepareStatement("delete from mydb.categoria where Id = ? ; ");
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

            resultSet = statement.executeQuery("select * form mydb.categoria");

            while(resultSet.next()){
                i++;
            }

        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }

        return i;

    }

    @Override
    public Collection<Categoria> values(){
        Collection<Categoria> cat = new HashSet<>();
        try{
            resultSet = statement.executeQuery("select * from mydb.Categoria");
            while(resultSet.next()){
                cat.add(new Categoria(resultSet.getInt("Id"),resultSet.getString("Categoria"),resultSet.getBoolean("Regular")));
            }


        } catch (SQLException e) {
        }

        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Categoria>> entrySet(){
        throw new NullPointerException("public Set<Map.Entry<Object,Object>> entrySet() not implemented!");
    }

    @Override
    public boolean equals(Object o){
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    @Override
    public int hashCode(){
        return this.connect.hashCode();
    }

    @Override
    public Set<Integer> keySet(){
        throw new NullPointerException("Not implemented!");
    }

}