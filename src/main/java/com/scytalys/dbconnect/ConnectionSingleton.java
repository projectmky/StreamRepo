package com.scytalys.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static final String connectionString = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "dimitris";
    private static final String password = "@pass0rd@@@";

    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            connection = createConnection();
        }
        return connection;
    }

    private static Connection createConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionString, user,password);
            if(conn != null){
                System.out.println("Connection established");
                return conn;
            }
            else {
                System.out.println("Failed to establish connection");
                return null;
            }
        } catch (SQLException e) {
           e.printStackTrace();
           return null;
        }

    }

    public static int executeQuery(Connection conn, String query) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        int rowsInserted = preparedStatement.executeUpdate();
        return rowsInserted;
    }

}
