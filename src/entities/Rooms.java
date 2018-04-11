package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Rooms implements CRUD {

	@Override
	public void retrieve() throws SQLException {
		
		String query = "select * from "+NS.ROOMS_TABLE;

		ResultSet result = execute(query);

		if(result != null) {

			System.out.println("roomNumber" + " |"+ "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
			System.out.println("---------------------------------------------------------------");

			while(result.next()) {

				int roomNumber = result.getInt(NS.ROOMS_ROOMNUMBER);
				int hotelId = result.getInt(NS.ROOMS_HOTELID);
				String category = result.getString(NS.ROOMS_CATEGORY);
				int maxOccupancy = result.getInt(NS.ROOMS_MAXOCCUPANCY);
				boolean availability = result.getBoolean(NS.ROOMS_AVAILABILITY);

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

		String query = "Insert into " + NS.ROOMS_TABLE + "(" + NS.ROOMS_ROOMNUMBER + "," + NS.ROOMS_HOTELID + "," + NS.ROOMS_CATEGORY + ","
				+ NS.ROOMS_MAXOCCUPANCY + "," + NS.ROOMS_AVAILABILITY + ") values('" + roomNumber + "','"
				+ hotelId + "','" + category + "','" + maxOccupancy + "','" + availability +"')";
		execute(query);

	}

	@Override
	public void update(Scanner scan) throws SQLException {
		
		System.out.println("Enter room number : ");
		String roomNumber = scan.nextLine();

		System.out.println("Enter hotelId : ");
		String hotelId = scan.nextLine();

		String updateString = new String();

		System.out.println("Enter only update values");
		System.out.println("Enter category : ");
		String category = scan.nextLine();

		if(!category.isEmpty())
			updateString += NS.ROOMS_CATEGORY + " = '" +  category + "'";

		System.out.println("Enter max occupancy : ");
		String maxOccupancy = scan.nextLine();

		if(!maxOccupancy.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.ROOMS_MAXOCCUPANCY + " = '" +  maxOccupancy + "'";
		}


		System.out.println("Enter availability : ");
		String availability = scan.nextLine();

		if(!availability.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.ROOMS_AVAILABILITY + " = '" +  availability + "'";
		}

		String query = "Update " + NS.ROOMS_TABLE + " set " + updateString + " where " + NS.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + NS.ROOMS_HOTELID + " = " + hotelId;
		execute(query);

	}

	@Override
	public void delete(Scanner scan) throws SQLException {
		
		System.out.println("Enter room number : ");
		String roomNumber = scan.nextLine();

		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();

		String query = "Delete from " + NS.ROOMS_TABLE + " where " + NS.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + NS.ROOMS_HOTELID + " = " + hotelId;
		execute(query);

	}

}
