package edu.csc.dbms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Info_Processing {
	Scanner scan =new Scanner(System.in);
	  public ResultSet execute(String query) {

        //URLs
        final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
        final String DB_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/araja2";

        //  Database credentials
        final String USER = "araja2";
        final String PASS = "200203475";

        Connection conn;
        Statement stmt;
        ResultSet result = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            result = stmt.executeQuery(query);

        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        return result;

    }
	  public void info_processing() throws SQLException
		{
			System.out.println("1. Check Number of Rooms available based on room type");
			System.out.println("2. List all the Rooms available based on room type");
			System.out.println("3. Assign rooms based on request and availability and type of room requested, if type of room is requested");
			System.out.println("4. Assign rooms based on request and availability and type of room requested, if the Room Number is provided");
			System.out.println("5. Release Room");
			
			System.out.println();
			System.out.print("Select the information to be processed :");
			System.out.println();

			int option = Integer.parseInt(scan.nextLine());
			switch (option)
			{
			case 1:
				check_available_rooms();
				break;
			case 2:
				rooms_list_by_roomtype();
				break;
			case 3:
				assign_rooms_by_type();
				break;
			case 4:
				assign_rooms_by_roomnumber();
				break;
			case 5:
				release_room();
				break;
			default :
				System.out.println("Please Enter a valid option ....");	
			}
		}
	  public void check_available_rooms() throws SQLException
	  {		
		  	System.out.println("Enter the category of Room to check availability: ");
			String category =scan.nextLine();

			System.out.println("Enter hotelId : ");
			String hotelId = scan.nextLine();
			String query = "Select COUNT(*) as No_of_Rooms_Available FROM " + Constants.ROOMS_TABLE +   " where " + Constants.ROOMS_HOTELID + " = " + hotelId +  " and "  + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = "+ "1";
			ResultSet result = execute(query);
			
			if (result != null) {
			System.out.println("No_of_Rooms_Available");
			System.out.println("---------------------------------------------------------------");
			while (result.next()) {
			System.out.println(result.getInt("No_of_Rooms_Available"));
			}
			}	
	  }
	  public void rooms_list_by_roomtype() throws SQLException
	  {
		  System.out.println("Enter the category of Room to check availability: ");
		  String category =scan.nextLine();

			String query = "Select * FROM " + Constants.ROOMS_TABLE +   " where " + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = "+ "1";
			ResultSet result = execute(query);

	        if (result != null) {

	            System.out.println("roomNumber" + " |" + "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                int roomNumber = result.getInt(Constants.ROOMS_ROOMNUMBER);
	                int hotelId = result.getInt(Constants.ROOMS_HOTELID);
	                int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
	            }
	        }
		  
	  }
	  public void assign_rooms_by_type() throws SQLException
	  {	
		  String roomNumber_assign = "";
		  System.out.println("Enter the category of Room to check availability: ");
		  String category =scan.nextLine();
		  System.out.println("Enter the hotelId: ");
		  String hotelId =scan.nextLine();
		  
		  String query = "Select * FROM " + Constants.ROOMS_TABLE +   " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and "  + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = "+ " 1 " + " LIMIT 1";
		  ResultSet result = execute(query);
		  if (result != null) {

	            System.out.println("roomNumber" + " |" + "hotelId" + " |" + "maxOccupancy" + " |" + "availability");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                String roomNumber = result.getString(Constants.ROOMS_ROOMNUMBER);
	                roomNumber_assign = roomNumber;
	                int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                System.out.println(roomNumber + " |" + hotelId + " |" + maxOccupancy + " |" + availability);
	            }
		  }
		  System.out.println("Enter startDate : ");
	        String startDate = scan.nextLine();
	        System.out.println("Enter endDate : ");
	        String endDate = scan.nextLine();
	        System.out.println("Enter checkinTime : ");
	        String checkinTime = scan.nextLine();
	        System.out.println("Enter checkoutTime : ");
	        String checkoutTime = scan.nextLine();
	        System.out.println("Enter numberOfGuests : ");
	        String numberOfGuests = scan.nextLine();
	        System.out.println("Enter total : ");
	        String total = scan.nextLine();
	        System.out.println("Enter customerId : ");
	        String customerId = scan.nextLine();
	        System.out.println("Enter paymentId : ");
	        String paymentId = scan.nextLine();
	        
		  query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + ","
	                + Constants.CHECK_INS_ENDDATE + "," + Constants.CHECK_INS_CHECKINTIME + "," + Constants.CHECK_INS_CHECKOUTTIME + ","
	                + Constants.CHECK_INS_NUMBEROFGUESTS + "," + Constants.CHECK_INS_TOTAL + "," + Constants.CHECK_INS_CUSTOMERID
	                + "," + Constants.CHECK_INS_HOTELID + "," + Constants.CHECK_INS_ROOMNUMBER + "," + Constants.CHECK_INS_PAYMENTID + ") values('" + startDate + "','" + endDate + "','" + checkinTime + "','"
	                + checkoutTime + "','" + numberOfGuests + "','" + total + "','" + customerId + "','" + hotelId + "','" + roomNumber_assign + "','" + paymentId + "')";
		  execute(query);
		   query = "select * from " + Constants.CHECK_INS_TABLE;

	        result = execute(query);

	        if (result != null) {

	            System.out.println("checkinId" + " |" + "startDate" + " |" + "endDate" + " |" + "checkinTime" + " |" + "checkoutTIme" + " |" + "numberOfGuests" + " |" + "total" + " |" + "customerId" + " |" + "hotelId" + " |" + "roomNumber" + " |" + "paymentId");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                 int checkinId = result.getInt(Constants.CHECK_INS_CHECKINID);
	                 startDate = result.getString(Constants.CHECK_INS_STARTDATE);
	                 endDate = result.getString(Constants.CHECK_INS_ENDDATE);
	                 checkinTime = result.getString(Constants.CHECK_INS_CHECKINTIME);
	                 checkoutTime = result.getString(Constants.CHECK_INS_CHECKOUTTIME);
	                 numberOfGuests = result.getString(Constants.CHECK_INS_NUMBEROFGUESTS);
	                 String totals = result.getString(Constants.CHECK_INS_TOTAL);
	                 customerId = result.getString(Constants.CHECK_INS_CUSTOMERID);
	                 hotelId = result.getString(Constants.CHECK_INS_HOTELID);
	                 String roomNumber = result.getString(Constants.CHECK_INS_ROOMNUMBER);
	                 paymentId = result.getString(Constants.CHECK_INS_PAYMENTID);

	                System.out.println(checkinId + " |" + startDate + " |" + endDate + " |" + checkinTime + " |" + checkoutTime + " |" + numberOfGuests + " |" + totals + " |" + customerId + " |" + hotelId + " |" + roomNumber + " |" + paymentId);
	            }
	        }
	        
	             query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber_assign + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
	            execute(query);

	            query = "select * from " + Constants.ROOMS_TABLE;

	            result = execute(query);

	            if (result != null) {

	                System.out.println("roomNumber" + " |" + "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
	                System.out.println("---------------------------------------------------------------");

	                while (result.next()) {

	                     String roomNumber = result.getString(Constants.ROOMS_ROOMNUMBER);
	                     hotelId = result.getString(Constants.ROOMS_HOTELID);
	                     category = result.getString(Constants.ROOMS_CATEGORY);
	                     int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                     boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                    System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
	                }
	            }

	      }
	    
	  public void assign_rooms_by_roomnumber() throws SQLException
	  {
		  System.out.println("Enter the roomNumber of Room to check availability: ");
		  String roomNumber =scan.nextLine();
		  System.out.println("Enter the hotelId: ");
		  String hotelId =scan.nextLine();
		  
		  String query = "Select * FROM " + Constants.ROOMS_TABLE +   " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and "  + Constants.ROOMS_ROOMNUMBER + " = '" + roomNumber + "' and " + Constants.ROOMS_AVAILABILITY + " = "+ "1" + " LIMIT 1";
		  ResultSet result = execute(query);
		  if (result != null) {

	            System.out.println("category" + " |" + "hotelId" + " |" + "maxOccupancy" + " |" + "availability");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                String category = result.getString(Constants.ROOMS_CATEGORY);
	                int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                System.out.println(category + " |" + hotelId + " |" + maxOccupancy + " |" + availability);
	            }
		  }
		  System.out.println("Enter startDate : ");
	        String startDate = scan.nextLine();
	        System.out.println("Enter endDate : ");
	        String endDate = scan.nextLine();
	        System.out.println("Enter checkinTime : ");
	        String checkinTime = scan.nextLine();
	        System.out.println("Enter checkoutTime : ");
	        String checkoutTime = scan.nextLine();
	        System.out.println("Enter numberOfGuests : ");
	        String numberOfGuests = scan.nextLine();
	        System.out.println("Enter total : ");
	        String total = scan.nextLine();
	        System.out.println("Enter customerId : ");
	        String customerId = scan.nextLine();
	        System.out.println("Enter paymentId : ");
	        String paymentId = scan.nextLine();
	        
		  query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + ","
	                + Constants.CHECK_INS_ENDDATE + "," + Constants.CHECK_INS_CHECKINTIME + "," + Constants.CHECK_INS_CHECKOUTTIME + ","
	                + Constants.CHECK_INS_NUMBEROFGUESTS + "," + Constants.CHECK_INS_TOTAL + "," + Constants.CHECK_INS_CUSTOMERID
	                + "," + Constants.CHECK_INS_HOTELID + "," + Constants.CHECK_INS_ROOMNUMBER + "," + Constants.CHECK_INS_PAYMENTID + ") values('" + startDate + "','" + endDate + "','" + checkinTime + "','"
	                + checkoutTime + "','" + numberOfGuests + "','" + total + "','" + customerId + "','" + hotelId + "','" + roomNumber + "','" + paymentId + "')";
		  execute(query);
		   query = "select * from " + Constants.CHECK_INS_TABLE;

	        result = execute(query);

	        if (result != null) {

	            System.out.println("checkinId" + " |" + "startDate" + " |" + "endDate" + " |" + "checkinTime" + " |" + "checkoutTIme" + " |" + "numberOfGuests" + " |" + "total" + " |" + "customerId" + " |" + "hotelId" + " |" + "roomNumber" + " |" + "paymentId");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                 int checkinId = result.getInt(Constants.CHECK_INS_CHECKINID);
	                 startDate = result.getString(Constants.CHECK_INS_STARTDATE);
	                 endDate = result.getString(Constants.CHECK_INS_ENDDATE);
	                 checkinTime = result.getString(Constants.CHECK_INS_CHECKINTIME);
	                 checkoutTime = result.getString(Constants.CHECK_INS_CHECKOUTTIME);
	                 numberOfGuests = result.getString(Constants.CHECK_INS_NUMBEROFGUESTS);
	                 String totals = result.getString(Constants.CHECK_INS_TOTAL);
	                 customerId = result.getString(Constants.CHECK_INS_CUSTOMERID);
	                 hotelId = result.getString(Constants.CHECK_INS_HOTELID);
	                 roomNumber = result.getString(Constants.CHECK_INS_ROOMNUMBER);
	                 paymentId = result.getString(Constants.CHECK_INS_PAYMENTID);

	                System.out.println(checkinId + " |" + startDate + " |" + endDate + " |" + checkinTime + " |" + checkoutTime + " |" + numberOfGuests + " |" + totals + " |" + customerId + " |" + hotelId + " |" + roomNumber + " |" + paymentId);
	            }
	        }
	             query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
	            execute(query);

	            query = "select * from " + Constants.ROOMS_TABLE;

	            result = execute(query);

	            if (result != null) {

	                System.out.println("roomNumber" + " |" + "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
	                System.out.println("---------------------------------------------------------------");

	                while (result.next()) {

	                     roomNumber = result.getString(Constants.ROOMS_ROOMNUMBER);
	                     hotelId = result.getString(Constants.ROOMS_HOTELID);
	                     String category = result.getString(Constants.ROOMS_CATEGORY);
	                     int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                     boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                    System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
	                }
	            }
	        }
	  public void release_room() throws SQLException
	  {
	        System.out.println("Enter room number : ");
	        String roomNumber = scan.nextLine();

	        System.out.println("Enter hotel ID : ");
	        String hotelId = scan.nextLine();

	        String query = "Update " + Constants.ROOMS_TABLE +" set "+ Constants.ROOMS_AVAILABILITY + " = 1  where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + Constants.ROOMS_HOTELID + " = " + hotelId;
	        execute(query);
	        query = "select * from " + Constants.ROOMS_TABLE;

	        ResultSet result = execute(query);

	        if (result != null) {

	            System.out.println("roomNumber" + " |" + "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
	            System.out.println("---------------------------------------------------------------");

	            while (result.next()) {

	                roomNumber = result.getString(Constants.ROOMS_ROOMNUMBER);
	                hotelId = result.getString(Constants.ROOMS_HOTELID);
	                String category = result.getString(Constants.ROOMS_CATEGORY);
	                int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
	                boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);

	                System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
	            }
	        }
	  }
	  

}
