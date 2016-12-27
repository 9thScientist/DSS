package data;

import Main.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoriaDAO implements Map<Integer,Categoria> {

    private Connection con;

    @Override
    public void clear(){
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            stm.executeUpdate("delete from mydb.categoria");
        }catch (ClassNotFoundException | SQLException e) {
            throw new NullPointerException(e.getMessage()); 
        } finally {
            Connect.close(con);
        }
    }
    
    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;

        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            String sql = "select id from mydb.categoria where Id ='"+(int)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            r=rs.next();

        } catch (ClassNotFoundException | SQLException e) {
            throw new NullPointerException(e.getMessage());
        }finally{
            Connect.close(con);
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value){
        Categoria a = (Categoria) value;
        return containsKey(a.getKey());
        }

    @Override
    public Categoria get(Object key){
        Categoria a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mydb.categoria where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            if(rs.next()){
                a = new Categoria(rs.getInt("Id"), rs.getString("Categoria"),rs.getBoolean("Regular"));
            }

        }catch(ClassNotFoundException | SQLException e){
             e.printStackTrace();
        } finally {
            Connect.close(con);
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
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.categoria values (?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Categoria=VALUES(Categoria), Regular=VALUES(Regular) statement.RETURN_GENERATED_KEYS");

            pStm.setInt(1,categoria.getId());
            pStm.setString(2,categoria.getCategoria());
            pStm.setBoolean(1,categoria.getRegular());
            pStm.executeUpdate();

            ResultSet rs = pStm.getGeneratedKeys();
            if(rs.next()){
                int newId = rs.getInt(1);
                categoria.setId(newId);
            }
            a = categoria;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
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
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.categoria where Id = ? ; ");
            pStm.setInt(1,(int)key);
            pStm.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return a;
    }

    @Override
    public int size(){
        int i=0;
        try{
            con= Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * form mydb.categoria");

            while(rs.next()){
                i++;
            }

        }catch(ClassNotFoundException | SQLException e){
            throw new NullPointerException(e.getMessage());
        }finally {
            Connect.close(con);
        }

        return i;

    }

    @Override
    public Collection<Categoria> values(){
        Collection<Categoria> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.categoria");
            while(rs.next()){
                cat.add(new Categoria(rs.getInt("Id"),rs.getString("Categoria"),rs.getBoolean("Regular")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
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
        return this.con.hashCode();
    }

    @Override
    public Set<Integer> keySet(){
        throw new NullPointerException("Not implemented!");
    }

}