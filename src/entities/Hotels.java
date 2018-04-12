package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;

public class Hotels implements CRUD {

    @Override
    public void retrieve() throws SQLException {
        String query = "select * from " + Constants.HOTELS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("HotelId" + " |" + "name" + " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "phNumber");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int hotelId = result.getInt(Constants.HOTELS_ID);
                String name = result.getString(Constants.HOTELS_NAME);
                String address = result.getString(Constants.HOTELS_ADDRESS);
                String city = result.getString(Constants.HOTELS_CITY);
                String state = result.getString(Constants.HOTELS_STATE);
                String country = result.getString(Constants.HOTELS_COUNTRY);
                String phNumber = result.getString(Constants.HOTELS_PH_NUMBER);
                int managerId = result.getInt(Constants.HOTELS_MANAGER_ID);

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


        String query = "Insert into " + Constants.HOTELS_TABLE + "(" + Constants.HOTELS_NAME + "," + Constants.HOTELS_ADDRESS + "," + Constants.HOTELS_CITY + ","
                + Constants.HOTELS_STATE + "," + Constants.HOTELS_COUNTRY + "," + Constants.HOTELS_PH_NUMBER + ") values('" + name + "','"
                + address + "','" + city + "','" + state + "','" + country + "'," + phNum + ")";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter hotel ID to update : ");
        String hotelId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter hotel name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.HOTELS_NAME + " = '" + name + "'";

        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_ADDRESS + " = '" + address + "'";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter Manager ID : ");
        String managerId = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.HOTELS_MANAGER_ID + " = " + managerId + "";
        }

        String query = "Update " + Constants.HOTELS_TABLE + " set " + updateString + " where " + Constants.HOTELS_ID + " = " + hotelId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();

        String query = "Delete from " + Constants.HOTELS_TABLE + " where " + Constants.HOTELS_ID + " = " + hotelId;
        execute(query);

    }

}
