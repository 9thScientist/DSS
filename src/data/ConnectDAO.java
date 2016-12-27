package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class ConnectDAO {

    protected Connection connect = null;
    protected Statement statement = null;

    public ConnectDAO()throws Exception{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mydb?user=dss&password=dsspw&useSSL=false");
            statement = connect.createStatement();
        }
        catch (ClassNotFoundException e){
        }
    }

    public void close() throws SQLException {
        try {
            if (connect != null)
                    connect.close();
        }
        catch (SQLException e) {
        }
    }

    public Connection getCon(){
        return connect;
    }
}
