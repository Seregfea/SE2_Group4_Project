package org.example.Database;

import java.sql.*;

public class Database {

    public static void main(String args[]) throws SQLException {
        Connection con;
        PreparedStatement pst;
        Statement statement;

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gruppe4_se2", "root", "gruppe4");
            System.out.println("success connected to: " + con.getCatalog() );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        statement = con.createStatement();
        ResultSet resultset = statement.executeQuery("select * from player");

        while (resultset.next()){
            System.out.println("//////////////// index ////////////////");
            System.out.println(resultset.getString("name"));
        }

    }
}
