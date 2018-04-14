package edu.csc.dbms;

import entities.CheckIns;
import entities.Rooms;

import java.sql.*;
import java.util.Scanner;

public class Info_Processing {
    private Scanner scan = new Scanner(System.in);

    public void info_processing() throws SQLException {
        System.out.println("1. Check Number of Rooms available based on room type");
        System.out.println("2. List all the Rooms available based on room type");
        System.out.println("3. Assign rooms based on request and availability and type of room requested, if type of room is requested");
        System.out.println("4. Assign rooms based on request and availability and type of room requested, if the Room Number is provided");
        System.out.println("5. Release Room");

        System.out.println();
        System.out.print("Select the information to be processed :");
        System.out.println();

        Rooms rooms = new Rooms();
        CheckIns checkIns = new CheckIns();

        int option = Util.getOption();
        
        switch (option) {
            case 1:
                check_available_rooms();
                break;
            case 2:
                rooms_list_by_roomtype();
                break;
            case 3:
                assign_rooms_by_type();
                System.out.println();
                checkIns.retrieve();
                System.out.println();
                rooms.retrieve();
                break;
            case 4:
                assign_rooms_by_roomnumber();
                System.out.println();
                checkIns.retrieve();
                System.out.println();
                rooms.retrieve();
                break;
            case 5:
                release_room();
                rooms.retrieve();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }
    }

    public void check_available_rooms() throws SQLException {
    	printRoomCategories();
        System.out.println("Enter the category of Room to check availability: ");
        String category = scan.nextLine();

        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();
        String query = "Select COUNT(*) as No_of_Rooms_Available FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and " + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = " + "1";
        ResultSet result = DBUtil.executeQuery(query);

        if (result != null) {
            System.out.println("No_of_Rooms_Available");
            System.out.println("---------------------------------------------------------------");
            while (result.next()) {
                System.out.println(result.getInt("No_of_Rooms_Available"));
            }
        }
    }

    public void rooms_list_by_roomtype() throws SQLException {
    	
    	printRoomCategories();
        System.out.println("Enter the category of Room to check availability: ");
        String category = scan.nextLine();

        String query = "Select * FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = " + "1";
        ResultSet result = DBUtil.executeQuery(query);

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

    public void assign_rooms_by_type() throws SQLException {
    	
    	//Connection with auto commit-false
        Connection conn = DBUtil.getConnection();
        Statement stm = conn.createStatement();
    	
    	try {
    		String roomNumber_assign = null;
    		printRoomCategories();
            System.out.println("Enter the category of Room to check availability: ");
            String category = scan.nextLine();
            System.out.println("Enter the hotelId: ");
            String hotelId = scan.nextLine();

            String query = "Select * FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and " + Constants.ROOMS_CATEGORY + " = '" + category + "' and " + Constants.ROOMS_AVAILABILITY + " = " + " 1 " + " LIMIT 1";
            
            ResultSet result = stm.executeQuery(query);
            
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
            if(roomNumber_assign != null) {
            	createCheckin(conn, roomNumber_assign, hotelId);
            }else {
            	throw new SQLException("Rooms unaivailable \n");
            }
            

            query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber_assign + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
            
            stm.executeQuery(query);
            
            //commit transaction
            conn.commit();
            
    	}catch(SQLException se) {
    		conn.rollback();
    		System.out.println("Transaction Rollback - " + se.getMessage());
    	}catch(Exception e) {
    		conn.rollback();
    		System.out.println("Transaction Rollback - " + e.getMessage());
    	}
        
    }

    private void createCheckin(Connection conn, String roomNumber, String hotelId) throws SQLException {
        String query;
        System.out.println("Enter startDate (YYYY-MM-DD)  : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
        String endDate = scan.nextLine();
        System.out.println("Enter checkinTime (HH:MM): ");
        String checkinTime = scan.nextLine();
        System.out.println("Enter numberOfGuests : ");
        String numberOfGuests = scan.nextLine();
        System.out.println("Enter customerId : ");
        String customerId = scan.nextLine();
        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();

        query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + "," + Constants.CHECK_INS_ENDDATE + ","
                + Constants.CHECK_INS_CHECKINTIME + "," + Constants.CHECK_INS_NUMBEROFGUESTS + "," + Constants.CHECK_INS_CUSTOMERID
                + "," + Constants.CHECK_INS_HOTELID + "," + Constants.CHECK_INS_ROOMNUMBER + "," + Constants.CHECK_INS_PAYMENTID + ") "
                + "values('" + startDate + "','" + endDate + "','" + checkinTime + "'," + numberOfGuests + "," + customerId + ","
                + hotelId + "," + roomNumber + "," + paymentId + ")";
        
        Statement stm = conn.createStatement();
        stm.executeQuery(query);
    }

    public void assign_rooms_by_roomnumber() throws SQLException {
    	
    	//Connection with auto commit-false
        Connection conn = DBUtil.getConnection();
        Statement stm = conn.createStatement();
    	
    	try {
    		
    		System.out.println("Enter the roomNumber of Room to check availability: ");
            String roomNumber = scan.nextLine();
            System.out.println("Enter the hotelId: ");
            String hotelId = scan.nextLine();

            String query = "Select * FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and " + Constants.ROOMS_ROOMNUMBER + " = '" + roomNumber + "' and " + Constants.ROOMS_AVAILABILITY + " = " + "1" + " LIMIT 1";

            ResultSet result = stm.executeQuery(query);
            boolean flag = false;
            if (result != null) {

                System.out.println("category" + " |" + "hotelId" + " |" + "maxOccupancy" + " |" + "availability");
                System.out.println("---------------------------------------------------------------");
                
                while (result.next()) {

                    String category = result.getString(Constants.ROOMS_CATEGORY);
                    int maxOccupancy = result.getInt(Constants.ROOMS_MAXOCCUPANCY);
                    boolean availability = result.getBoolean(Constants.ROOMS_AVAILABILITY);
                    flag = availability;

                    System.out.println(category + " |" + hotelId + " |" + maxOccupancy + " |" + availability);
                }
            }
            
            if(flag) {
            	createCheckin(conn, roomNumber, hotelId);
            }else {
            	throw new SQLException("Rooms unaivailable \n");
            }
            

            query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
            stm.executeQuery(query);
            
          //commit transaction
            conn.commit();
    		
    	}catch(SQLException se) {
    		conn.rollback();
    		System.out.println("Transaction Rollback - " + se.getMessage());
    	}catch(Exception e) {
    		conn.rollback();
    		System.out.println("Transaction Rollback - " + e.getMessage());
    	}
        
    }

    public void release_room() throws SQLException {
        System.out.println("Enter room number : ");
        String roomNumber = scan.nextLine();

        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();

        String query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " = 1  where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + Constants.ROOMS_HOTELID + " = " + hotelId;
        DBUtil.executeQuery(query);
    }
    
    public void printRoomCategories() throws SQLException{
    	
    	String query = "Select distinct " + Constants.ROOMS_CATEGORY + " from " + Constants.ROOMS_TABLE;
    	
    	ResultSet result = DBUtil.executeQuery(query);
    	
    	System.out.print("Available Categories : ");
    	
    	while(result.next()) {
    		
    		System.out.print(result.getString(Constants.ROOMS_CATEGORY) + " |");
    		
    	}
    	System.out.println();
    }
}
