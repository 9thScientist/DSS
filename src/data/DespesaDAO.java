package data;

import Main.Despesa;
import Main.Racio;
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
    public void clear(){
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select id from mydb.despesa");
            
            while(rs.next()){
                
                PreparedStatement pStm = con.prepareStatement("delete from mydb.despesa where Id = ? ; ");
                pStm.setInt(1,(int)rs.getInt("Id"));
                pStm.executeUpdate();
                
                pStm = con.prepareStatement("delete from mydb.movimento where Id = ? ; ");
                pStm.setInt(1,(int)rs.getInt("Id"));
                pStm.executeUpdate();
                
                pStm = con.prepareStatement("delete from mydb.racio where Despesa = ? ; ");
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
            String sql = "select id from mydb.despesa where Id ='"+(int)key+"'";
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
        Despesa a = (Despesa) value;
        return containsKey(a.getKey());
        }

    @Override
    public Despesa get(Object key){
        Despesa a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("select * from mydb.despesa where id=?");
            pStm.setInt(1, (Integer)key);
            ResultSet rs = pStm.executeQuery();
            
            PreparedStatement pStmM = con.prepareStatement("select * from mydb.movimento where id=?");
            pStmM.setInt(1, (Integer)key);
            ResultSet rsM = pStmM.executeQuery();
            
            PreparedStatement pStmR = con.prepareStatement("select * from mydb.racio where Despesa=?");
            pStmR.setInt(1, (Integer)key);
            ResultSet rsR = pStmR.executeQuery();
            
            PreparedStatement pStmC = con.prepareStatement("select * from mydb.categoria where Id=?");
            pStmR.setInt(1, rs.getInt("Categoria"));
            ResultSet rsC = pStmC.executeQuery();
            
            if(rs.next()){
                HashMap<Integer, Racio> racios = new HashMap<>();
                int i =1;
                MoradorDAO mor = new MoradorDAO();
                ApartamentoDAO apa = new ApartamentoDAO();
                CategoriaDAO cat = new CategoriaDAO();
                while(rsR.next()){
                    racios.put(i,new Racio(mor.get(rsR.getInt("Id")),rsR.getFloat("Valor")));
                    i++;
                }
                a = new Despesa(rsM.getInt("Id"), apa.get(rsM.getInt("Apartamento")), mor.get(rsM.getInt("Morador")), rsM.getFloat("Valor"), rsM.getDate("Data"), rsM.getBoolean("Transacao"),cat.get(rs.getInt("Categoria")),racios);
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
    public Despesa put(Integer id,Despesa despesa){
        Despesa a = null;
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("insert into mydb.despesa values (?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Categoria=VALUES(Categoria), Descrição= VALUES(Descrição) statement.RETURN_GENERATED_KEYS");

            pStm.setInt(1,despesa.getId());
            pStm.setInt(2,despesa.getCategoria());
            pStm.setString(3,despesa.getDescricao());
            pStm.executeUpdate();
            
            pStm = con.prepareStatement("insert into mydb.movimento values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id), Apartamento=VALUES(Apartamento), Morador=VALUES(Morador), Valor=VALUES(Valor), Data=VALUES(Data), Transacao=VALUES(Transacao), statement.RETURN_GENERATED_KEYS");

            pStm.setInt(1,despesa.getId());
            pStm.setInt(2,despesa.getApartamento().getId());
            pStm.setInt(3,despesa.getMorador().getId());
            pStm.setFloat(4,despesa.getValor());
            pStm.setDate(5,despesa.getData());
            pStm.setBoolean(6,despesa.getTransacao());
            pStm.executeUpdate();
            
            for(Racio r : despesa.getRacios().values()) {
                
                pStm = con.prepareStatement("insert into mydb.racio values (?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE Morador=VALUES(Morador), Despesa=VALUES(Despesa), Racio=VALUES(Racio), statement.RETURN_GENERATED_KEYS");

                pStm.setInt(1,r.getMorador().getId());
                pStm.setInt(2,r.getDespesa());
                pStm.setFloat(3,r.getRacio());
                pStm.executeUpdate();
            }
            ResultSet rs = pStm.getGeneratedKeys();
            if(rs.next()){
                int newId = rs.getInt(1);
                despesa.setId(newId);
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
    public Despesa remove(Object key){
        Despesa a = this.get(key);
        try{
            con = Connect.connect();
            PreparedStatement pStm = con.prepareStatement("delete from mydb.despesa where Id = ? ; ");
            pStm.setInt(1,(int)key);
            pStm.executeUpdate();
            pStm = con.prepareStatement("delete from mydb.movimento where Id = ? ; ");
            pStm.setInt(1,(int)key);
            pStm.executeUpdate();
            pStm = con.prepareStatement("delete from mydb.racio where Despesa = ? ; ");
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
            ResultSet rs = stm.executeQuery("select * form mydb.despesa");

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
    public Collection<Despesa> values(){
        Collection<Despesa> cat = new HashSet<>();
        try{
            con = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select * from mydb.despesa");
            while(rs.next()){
                
                
                PreparedStatement pStmM = con.prepareStatement("select * from mydb.movimento where id=?");
                pStmM.setInt(1,rs.getInt("Id")); 
                ResultSet rsM = pStmM.executeQuery();
            
                PreparedStatement pStmR = con.prepareStatement("select * from mydb.racio where Despesa=?");
                pStmR.setInt(1,rs.getInt("Id"));
                ResultSet rsR = pStmR.executeQuery();

                PreparedStatement pStmC = con.prepareStatement("select * from mydb.categoria where Id=?");
                pStmR.setInt(1, rs.getInt("Categoria"));
                ResultSet rsC = pStmC.executeQuery();

                HashMap<Integer, Racio> racios = new HashMap<>();
                int i =1;
                MoradorDAO mor = new MoradorDAO();
                ApartamentoDAO apa = new ApartamentoDAO();
                CategoriaDAO cate = new CategoriaDAO();
                while(rsR.next()){
                    racios.put(i,new Racio(mor.get(rsR.getInt("Id")),rsR.getFloat("Valor")));
                    i++;
                
                }
                cat.add(new Despesa(rsM.getInt("Id"), apa.get(rsM.getInt("Apartamento")), mor.get(rsM.getInt("Morador")), rsM.getFloat("Valor"), rsM.getDate("Data"), rsM.getBoolean("Transacao"),cate.get(rs.getInt("Categoria")),racios));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Connect.close(con);
        }
        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Despesa>> entrySet(){
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