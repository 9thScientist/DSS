package data;

import Main.Morador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoradorDAO implements Map<Integer,Morador> {

    private Connection conn;

    @Override
    public void clear(){
        try {
            conn = Connect.connect();
            Statement stm = con.createStatement();
            stm.executeUpdate("DELETE FROM morador");
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
            Statement stm = con.prepareStatement("SELECT Id from morador WHERE Id = ?");
			stm.setInt(1, (Integer) key);
            ResultSet rs = stm.executeQuery();
            r = rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }

        return r;
    }

    @Override
    public boolean containsValue(Object value){
        Morador m = (Morador) value;
        return containsKey(m.getId());
	}

    @Override
    public Morador get(Object key){
        Morador m = null;

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM morador WHERE Id = ?");
            stm.setInt(1, (Integer) key);
            ResultSet rs = stm.executeQuery();

            if (rs.next()){
                ApartamentoDAO apDAO = new ApartamentoDAO();

				int id = rs.getInt("Id");
				Apartamento ap = apDAO.get(rs.getInt("Apartamento"));
				String nome = rs.getString("Nome");
				String contacto = rs.getString("Contacto");
				String imagem = rs.getString("Imagem");
				float saldo = rs.getSaldo("Saldo");

                a = new Morador(id, ap, nome, contacto, saldo, imagem);
            }
        } catch (Exception e) {
             e.printStackTrace();
        } finally {
            Connect.close(conn);
        }

        return m;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Morador put(Integer id,Morador morador){
        Morador m = null;

        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement(
				"INSERT INTO morador VALUES (?,?,?,?,?,?)\n" +
				"ON DUPLICATE KEY UPDATE Id=VALUES(Id), Apartamento=VALUES(Apartamento), Nome=VALUES(Nome), Contacto=VALUES(Contacto), Saldo=VALUES(Saldo), Imagem=VALUES(Imagem)", statement.RETURN_GENERATED_KEYS);

            pStm.setInt(1, morador.getId());
            pStm.setInt(2, morador.getApartamento().getId());
            pStm.setString(3, morador.getNome());
            pStm.setString(4, morador.getContacto());
            pStm.setFloat(5, morador.getSaldo());
            pStm.setString(6, morador.getImagem());
            pStm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();

            if(rs.next()){
                int newId = rs.getInt(1);
                morador.setId(newId);
            }

            m = morador;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Connect.close(con);
        }

        return n;
    }

    @Override
    public void putAll(Map<? extends Integer,? extends Morador> collection) {
        for(Morador m : collection.values())
            put(m.getId(), m);
    }

    @Override
    public Morador remove(Object key){
        Morador m = get(key);

        try{
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM morador WHERE Id = ?");
            stm.setInt(1, (Integer) key);
            stm.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }

        return m;
    }

    @Override
    public int size(){
        int counter = 0;

        try {
            conn = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM morador");

            while(rs.next())
                counter += 1;
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }

        return counter;
    }

    @Override
    public Collection<Morador> values(){
        Collection<Morador> cat = new HashSet<>();

        try{
            conn = Connect.connect();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM morador");

            while(rs.next()){
                ApartamentoDAO apDAO = new ApartamentoDAO();

				int id = rs.getInt("Id");
				Apartamento ap = apDAO.get(rs.getInt("Apartamento"));
				String nome = rs.getString("Nome");
				String contacto = rs.getString("Contacto");
				String imagem = rs.getString("Imagem");
				float saldo = rs.getFloat("Saldo");

                cat.add(new Morador(id, ap, nome, contacto, saldo, imagem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }

        return cat;
    }

    @Override
     public Set<Map.Entry<Integer,Morador>> entrySet(){
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
e
    @Override
    public Set<Integer> keySet(){
        throw new NullPointerException("Not implemented");
    }

}
