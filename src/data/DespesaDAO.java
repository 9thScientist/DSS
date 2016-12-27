
package data;

import Main.Despesa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DespesaDAO extends ConnectDAO implements Map<Integer,Despesa> {


    private PreparedStatement preparedStatement= null;
    private ResultSet resultSet = null;

    public DespesaDAO() throws Exception{
    }

    @Override
    public void clear(){
        try{

            resultSet = statement.executeQuery("select * from mybd.despesa ");

            while(resultSet.next()){

               int id = resultSet.getInt("Id");
               preparedStatement = connect.prepareStatement("delete from mydb.despesa where Id = ? ");
               preparedStatement.setInt(1, id);
               preparedStatement.executeUpdate();

               preparedStatement = connect.prepareStatement("delete from mydb.movimento where Id = ? ");
               preparedStatement.setInt(1, id);
               preparedStatement.executeUpdate();

               preparedStatement = connect.prepareStatement("delete from mydb.racio where Despesa = ? ");
               preparedStatement.setInt(1, id);
               preparedStatement.executeUpdate();

            }



        }catch (SQLException e){

        }
    }

    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;

        try{
            String sql = "select id from mydb.despesa where Id ='"+(int) key+"'";
            resultSet = statement.executeQuery(sql);
            r=resultSet.next();

        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
        return r;
    }

    public boolean containsValue(Object value){
        Despesa a = (Despesa) value;
        return containsKey(a.getKey());
        }

    @Override
    public Despesa get(Object key){
        Despesa a = null;
        try{
            ResultSet racio;
            ResultSet movimento;


            preparedStatement = connect.prepareStatement("select * from mybd.despesa where id=?");
            preparedStatement.setInt(1, (Integer)key);
            resultSet = preparedStatement.executeQuery();

            int id = resultSet.getInt("Id");
            preparedStatement = connect.prepareStatement("select * from mybd.movimento where id=?");
            preparedStatement.setInt(1, id);
            movimento = preparedStatement.executeQuery();

            preparedStatement = connect.prepareStatement("select * from mybd.Despesa where Despesa=?");
            preparedStatement.setInt(1, id);
            racio = preparedStatement.executeQuery();

            TreeMap<Integer,Float> racios = new TreeMap();

            if(resultSet.next()){

                while(racio.next()){
                    int i=0;
                    racios.put(i, racio.getFloat("Racio"));
                    i++;
                }


                a = new Despesa(movimento.getInt("Id"), movimento.getInt("Apartamento"),movimento.getInt("Morador"),movimento.getFloat("Valor"),movimento.getDate("Data"),movimento.getBoolean("Transacao"),resultSet.getInt("Categoria"),resultSet.getString("Descrição"),racios);
            }

        }catch(SQLException e){
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

            preparedStatement = connect.prepareStatement("insert into mydb.movimento values (?,?,?,?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Apartamento=VALUES(Apartamento), Morador = VALUES(Morador), Valor = VALUES(Valor), Data = VALUES(Data), Transacao = VALUES(Transacao), statement.RETURN_GENERATED_KEYS");

            preparedStatement.setInt(1,despesa.getId());
            preparedStatement.setInt(2,despesa.getApartamento());
            preparedStatement.setInt(3,despesa.getMorador());
            preparedStatement.setFloat(4,despesa.getValor());
            preparedStatement.setDate(5,despesa.getData());
            preparedStatement.setBoolean(6,despesa.getTransacao());
            preparedStatement.executeUpdate();



            preparedStatement = connect.prepareStatement("insert into mydb.despesa values (?,?,?)\n" +
            "ON DUPLICATE KEY UPDATE Id=VALUES(Id),  Categoria=VALUES(Categoria), Descricao = VALUES(Descricao), statement.RETURN_GENERATED_KEYS");

            preparedStatement.setInt(1,despesa.getId());
            preparedStatement.setInt(2,despesa.getCategoria());
            preparedStatement.setString(3,despesa.getDescricao());
            preparedStatement.executeUpdate();


            TreeMap<Integer,Float> racio = despesa.getRacio();

            for(Integer n : racio.keySet() ){
                preparedStatement = connect.prepareStatement("insert into mydb.racio values (?,?,?)\n" +
                "ON DUPLICATE KEY UPDATE Morador=VALUES(Morador),  Despesa=VALUES(Despesa), Racio = VALUES(Racio), statement.RETURN_GENERATED_KEYS");

                preparedStatement.setInt(1,despesa.getId());
                preparedStatement.setInt(2,n);
                preparedStatement.setFloat(3, racio.get(n));
                preparedStatement.executeUpdate();

            }

            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()){
                int newId = resultSet.getInt(1);
                despesa.setId(newId);
            }
            a = despesa;
        }catch(SQLException e){

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
            preparedStatement = connect.prepareStatement("delete from mydb.despesa where Id = ? ; ");
            preparedStatement.setInt(1,(int)key);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from mydb.movimento where Id = ? ; ");
            preparedStatement.setInt(1,(int)key);
            preparedStatement.executeUpdate();

            preparedStatement = connect.prepareStatement("delete from mydb.racio where Despesa = ? ; ");
            preparedStatement.setInt(1,(int)key);
            preparedStatement.executeUpdate();


        }catch (SQLException e){

        }
        return a;
    }

    @Override
    public int size(){
        int i=0;
        try{

            resultSet = statement.executeQuery("select * form mydb.despesa");

            while(resultSet.next()){
                i++;
            }

        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }

        return i;

    }

    @Override
    public Collection<Despesa> values(){
        Collection<Despesa> cat = new HashSet<>();
        try{
            resultSet = statement.executeQuery("select * from mydb.despesa");
            while(resultSet.next()){


                int id = resultSet.getInt("Id");
                preparedStatement = connect.prepareStatement("select * from mybd.movimento where id=?");
                preparedStatement.setInt(1, id);
                ResultSet movimento = preparedStatement.executeQuery();

                preparedStatement = connect.prepareStatement("select * from mybd.Despesa where Despesa=?");
                preparedStatement.setInt(1, id);
                ResultSet racio = preparedStatement.executeQuery();

                TreeMap<Integer,Float> racios = new TreeMap();

                    while(racio.next()){
                        int i=0;
                        racios.put(i, racio.getFloat("Racio"));
                        i++;
                }

                cat.add(new Despesa(movimento.getInt("Id"), movimento.getInt("Apartamento"),movimento.getInt("Morador"),movimento.getFloat("Valor"),movimento.getDate("Data"),movimento.getBoolean("Transacao"),resultSet.getInt("Categoria"),resultSet.getString("Descrição"),racios));
            }
        } catch (SQLException e) {
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
        return this.connect.hashCode();
    }

    @Override
    public Set<Integer> keySet(){
        throw new NullPointerException("Not implemented!");
    }




}