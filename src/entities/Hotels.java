package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Hotels implements CRUD {

    @Override
    public void retrieve() throws SQLException {
        String query = "select * from " + NS.HOTELS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("HotelId" + " |" + "name" + " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "phNumber");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int hotelId = result.getInt(NS.HOTELS_ID);
                String name = result.getString(NS.HOTELS_NAME);
                String address = result.getString(NS.HOTELS_ADDRESS);
                String city = result.getString(NS.HOTELS_CITY);
                String state = result.getString(NS.HOTELS_STATE);
                String country = result.getString(NS.HOTELS_COUNTRY);
                String phNumber = result.getString(NS.HOTELS_PH_NUMBER);
                int managerId = result.getInt(NS.HOTELS_MANAGER_ID);

                System.out.println(hotelId + " |" + name + " |" + address + " |" + city + " |" + state + " |" + country + " |" + phNumber + " |" + managerId);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter hotel name : ");
        String name = scan.nextLine();
        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();


        String query = "Insert into " + NS.HOTELS_TABLE + "(" + NS.HOTELS_NAME + "," + NS.HOTELS_ADDRESS + "," + NS.HOTELS_CITY + ","
                + NS.HOTELS_STATE + "," + NS.HOTELS_COUNTRY + "," + NS.HOTELS_PH_NUMBER + ") values('" + name + "','"
                + address + "','" + city + "','" + state + "','" + country + "'," + phNum + ")";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter hotel ID to update : ");
        String hotelId = scan.nextLine();

        String updateString = new String();

        System.out.println("Enter only update values");
        System.out.println("Enter hotel name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += NS.HOTELS_NAME + " = '" + name + "'";

        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_ADDRESS + " = '" + address + "'";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter Manager ID : ");
        String managerId = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.HOTELS_MANAGER_ID + " = " + managerId + "";
        }

        String query = "Update " + NS.HOTELS_TABLE + " set " + updateString + " where " + NS.HOTELS_ID + " = " + hotelId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();

        String query = "Delete from " + NS.HOTELS_TABLE + " where " + NS.HOTELS_ID + " = " + hotelId;
        execute(query);

    }

}
