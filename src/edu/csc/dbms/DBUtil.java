package edu.csc.dbms;

import java.sql.*;

public class DBUtil {

    public static ResultSet executeQuery(String query) {
        //URLs
        final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        final String DB_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/araja2";

        //  Database credentials
        final String USER = "araja2";
        final String PASS = "200203475";

        Connection conn;
        Statement stmt;
        ResultSet result = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            result = stmt.executeQuery(query);

        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return result;

    }

}
