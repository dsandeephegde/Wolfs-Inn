package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;

public class Rooms implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.ROOMS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("roomNumber" + " |" + "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int roomNumber = result.getInt(Constants.ROOMS_ROOMNUMBER);
                int hotelId = result.getInt(Constants.ROOMS_HOTELID);
                String category = result.getString(Constants.ROOMS_CATEGORY);
                int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
                boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

                System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter room number : ");
        String roomNumber = scan.nextLine();
        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();
        System.out.println("Enter category : ");
        String category = scan.nextLine();
        System.out.println("Enter max occupancy : ");
        String maxOccupancy = scan.nextLine();
        System.out.println("Enter availability : ");
        String availability = scan.nextLine();

        String query = "Insert into " + Constants.ROOMS_TABLE + "(" + Constants.ROOMS_ROOMNUMBER + "," + Constants.ROOMS_HOTELID + "," + Constants.ROOMS_CATEGORY + ","
                + Constants.ROOMS_MAXOCCUPANCY + "," + Constants.ROOMS_AVAILABILITY + ") values('" + roomNumber + "','"
                + hotelId + "','" + category + "','" + maxOccupancy + "','" + availability + "')";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter room number : ");
        String roomNumber = scan.nextLine();

        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter category : ");
        String category = scan.nextLine();

        if (!category.isEmpty())
            updateString += Constants.ROOMS_CATEGORY + " = '" + category + "'";

        System.out.println("Enter max occupancy : ");
        String maxOccupancy = scan.nextLine();

        if (!maxOccupancy.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.ROOMS_MAXOCCUPANCY + " = '" + maxOccupancy + "'";
        }


        System.out.println("Enter availability : ");
        String availability = scan.nextLine();

        if (!availability.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.ROOMS_AVAILABILITY + " = '" + availability + "'";
        }

        String query = "Update " + Constants.ROOMS_TABLE + " set " + updateString + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter room number : ");
        String roomNumber = scan.nextLine();

        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();

        String query = "Delete from " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + Constants.ROOMS_HOTELID + " = " + hotelId;
        execute(query);

    }

}
