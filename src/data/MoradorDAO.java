package data;

import Main.Morador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoradorDAO implements Map<Integer,Morador> {

    private Connection con;

    @Override
    public void clear(){
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            stm.executeUpdate("delete from mydb.morador");
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
            String sql = "select id from mydb.morador where Id ='"+(int)key+"'";
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
        Morador a = (Morador) value;
        return containsKey(a.getKey());
        }

    @Override
    public Morador get(Object key){
        Morador a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mybd.morador where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            if(rs.next()){
                a = new Morador(rs.getInt("Id"),rs.getInt("Apartamento"), rs.getString("Nome"), rs.getString("Contacto"), rs.getFloat("Saldo"), rs.getString("Imagem"));
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
    public Morador put(Integer id,Morador morador){
        Morador a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.morador values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Apartamento=VALUES(Apartamento), Nome=VALUES(Nome), Contacto=VALUES(Contacto), Saldo=VALUES(Saldo), Imagem=VALUES(Imagem),   statement.RETURN_GENERATED_KEYS");

            pStm.setInt(1,morador.getId());
            pStm.setInt(2,morador.getApartamento());
            pStm.setString(3,morador.getNome());
            pStm.setString(4,morador.getContacto());
            pStm.setFloat(5,morador.getSaldo());
            pStm.setString(6,morador.getImagem());
            pStm.executeUpdate();

            ResultSet rs = pStm.getGeneratedKeys();
            if(rs.next()){
                int newId = rs.getInt(1);
                morador.setId(newId);
            }
            a = morador;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
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
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.morador where Id = ? ; ");
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
            ResultSet rs = stm.executeQuery("select * form mydb.morador");

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
    public Collection<Morador> values(){
        Collection<Morador> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.morador");
            while(rs.next()){
                cat.add(new Morador(rs.getInt("Id"),rs.getInt("Apartamento"),rs.getString("Nome"),rs.getString("Contacto"),rs.getFloat("Saldo"),rs.getString("Imagem")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }

        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Morador>> entrySet(){
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