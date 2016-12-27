package data;

import Main.Categoria;
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
    public void clear(){
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM categoria");
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(con);
        }
    }

    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;

        try {
            conn = Connect.connect();
            Statement stm = conn.prepareStatement("SELECT Id FROM categoria WHERE Id = ");
			stm.setId(1, (Integer) key);
            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally{
            Connect.close(conn);
        }

        return r;
    }

    @Override
    public boolean containsValue(Object value){
        Categoria c = (Categoria) value;
        return containsKey(c.getId());
	}

    @Override
    public Categoria get(Object key){
        Categoria c = null;

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM categoria WHERE ID = ?");
            stm.setInt(1, (Integer) key);
            ResultSet rs = stm.executeQuery();

            if(rs.next()) {
				int id = rs.getInt("Id");
				Sting nome = rs.getString("Categoria");
				boolean recorrente = rs.getBoolean("Regular");

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
    public Categoria put(Integer id, Categoria cat){
        Categoria c = null;

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO categoria VALUES (?,?,?)\n" +
				"ON DUPLICATE KEY UPDATE Id=VALUES(Id), Categoria=VALUES(Categoria), Regular=VALUES(Regular)", statement.RETURN_GENERATED_KEYS);

            pStm.setInt(1, cat.getId());
            pStm.setString(2, cat.getDescricao());
            pStm.setBoolean(3, cat.isRecorrente());
            pStm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                int newId = rs.getInt(1);
                cat.setId(newId);
            }

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
    public Categoria remove(Object key){
        Categoria c = get(key);

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM categoria WHERE Id = ?");
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
    public int size(){
        int counter = 0;

        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM categoria");

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
    public Collection<Categoria> values(){
        Collection<Categoria> cat = new HashSet<>();

        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM categoria");

            while(rs.next()){
				int id = rs.getInt("Id");
				String nome = rs.getNome("Categoria");
				boolean recorrente = rs.getBoolean("Regular");

                cat.add(new Categoria(id, nome, recorrente);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }

        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Categoria>> entrySet(){
        throw new NullPointerException("Not implemented");
    }

    @Override
    public boolean equals(Object o){
        throw new NullPointerException("Not implemented");
    }

    @Override
    public int hashCode(){
        return conn.hashCode();
    }

    @Override
    public Set<Integer> keySet(){
        throw new NullPointerException("Not implemented");
    }
}
