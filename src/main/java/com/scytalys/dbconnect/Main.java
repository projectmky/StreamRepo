package com.scytalys.dbconnect;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class Main {
    public static void main(String[] array) throws SQLException {
        Connection connection = ConnectionSingleton.getConnection();
        //Checked needs try-catch or throw block &Unchecked-runtime doesn't know

        String dropTableCommand = "DROP TABLE IF EXISTS products";
        String createTableCommand ="CREATE TABLE IF NOT EXISTS products(id serial primary key, productName varchar(50),price numeric(12,2))";
        String insertCommand ="INSERT INTO products (productName)  VALUES (?)";


        System.out.println(ConnectionSingleton.executeQuery(connection, dropTableCommand));
        System.out.println(ConnectionSingleton.executeQuery(connection, createTableCommand));

       PreparedStatement preparedStatement = connection.prepareStatement(insertCommand);
       preparedStatement.setString(1,"chips");

        try(Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("productName"));
            }

    }
        catch (SQLException e){
        e.printStackTrace();
        }
    }

}
