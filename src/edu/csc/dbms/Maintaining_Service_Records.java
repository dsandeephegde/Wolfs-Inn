package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

import entities.Buys;

public class Maintaining_Service_Records{
    protected Scanner scan = new Scanner(System.in);
    protected Buys buys = new Buys();

    public void operations() throws SQLException{
        System.out.println("1. Add a service availed by the customer.");
        System.out.println("2. Update prices of services");
        System.out.println("3. Delete a service not availed by customer");

        int option = Integer.parseInt(scan.nextLine());

        switch (option) {
            case 1:
                buys.create();
                buys.retrieve();
                break;
            case 2:
                buys.update();
                buys.retrieve();
                break;
            case 3:
                buys.delete();
                buys.retrieve();
                break;
            default:
                System.out.println("Please Enter a valid option!");

		}
	}
}