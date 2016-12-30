package data;

import Despesas.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoriaDAO implements Map<Integer,Categoria> {

    private Connection conn;

    @Override
    public void clear() {
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM Categoria");
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            String sql = "select id from mydb.Categoria where Id ='"+(int)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            Connect.close(conn);
        }
        return r;
    }

    @Override
    public boolean containsValue(Object value) {
        Categoria c = (Categoria) value;
        return containsKey(c.getId());
    }

    @Override
    public Categoria get(Object key) {
        Categoria c = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM Categoria WHERE ID = ?");
            stm.setInt(1, (Integer) key);
            ResultSet rs = stm.executeQuery();

            if(rs.next()) {
                int id = rs.getInt("Id");
                String nome = rs.getString("Categoria");
                boolean recorrente = rs.getBoolean("Recorrente");
                c = new Categoria(id, nome, recorrente);
            }
	} catch (Exception e) {
             e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return c;
    }
    
    
        
    public Categoria getByNome(String key) {
        Categoria c = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM mydb.Categoria WHERE Categoria = ?");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                int id = rs.getInt("Id");
                String nome = rs.getString("Categoria");
                boolean recorrente = rs.getBoolean("Recorrente");
                c = new Categoria(id, nome, recorrente);
            
            }
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return c;
    }
    
    
    
    

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Categoria put(Integer id, Categoria cat) {
        Categoria c = null;

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO Categoria VALUES (?,?,?)\n" +
				"ON DUPLICATE KEY UPDATE Id=VALUES(Id), Categoria=VALUES(Categoria), Recorrente=VALUES(Recorrente)", Statement.RETURN_GENERATED_KEYS);

            stm.setInt(1, cat.getId());
            stm.setString(2, cat.getDescricao());
            stm.setBoolean(3, cat.isRecorrente());
            stm.executeUpdate();
            ResultSet rs = stm.getGeneratedKeys();
            c = cat;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return c;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Categoria> collection) {
        for(Categoria c : collection.values())
            put(c.getId(), c);
    }

    @Override
    public Categoria remove(Object key) {
        Categoria c = get(key);
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM Categoria WHERE Id = ?");
            stm.setInt(1, (int) key);
            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return c;
    }

    @Override
    public int size() {
        int counter = 0;
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Categoria");
            while(rs.next())
                counter += 1;
        } catch (Exception e){
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
        return counter;
    }

    @Override
    public Collection<Categoria> values() {
        Collection<Categoria> cat = new HashSet<>();
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Categoria");
        while(rs.next()){
            int id = rs.getInt("Id");
            String nome = rs.getString("Categoria");
            boolean recorrente = rs.getBoolean("Recorrente");
            cat.add(new Categoria(id, nome, recorrente));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Categoria>> entrySet() {
        throw new NullPointerException("Not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new NullPointerException("Not implemented");
    }

    @Override
    public int hashCode() {
        return conn.hashCode();
    }

    @Override
    public Set<Integer> keySet() {
        throw new NullPointerException("Not implemented");
    }
}
