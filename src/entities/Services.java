package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Services implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + NS.SERVICES_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("ServiceId" + " |" + "name" + " |" + "basePrice");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int serviceId = result.getInt(NS.SERVICES_ID);
                String name = result.getString(NS.SERVICES_NAME);
                int basePrice = result.getInt(NS.SERVICES_BASE_PRICE);

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

        String query = "Insert into " + NS.SERVICES_TABLE + "(" + NS.SERVICES_NAME + "," + NS.SERVICES_BASE_PRICE + ") values('" + name + "'," + basePrice + ")";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter service ID to update : ");
        String serviceId = scan.nextLine();

        String updateString = new String();

        System.out.println("Enter only update values");
        System.out.println("Enter service name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += NS.SERVICES_NAME + " = '" + name + "'";

        System.out.println("Enter base price : ");
        String basePrice = scan.nextLine();

        if (!basePrice.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.SERVICES_BASE_PRICE + " = " + basePrice;
        }

        String query = "Update " + NS.SERVICES_TABLE + " set " + updateString + " where " + NS.SERVICES_ID + " = " + serviceId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter service ID : ");
        String serviceId = scan.nextLine();

        String query = "Delete from " + NS.SERVICES_TABLE + " where " + NS.SERVICES_ID + " = " + serviceId;
        execute(query);

    }

}
