package mx.edu.utez.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionMySQL {
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/usuario?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
    }

    public static void main(String[] args) {
        try{
            Connection con = ConnectionMySQL.getConnection();
            System.out.println("Conexión exitosa");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

