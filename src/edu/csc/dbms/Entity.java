package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

public interface Entity {

    void retrieve() throws SQLException;

    void create() throws SQLException;

    void update() throws SQLException;

    void delete() throws SQLException;

    default void crudOperations() throws SQLException {

        Scanner scan = new Scanner(System.in);
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
                retrieve();
                break;

            case 2:
                create();
                retrieve();
                break;

            case 3:
                retrieve();
                update();
                retrieve();
                break;

            case 4:
                retrieve();
                delete();
                retrieve();
                break;
        }

    }
}
