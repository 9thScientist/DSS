package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	private static final String URL = "localhost";
	private static final String DB = "mydb";
	private static final String USERNAME = "dss";
	private static final String PASSWORD = "dsspw";

    public static Connection connect() throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("not null");
            return DriverManager.getConnection("jdbc:mysql://"+URL+"/"+DB+"?user="+
				USERNAME+"&password="+PASSWORD+"&useSSL=false");
        }

    public static void close(Connection c) {
        try {
            if (c != null && !c.isClosed())
				c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
