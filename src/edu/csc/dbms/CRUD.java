package edu.csc.dbms;

import java.sql.SQLException;
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
}
