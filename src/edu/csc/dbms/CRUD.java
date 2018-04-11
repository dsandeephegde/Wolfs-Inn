package edu.csc.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public interface CRUD {

    public void retrieve() throws SQLException;

    public void create(Scanner scan) throws SQLException;

    public void update(Scanner scan) throws SQLException;

    public void delete(Scanner scan) throws SQLException;

    static void select(CRUD entityObject, Scanner scan) throws SQLException {

        System.out.println("1. Show all entries");
        System.out.println("2. Create new entry");
        System.out.println("3. Update existing entry");
        System.out.println("4. Delete an entry");

        System.out.println();
        System.out.print("Select from the following option :");

        int selected = Integer.parseInt(scan.nextLine());

        System.out.println();

        switch (selected) {

            case 1:
                entityObject.retrieve();
                break;

            case 2:
                entityObject.create(scan);
                entityObject.retrieve();
                break;

            case 3:
                entityObject.retrieve();
                entityObject.update(scan);
                entityObject.retrieve();
                break;

            case 4:
                entityObject.retrieve();
                entityObject.delete(scan);
                entityObject.retrieve();
                break;
        }

    }

    default ResultSet execute(String query) {

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
