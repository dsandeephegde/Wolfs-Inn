package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        int option = 0;
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");

        try {
            while (option != 100) {

                //NS.listTasks();

                System.out.println("1. CRUD operations");
                System.out.println("2. Task and Operations");

                System.out.println();
                System.out.print("Select the task :");

                option = Integer.parseInt(scan.nextLine());

                System.out.println();

                switch (option) {

                    case 1: //CRUD operations
                        NS.listEntities(scan);
                        break;

                    case 2: //task

                }

                System.out.println();
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }


        System.out.println("Thank you for using Wolfs Inn");
    }

}
