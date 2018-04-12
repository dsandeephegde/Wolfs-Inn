package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;

public class Services implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.SERVICES_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("ServiceId" + " |" + "name" + " |" + "basePrice");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int serviceId = result.getInt(Constants.SERVICES_ID);
                String name = result.getString(Constants.SERVICES_NAME);
                int basePrice = result.getInt(Constants.SERVICES_BASE_PRICE);

                System.out.println(serviceId + " |" + name + " |" + basePrice);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter Service name : ");
        String name = scan.nextLine();
        System.out.println("Enter base price : ");
        String basePrice = scan.nextLine();

        String query = "Insert into " + Constants.SERVICES_TABLE + "(" + Constants.SERVICES_NAME + "," + Constants.SERVICES_BASE_PRICE + ") values('" + name + "'," + basePrice + ")";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter service ID to update : ");
        String serviceId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter service name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.SERVICES_NAME + " = '" + name + "'";

        System.out.println("Enter base price : ");
        String basePrice = scan.nextLine();

        if (!basePrice.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.SERVICES_BASE_PRICE + " = " + basePrice;
        }

        String query = "Update " + Constants.SERVICES_TABLE + " set " + updateString + " where " + Constants.SERVICES_ID + " = " + serviceId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter service ID : ");
        String serviceId = scan.nextLine();

        String query = "Delete from " + Constants.SERVICES_TABLE + " where " + Constants.SERVICES_ID + " = " + serviceId;
        execute(query);

    }

}
