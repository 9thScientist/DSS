package data;

import Moradores.Apartamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

public class ApartamentoDAO {

    private Connection con;

    public void clear() {
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

    public Apartamento get(Object key) {
        Apartamento a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mydb.apartamento where id=?");
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

    public boolean isEmpty() {
        return size() == 0;
    }

    
    public Apartamento put(Integer id,Apartamento apartamento) {
        Apartamento a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.apartamento values (?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Saldo=VALUES(Saldo)", Statement.RETURN_GENERATED_KEYS);

            pStm.setInt(1,apartamento.getId());
            pStm.setFloat(2,apartamento.getSaldo());
            pStm.executeUpdate();

            a = apartamento;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return a;
    }

    public Apartamento remove(Object key) {
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

    public int size() {
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

    public Collection<Apartamento> values() {
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
}