package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class RoomPrices implements CRUD {

	@Override
	public void retrieve() throws SQLException {
		
		String query = "select * from "+NS.ROOM_PRICES_TABLE;

		ResultSet result = execute(query);

		if(result != null) {

			System.out.println("category" + " |"+ "maxOccupancy" + " |" + "price");
			System.out.println("---------------------------------------------------------------");

			while(result.next()) {

				String category = result.getString(NS.ROOM_PRICES_CATEGORY);
				int maxOccupancy = result.getInt(NS.ROOM_PRICES_MAXOCCUPANCY);
				int price = result.getInt(NS.ROOM_PRICES_PRICE);

				System.out.println(category + " |" + maxOccupancy + " |" + price );
			}
		}

	}

	@Override
	public void create(Scanner scan) throws SQLException {
		
		System.out.println("Enter category : ");
		String category = scan.nextLine();
		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();
		System.out.println("Enter price : ");
		String price = scan.nextLine();

		String query = "Insert into " + NS.ROOM_PRICES_TABLE + "(" + NS.ROOM_PRICES_CATEGORY + "," + NS.ROOM_PRICES_MAXOCCUPANCY + "," + NS.ROOM_PRICES_PRICE
				+ ") values('" + category + "','"
				+ maxOccupancy + "','" + price +"')";
		execute(query);

	}

	@Override
	public void update(Scanner scan) throws SQLException {
		
		System.out.println("Enter category : ");
		String category = scan.nextLine();

		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();

		String updateString = new String();

		System.out.println("Enter only update values");
		System.out.println("Enter price : ");
		String price = scan.nextLine();

		if(!price.isEmpty())
			updateString += NS.ROOM_PRICES_PRICE + " = " +  price;

		String query = "Update " + NS.ROOM_PRICES_TABLE + " set " + updateString + " where " + NS.ROOM_PRICES_CATEGORY + " LIKE '" + category + "' and " + NS.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy +"'";
		execute(query);

	}

	@Override
	public void delete(Scanner scan) throws SQLException {
		
		System.out.println("Enter category : ");
		String category = scan.nextLine();

		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();

		String query = "Delete from " + NS.ROOM_PRICES_TABLE + " where " + NS.ROOM_PRICES_CATEGORY + " = '" + category + "' AND " + NS.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy +"'";
		execute(query);

	}

}
