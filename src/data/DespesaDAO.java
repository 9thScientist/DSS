package data;

import Despesas.Despesa;
import Moradores.Morador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DespesaDAO implements Map<Integer,Despesa> {

    private Connection con;

    @Override
    public void clear() {
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id from mydb.Despesa");
            
            while(rs.next()){
                PreparedStatement pStm = con.prepareStatement("delete from mydb.Despesa where Id = ? ; ");
                pStm.setInt(1,(int)rs.getInt("Id"));
                pStm.executeUpdate();
                
                pStm = con.prepareStatement("delete from mydb.Movimento where Id = ? ; ");
                pStm.setInt(1,(int)rs.getInt("Id"));
                pStm.executeUpdate();
                
                pStm = con.prepareStatement("delete from mydb.Racio where Despesa = ? ; ");
                pStm.setInt(1,(int)rs.getInt("Id"));
                pStm.executeUpdate();
            }
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
            String sql = "select id from mydb.Despesa where Id ='"+(Integer)key+"'";
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
    public Despesa get(Object key) {
        Despesa a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mydb.Despesa where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            
            PreparedStatement pStmM = con.prepareStatement("select * from mydb.Movimento where id=?");
            pStmM.setInt(1, (Integer)key);
            ResultSet rsM = pStmM.executeQuery();
            
            PreparedStatement pStmR = con.prepareStatement("select * from mydb.Racio where Despesa=?");
            pStmR.setInt(1, (Integer)key);
            ResultSet rsR = pStmR.executeQuery();
            
            PreparedStatement pStmC = con.prepareStatement("select * from mydb.Categoria where Id=?");
            pStmR.setInt(1, rs.getInt("Categoria"));
            
            if(rs.next()){
                HashMap<Morador, Float> racios = new HashMap<>();
                MoradorDAO mor = new MoradorDAO();
                ApartamentoDAO apa = new ApartamentoDAO();
                CategoriaDAO cat = new CategoriaDAO();
                while(rsR.next()){
                    racios.put(mor.get(rsR.getInt("Id")),rsR.getFloat("Valor"));
                }
                a = new Despesa(rsM.getInt("Id"), apa.get(rsM.getInt("Apartamento")), mor.get(rsM.getInt("Morador")), rsM.getFloat("Valor"), rsM.getDate("Data"), rsM.getBoolean("Transacao"),rs.getBoolean("Pago"),rs.getString("Descricao"),cat.get(rs.getInt("Categoria")),racios);
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
    public Despesa put(Integer Id,Despesa despesa) {
        Despesa a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.Despesa values (?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Categoria=VALUES(Categoria), Descrição= VALUES(Descrição),Pago= VALUES(Pago)", Statement.RETURN_GENERATED_KEYS);

            pStm.setInt(1,despesa.getId());
            pStm.setInt(2,despesa.getCategoria().getId());
            pStm.setString(3,despesa.getDescricao());
            pStm.setBoolean(4,despesa.pago());
            pStm.executeUpdate();
            
            pStm = con.prepareStatement("insert into mydb.Movimento values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Apartamento=VALUES(Apartamento), Morador=VALUES(Morador), Valor=VALUES(Valor), Data=VALUES(Data), Transacao=VALUES(Transacao)", Statement.RETURN_GENERATED_KEYS);

            pStm.setInt(1,despesa.getId());
            pStm.setInt(2,despesa.getApartamento().getId());
            pStm.setInt(3,despesa.getMorador().getId());
            pStm.setFloat(4,despesa.getValor());
            pStm.setDate(5,despesa.getData());
            pStm.setBoolean(6,despesa.isTransacao());
            pStm.executeUpdate();
            for(Map.Entry<Morador,Float> r : despesa.getRacios().entrySet()) {
                PreparedStatement pStmR = con.prepareStatement("insert into mydb.Racio values (?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE Morador=VALUES(Morador), Despesa=VALUES(Despesa), Racio=VALUES(Racio)", Statement.RETURN_GENERATED_KEYS);

                pStmR.setInt(1,r.getKey().getId());
                pStmR.setInt(2,despesa.getId());
                pStmR.setFloat(3,r.getValue());
                pStmR.executeUpdate();
            }
            a = despesa;
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return a;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Despesa> t) {
        for(Despesa a : t.values()) {
            put(a.getId(), a);
        }
    }

    @Override
    public Despesa remove(Object key) {
        Despesa a = this.get(key);
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.Despesa where Id = ? ; ");
            pStm.setInt(1,(Integer)key);
            pStm.executeUpdate();
            pStm = con.prepareStatement("delete from mydb.Movimento where Id = ? ; ");
            pStm.setInt(1,(Integer)key);
            pStm.executeUpdate();
            pStm = con.prepareStatement("delete from mydb.Racio where Despesa = ? ; ");
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
            ResultSet rs = stm.executeQuery("select * from mydb.Despesa");

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
    public Collection<Despesa> values() {
        Collection<Despesa> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.Despesa");
            while(rs.next()){
                PreparedStatement pStmM = con.prepareStatement("select * from mydb.Movimento where id=?");
                pStmM.setInt(1,rs.getInt("Id")); 
                ResultSet rsM = pStmM.executeQuery();
            
                PreparedStatement pStmR = con.prepareStatement("select * from mydb.Racio where Despesa=?");
                pStmR.setInt(1,rs.getInt("Id"));
                ResultSet rsR = pStmR.executeQuery();

                PreparedStatement pStmC = con.prepareStatement("select * from mydb.Categoria where Id=?");
                pStmR.setInt(1, rs.getInt("Categoria"));
                ResultSet rsC = pStmC.executeQuery();

                HashMap<Morador, Float> racios = new HashMap<>();
                
                MoradorDAO mor = new MoradorDAO();
                ApartamentoDAO apa = new ApartamentoDAO();
                CategoriaDAO cate = new CategoriaDAO();
                while(rsR.next()){
                    racios.put(mor.get(rsM.getInt("Morador")),rsR.getFloat("Valor"));
                }
                cat.add(new Despesa(rsM.getInt("Id"), apa.get(rsM.getInt("Apartamento")), mor.get(rsM.getInt("Morador")), rsM.getFloat("Valor"), rsM.getDate("Data"), rsM.getBoolean("Transacao"),rs.getBoolean("Pago"),rs.getString("Descrição"),cate.get(rs.getInt("Categoria")),racios));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Despesa>> entrySet() {
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