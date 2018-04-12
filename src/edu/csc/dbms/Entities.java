package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

import entities.*;

public class Entities {

    public static void list(Scanner scan) throws SQLException {

        System.out.println("1. Hotels");
        System.out.println("2. Staffs");
        System.out.println("3. Customers");
        System.out.println("4. CheckIns");
        System.out.println("5. PaymentInfos");
        System.out.println("6. Rooms");
        System.out.println("7. RoomPrices");
        System.out.println("8. Services");
        System.out.println("9. Buys");
        System.out.println("10. Serves");

        System.out.println();
        System.out.print("Select the table :");
        System.out.println();

        int option = Integer.parseInt(scan.nextLine());
        CRUD entityObject = null;

        switch (option) {

            case 1:
                entityObject = new Hotels();
                break;
            case 2:
                entityObject = new Staffs();
                break;
            case 3:
                entityObject = new Customers();
                break;
            case 4:
                entityObject = new CheckIns();
                break;
            case 5:
                entityObject = new PaymentInfos();
                break;
            case 6:
                entityObject = new Rooms();
                break;
            case 7:
                entityObject = new RoomPrices();
                break;
            case 8:
                entityObject = new Services();
                break;
            case 9:
                entityObject = new Buys();
                break;
            case 10:
                entityObject = new Serves();
                break;
        }

        CRUD.select(entityObject, scan);

    }
}

