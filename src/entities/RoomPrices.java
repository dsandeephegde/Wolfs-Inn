package entities;

import edu.csc.dbms.Constants;
import edu.csc.dbms.DBUtil;
import edu.csc.dbms.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RoomPrices implements Entity {
    private static Scanner scan = new Scanner(System.in);

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.ROOM_PRICES_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        if (result != null) {

            System.out.println("category" + " |" + "maxOccupancy" + " |" + "price");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                String category = result.getString(Constants.ROOM_PRICES_CATEGORY);
                int maxOccupancy = result.getInt(Constants.ROOM_PRICES_MAXOCCUPANCY);
                int price = result.getInt(Constants.ROOM_PRICES_PRICE);

                System.out.println(category + " |" + maxOccupancy + " |" + price);
            }
        }

    }

    @Override
    public void create() throws SQLException {

        System.out.println("Enter category : ");
        String category = scan.nextLine();
        System.out.println("Enter maxOccupancy : ");
        String maxOccupancy = scan.nextLine();
        System.out.println("Enter price : ");
        String price = scan.nextLine();

        String query = "Insert into " + Constants.ROOM_PRICES_TABLE + "(" + Constants.ROOM_PRICES_CATEGORY + "," + Constants.ROOM_PRICES_MAXOCCUPANCY + "," + Constants.ROOM_PRICES_PRICE
                + ") values('" + category + "','"
                + maxOccupancy + "','" + price + "')";
        DBUtil.executeQuery(query);

    }

    @Override
    public void update() throws SQLException {

        System.out.println("Enter category : ");
        String category = scan.nextLine();

        System.out.println("Enter maxOccupancy : ");
        String maxOccupancy = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter price : ");
        String price = scan.nextLine();

        if (!price.isEmpty())
            updateString += Constants.ROOM_PRICES_PRICE + " = " + price;

        String query = "Update " + Constants.ROOM_PRICES_TABLE + " set " + updateString + " where " + Constants.ROOM_PRICES_CATEGORY + " LIKE '" + category + "' and " + Constants.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy + "'";
        DBUtil.executeQuery(query);

    }

    @Override
    public void delete() throws SQLException {

        System.out.println("Enter category : ");
        String category = scan.nextLine();

        System.out.println("Enter maxOccupancy : ");
        String maxOccupancy = scan.nextLine();

        String query = "Delete from " + Constants.ROOM_PRICES_TABLE + " where " + Constants.ROOM_PRICES_CATEGORY + " = '" + category + "' AND " + Constants.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy + "'";
        DBUtil.executeQuery(query);

    }

}
