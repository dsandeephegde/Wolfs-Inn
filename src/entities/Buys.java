package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Buys implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + NS.BUYS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("serviceId" + " |" + "checkinId" + " |" + "price");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int serviceId = result.getInt(NS.BUYS_SERVICEID);
                int checkinId = result.getInt(NS.BUYS_CHECKINID);
                int price = result.getInt(NS.BUYS_PRICE);

                System.out.println(serviceId + " |" + checkinId + " |" + price);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter serviceId: ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId: ");
        String checkinId = scan.nextLine();
        System.out.println("Enter price : ");
        String price = scan.nextLine();

        String query = "Insert into " + NS.BUYS_TABLE + "(" + NS.BUYS_SERVICEID + "," + NS.BUYS_CHECKINID + ","
                + NS.BUYS_PRICE + ") values('" + serviceId + "','" + checkinId + "','" + price + "')";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter serviceId to update : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String updateString = new String();

        System.out.println("Enter only update values");
        System.out.println("Enter price: ");
        String price = scan.nextLine();

        if (!price.isEmpty())
            updateString += NS.BUYS_PRICE + " = '" + price + "'";

        String query = "Update " + NS.BUYS_TABLE + " set " + updateString + " where " + NS.BUYS_SERVICEID + " = " + serviceId + " and " + NS.BUYS_CHECKINID + " = " + checkinId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter serviceId : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + NS.BUYS_TABLE + " where " + NS.BUYS_SERVICEID + " = " + serviceId + " and " + NS.BUYS_CHECKINID + " = " + checkinId;
        execute(query);

    }

}
