package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;

public class Staffs implements CRUD {

    @Override
    public void retrieve() throws SQLException {
        String query = "select * from " + Constants.STAFFS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("StaffId" + " |" + "name" + " |" + "age" + " |" + "jobTitle" + " |" + "phoneNumber"
                    + " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "hotelId");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int staffId = result.getInt(Constants.STAFFS_ID);
                String name = result.getString(Constants.STAFFS_NAME);
                int age = result.getInt(Constants.STAFFS_AGE);
                String jobTitle = result.getString(Constants.STAFFS_JOB_TITLE);
                String phNumber = result.getString(Constants.STAFFS_PH_NUMBER);
                String address = result.getString(Constants.STAFFS_ADDRESS);
                String city = result.getString(Constants.STAFFS_CITY);
                String state = result.getString(Constants.STAFFS_STATE);
                String country = result.getString(Constants.STAFFS_COUNTRY);
                int hotelId = result.getInt(Constants.STAFFS_HOTEL_ID);

                System.out.println(staffId + " |" + name + " |" + age + " |" + jobTitle + " |" + phNumber + " |" + address + " |" + city + " |" + state + " |" + country + " |" + hotelId);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter Staff name : ");
        String name = scan.nextLine();
        System.out.println("Enter age : ");
        String age = scan.nextLine();
        System.out.println("Enter jobTitle : ");
        String jobTitle = scan.nextLine();
        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();
        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter Hotel Id : ");
        String hotelId = scan.nextLine();


        String query = "Insert into " + Constants.STAFFS_TABLE + "(" + Constants.STAFFS_NAME + "," + Constants.STAFFS_AGE + "," + Constants.STAFFS_JOB_TITLE + ","
                + Constants.STAFFS_PH_NUMBER + "," + Constants.STAFFS_ADDRESS + "," + Constants.STAFFS_CITY + "," + Constants.STAFFS_STATE + ","
                + Constants.STAFFS_COUNTRY + "," + Constants.STAFFS_HOTEL_ID + ") values('" + name + "'," + age + ",'" + jobTitle
                + "','" + phNum + "','" + address + "','" + city + "','" + state + "','" + country + "'," + hotelId
                + ")";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter Staff ID to update : ");
        String staffId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter staff name : ");
        String name = scan.nextLine();

        if (!name.isEmpty())
            updateString += Constants.STAFFS_NAME + " = '" + name + "'";

        System.out.println("Enter age : ");
        String age = scan.nextLine();

        if (!age.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_AGE + " = " + age;
        }

        System.out.println("Enter Job Title : ");
        String jobTitle = scan.nextLine();

        if (!jobTitle.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_JOB_TITLE + " = '" + jobTitle + "'";
        }

        System.out.println("Enter phone number : ");
        String phNum = scan.nextLine();

        if (!phNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_PH_NUMBER + " = '" + phNum + "'";
        }

        System.out.println("Enter line 1 address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_ADDRESS + " = '" + address + "'";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter Hotel ID : ");
        String hotelId = scan.nextLine();

        if (!hotelId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.STAFFS_HOTEL_ID + " = " + hotelId + "";
        }

        String query = "Update " + Constants.STAFFS_TABLE + " set " + updateString + " where " + Constants.STAFFS_ID + " = " + staffId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter staff ID : ");
        String staffId = scan.nextLine();

        String query = "Delete from " + Constants.STAFFS_TABLE + " where " + Constants.STAFFS_ID + " = " + staffId;
        execute(query);

    }

}
