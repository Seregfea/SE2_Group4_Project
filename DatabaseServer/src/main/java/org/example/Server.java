package org.example;

import java.sql.*;

public class Server {

    public static void main(String args[]){
        Connection con;
        PreparedStatement pst;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/gruppe4_se2", "root", "gruppe4");
            System.out.println("success connected to: " + con.getCatalog() );
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
