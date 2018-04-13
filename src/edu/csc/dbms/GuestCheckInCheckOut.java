package edu.csc.dbms;

import java.sql.*;

import java.util.Scanner;

public class GuestCheckInCheckOut {

	private static Scanner scan = new Scanner(System.in);
	
	public void operations() throws SQLException {

        System.out.println("1. Check-In");
        System.out.println("2. Check-Out");

        System.out.println();
        System.out.print("Select the operation :");

        int option = Integer.parseInt(scan.nextLine());

        System.out.println("Enter the checkIn ID :");
        String checkinId = scan.nextLine();

        switch (option) {
            case 1:
                guestCheckIn(checkinId);
                break;
            case 2:
                guestCheckOut(checkinId);
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }

    }

	private void guestCheckIn(String checkinId) throws SQLException {
		
		//GetConnection
		//RoomSelection
		//CheckIn
		//PaymentInfo
		//Commit/Roll back 
		Connection conn = DBUtil.getConnection();
		try {
			
			System.out.println("Enter the category of Room to check availability: ");
	        String category = scan.nextLine();

	        System.out.println("Enter hotelId : ");
	        String hotelId = scan.nextLine();
			
			check_available_rooms(hotelId, category);
			
			System.out.println("Enter the roomNumber of Room to check availability and book: ");
	        String roomNumber = scan.nextLine();
			
			assign_rooms_by_roomnumber(conn, hotelId, roomNumber);
			enterPaymentInfo(conn);
			checkInDetails(conn, hotelId, roomNumber);
			
		}catch (SQLException se) {
			conn.rollback();
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
        	conn.rollback();
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }
		
		conn.commit();
		
	}
	
	
	private void guestCheckOut(String checkinId) throws SQLException {
		
		//GetConnection
		//ReleaseRoom
		//GenerateTotal
		//GenerateItemizedRecipt
		//UpdateTotal
		//Commit/Roll back
		
	}
	
	public void check_available_rooms(String hotelId, String category) throws SQLException {
        
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
	
	public void assign_rooms_by_roomnumber(Connection conn, String hotelId, String roomNumber) throws SQLException {

        String query = "Select * FROM " + Constants.ROOMS_TABLE + " where " + Constants.ROOMS_HOTELID + " = " + hotelId + " and " + Constants.ROOMS_ROOMNUMBER + " = '" + roomNumber + "' and " + Constants.ROOMS_AVAILABILITY + " = " + "1" + " LIMIT 1";
        
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery(query);
        
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

		query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where "
				+ Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
		
		stm = conn.createStatement();
        stm.executeQuery(query);
        
    }
	
	private void checkInDetails(Connection conn, String hotelId, String roomNumber) throws SQLException {
		
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

        String query = "Insert into " + Constants.CHECK_INS_TABLE + "(" + Constants.CHECK_INS_STARTDATE + ","
                + Constants.CHECK_INS_ENDDATE + "," + Constants.CHECK_INS_CHECKINTIME + "," + Constants.CHECK_INS_CHECKOUTTIME + ","
                + Constants.CHECK_INS_NUMBEROFGUESTS + "," + Constants.CHECK_INS_TOTAL + "," + Constants.CHECK_INS_CUSTOMERID
                + "," + Constants.CHECK_INS_HOTELID + "," + Constants.CHECK_INS_ROOMNUMBER + "," + Constants.CHECK_INS_PAYMENTID + ") values('" + startDate + "','" + endDate + "','" + checkinTime + "','"
                + checkoutTime + "','" + numberOfGuests + "','" + total + "','" + customerId + "','" + hotelId + "','" + roomNumber + "','" + paymentId + "')";
        
        Statement stm = conn.createStatement();
        stm.executeQuery(query);
        
        //modify this to get the latest record only using count and order by
        query = "select * from " + Constants.CHECK_INS_TABLE + " order by checkinId desc limit 1";

        ResultSet result = stm.executeQuery(query);

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
        //query = "Update " + Constants.ROOMS_TABLE + " set " + Constants.ROOMS_AVAILABILITY + " =  0 " + " where " + Constants.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + Constants.ROOMS_HOTELID + " = " + hotelId;
		
	}
	
	private void enterPaymentInfo(Connection conn) throws SQLException{
		
		System.out.println("Enter payer SSN : ");
        String ssn = scan.nextLine();
        System.out.println("Enter line 1 billing address : ");
        String address = scan.nextLine();
        System.out.println("Enter City : ");
        String city = scan.nextLine();
        System.out.println("Enter State : ");
        String state = scan.nextLine();
        System.out.println("Enter Country : ");
        String country = scan.nextLine();
        System.out.println("Enter payment method : ");
        String paymentMethod = scan.nextLine();
        System.out.println("Enter card number : ");
        String cardNum = scan.nextLine();
        System.out.println("Enter customer Id : ");
        String customerId = scan.nextLine();


        String query = "Insert into " + Constants.PAYMENT_INFOS_TABLE + "(" + Constants.PAYMENT_INFOS_SSN + ","
                + Constants.PAYMENT_INFOS_BILLING_ADDRESS + "," + Constants.PAYMENT_INFOS_CITY + "," + Constants.PAYMENT_INFOS_STATE + ","
                + Constants.PAYMENT_INFOS_COUNTRY + "," + Constants.PAYMENT_INFOS_PAYMENT_METHOD + "," + Constants.PAYMENT_INFOS_CARD_NUM
                + "," + Constants.PAYMENT_INFOS_CUSTOMER_ID + ") values('" + ssn + "','" + address + "','" + city + "','"
                + state + "','" + country + "','" + paymentMethod + "','" + cardNum + "'," + customerId + ")";
        
        Statement stm = conn.createStatement();
        stm.executeQuery(query);
	}
	
}
