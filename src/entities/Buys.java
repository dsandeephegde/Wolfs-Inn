package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;
import edu.csc.dbms.DBUtil;

public class Buys implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.BUYS_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        if (result != null) {

            System.out.println("serviceId" + " |" + "checkinId" + " |" + "price");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int serviceId = result.getInt(Constants.BUYS_SERVICEID);
                int checkinId = result.getInt(Constants.BUYS_CHECKINID);
                int price = result.getInt(Constants.BUYS_PRICE);

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

        String query = "Insert into " + Constants.BUYS_TABLE + "(" + Constants.BUYS_SERVICEID + "," + Constants.BUYS_CHECKINID + ","
                + Constants.BUYS_PRICE + ") values('" + serviceId + "','" + checkinId + "','" + price + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter serviceId to update : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter price: ");
        String price = scan.nextLine();

        if (!price.isEmpty())
            updateString += Constants.BUYS_PRICE + " = '" + price + "'";

        String query = "Update " + Constants.BUYS_TABLE + " set " + updateString + " where " + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter serviceId : ");
        String serviceId = scan.nextLine();
        System.out.println("Enter checkinId : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + Constants.BUYS_TABLE + " where " + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

}
