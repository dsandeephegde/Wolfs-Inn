package edu.csc.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Maintaining_Billing_Accounts {

	private static Scanner scan = new Scanner(System.in);
	
	public void operations() throws SQLException{
		
		System.out.println("1. Billing total");
		System.out.println("2. Itemized reciept");
        System.out.println("3. Billing total with itemized reciept");

        System.out.println();
        System.out.print("Select the type of billing to be generated :");

        int option = Integer.parseInt(scan.nextLine());
        
        System.out.println("Enter the checkIn ID :");
		String checkinId = scan.nextLine();
        
        switch (option) {
            case 1:
                getTotalPrice(checkinId);
                break;
            case 2:
                getItemizedRecipt(checkinId);
                break;
            case 3:
            	getTotalPrice(checkinId);
            	System.out.println();
                getItemizedRecipt(checkinId);
                break;    
            default:
                System.out.println("Please Enter a valid option ....");
        }
		
	}

	private void getTotalPrice(String checkinId) throws SQLException{
		
		String query = "SELECT (RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , " + Constants.CHECK_INS_STARTDATE + ") + SUM(B." + Constants.BUYS_PRICE + ")) * (CASE WHEN P." + Constants.PAYMENT_INFOS_PAYMENT_METHOD + " <> '" + 
						Constants.PAY_METHOD_HOTEL_CARD + "' THEN 1 ELSE 0.95 end) AS " + Constants.TOTAL_PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " + 
						Constants.PAYMENT_INFOS_TABLE + " P WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER + " AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY + 
						" = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." + Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId + " AND B." + Constants.BUYS_CHECKINID + " = C." +
						Constants.CHECK_INS_CHECKINID + " AND P." + Constants.PAYMENT_INFOS_ID + " = C." + Constants.CHECK_INS_PAYMENTID;
		
		ResultSet result = DBUtil.executeQuery(query);
		
		if(result.next()) {
			int totalPrice = result.getInt(Constants.TOTAL_PRICE);
			
			System.out.println(Constants.TOTAL_PRICE);
			System.out.println(totalPrice + "$");
		}
	}
	
	private void getItemizedRecipt(String checkinId) throws SQLException{
		
		String query = "SELECT S." + Constants.SERVICES_NAME + " AS " + Constants.DESCRIPTION + " , S." + Constants.SERVICES_BASE_PRICE + " , cast( B." + Constants.BUYS_PRICE + " / S." + Constants.SERVICES_BASE_PRICE +
				" AS INTEGER) AS " + Constants.NUMBER + " , B." + Constants.BUYS_PRICE + " FROM " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " + Constants.SERVICES_TABLE + " S WHERE C." +
				Constants.CHECK_INS_CHECKINID + " = B." + Constants.BUYS_CHECKINID + " AND B." + Constants.BUYS_SERVICEID + " = S." + Constants.SERVICES_ID + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId + 
				" UNION " +
				"SELECT '" + Constants.DESCRIPTION_VALUE + "' AS " + Constants.DESCRIPTION + " , RP." + Constants.ROOM_PRICES_PRICE + " AS " + Constants.BASE_PRICE + " , DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , C." + 
				Constants.CHECK_INS_STARTDATE + ") AS " + Constants.NUMBER + " , RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C."+ Constants.CHECK_INS_ENDDATE + " , C." + Constants.CHECK_INS_STARTDATE + ") AS " + 
				Constants.PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER + 
				" AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY + " = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." + 
				Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId ;
		
		ResultSet result = DBUtil.executeQuery(query);
		
		System.out.println("Itemized recipt");
		System.out.println(Constants.DESCRIPTION + " |" + Constants.BASE_PRICE + " |" + Constants.NUMBER + " |" + Constants.PRICE);
		System.out.println();
		
		while(result.next()) {
			
			String description = result.getString(Constants.DESCRIPTION);
			int basePrice = result.getInt(Constants.BASE_PRICE);
			int number = result.getInt(Constants.NUMBER);
			int price = result.getInt(Constants.PRICE);
			
			System.out.println(description + " |" + basePrice + " |" + number + " |" + price);
		}
	}


}
