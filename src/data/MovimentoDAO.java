package data;

import Despesas.Despesa;
import Despesas.Movimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovimentoDAO implements Map<Integer,Movimento> {

    private Connection con;

    @Override
    public void clear() {
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            stm.executeUpdate("delete from mydb.movimento");
            stm.executeUpdate("delete from mydb.despesa");
            stm.executeUpdate("delete from mydb.racio");
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
            String sql = "select id from mydb.movimento where Id ='"+(Integer)key+"'";
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
    public boolean containsValue(Object value) {
        throw new NullPointerException("public boolean containsValue(Object o) not implemented!");
    }

    @Override
    public Movimento get(Object key) {
        Movimento a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mydb.movimento where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            if(rs.next()){
                
                ApartamentoDAO apa = new ApartamentoDAO();
                MoradorDAO  mor = new MoradorDAO();
                if(rs.getBoolean("Transacao")){
                    a = new Movimento(rs.getInt("Id"), apa.get(rs.getInt("Apartamento")),mor.get(rs.getInt("Morador")), rs.getFloat("Valor"), rs.getDate("Data"), rs.getBoolean("Transacao"));
                }
                else{
                    DespesaDAO des = new DespesaDAO();
                    a = des.get(rs.getInt("Id"));   
                }
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
    public Movimento put(Integer key, Movimento movimento) {
        Movimento a = null;
        try{
            if(!movimento.isTransacao()){
                con = Connect.connect();
                PreparedStatement pStm = con.prepareStatement("insert into mydb.movimento values (?,?,?,?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Apartamento=VALUES(Apartamento), Morador=VALUES(Morador), Valor=VALUES(Valor), Data=VALUES(Data), Transacao=VALUES(Transacao), Apartamento=VALUES(Apartamento)", Statement.RETURN_GENERATED_KEYS);
                pStm.setInt(1,movimento.getId());
                pStm.setInt(2,movimento.getApartamento().getId());
                pStm.setInt(3,movimento.getMorador().getId());
                pStm.setFloat(4,movimento.getValor());
                pStm.setDate(5,movimento.getData());
                pStm.setBoolean(4,movimento.isTransacao());
                pStm.executeUpdate();
                a = movimento;
            }
            else{
               DespesaDAO des = new DespesaDAO();
               des.put(movimento.getId(),(Despesa)movimento);
            }
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
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
    public Movimento remove(Object key) {
        Movimento a = this.get(key);
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.movimento where Id = ? ; ");
            pStm.setInt(1,(Integer)key);
            pStm.executeUpdate();
            
            pStm = con.prepareStatement("delete from mydb.despesa where Id = ? ; ");
            pStm.setInt(1,(Integer)key);
            pStm.executeUpdate();
            
            pStm = con.prepareStatement("delete from mydb.racio where Despesa = ? ; ");
            pStm.setInt(1,(Integer)key);
            pStm.executeUpdate();
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return a;
    }

    @Override
    public int size() {
        int i=0;
        try{
            con= Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * form mydb.Movimento");
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
    public Collection<Movimento> values() {
        Collection<Movimento> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.Movimento");
            
            while(rs.next()){
                if(rs.getBoolean("Transacao")){
                    ApartamentoDAO apa = new ApartamentoDAO();
                    MoradorDAO mor = new MoradorDAO();
                    cat.add(new Movimento(rs.getInt("Id"), apa.get(rs.getInt("Apartamento")), mor.get(rs.getInt("Morador")), rs.getFloat("Valor"), rs.getDate("Data"), rs.getBoolean("Transacao")));
                }
                else{
                    DespesaDAO des = new DespesaDAO();
                    cat.add((Despesa) des.get(rs.getInt("Id")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
    return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Movimento>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<Object,Object>> entrySet() not implemented!");
    }

    @Override
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }

    @Override
    public int hashCode() {
        return this.con.hashCode();
    }

    @Override
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented!");
    }
}
