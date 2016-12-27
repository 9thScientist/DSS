package data;

import Main.Apartamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ApartamentoDAO implements Map<Integer,Apartamento> {

    private Connection con;

    @Override
    public void clear(){
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            stm.executeUpdate("delete from mydb.apartamento");
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
            String sql = "select id from mydb.apartamento where Id ='"+(int)key+"'";
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
        Apartamento a = (Apartamento) value;
        return containsKey(a.getKey());
        }

    @Override
    public Apartamento get(Object key){
        Apartamento a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mybd.apartamento where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            if(rs.next()){
                a = new Apartamento(rs.getInt("Id"), rs.getFloat("Saldo"));
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
    public Apartamento put(Integer id,Apartamento apartamento){
        Apartamento a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.apartamento values (?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Saldo=VALUES(Saldo), statement.RETURN_GENERATED_KEYS");

            pStm.setInt(1,apartamento.getId());
            pStm.setFloat(2,apartamento.getSaldo());
            pStm.executeUpdate();

            ResultSet rs = pStm.getGeneratedKeys();
            if(rs.next()){
                int newId = rs.getInt(1);
                apartamento.setId(newId);
            }
            a = apartamento;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
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
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.apartamento where Id = ? ; ");
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
            ResultSet rs = stm.executeQuery("select * form mydb.apartamento");

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
    public Collection<Apartamento> values(){
        Collection<Apartamento> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.apartamento");
            while(rs.next()){
                cat.add(new Apartamento(rs.getInt("Id"),rs.getFloat("Saldo")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }

        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Apartamento>> entrySet(){
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