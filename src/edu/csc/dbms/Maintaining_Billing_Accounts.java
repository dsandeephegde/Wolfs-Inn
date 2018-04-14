package edu.csc.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class Maintaining_Billing_Accounts {

    private static Scanner scan = new Scanner(System.in);

    public void operations() throws SQLException {

        System.out.println("1. Billing total");
        System.out.println("2. Itemized receipt");
        System.out.println("3. Billing total with itemized receipt");

        System.out.println();
        System.out.print("Select the type of billing to be generated :");

        int option = Util.getOption();
        // TODO CheckinId is asked even if wrong option is entered

        System.out.println("Enter the checkIn ID :");
        String checkinId = scan.nextLine();

        switch (option) {
            case 1:
                getTotalPrice(checkinId);
                break;
            case 2:
                getItemizedReceipt(checkinId);
                break;
            case 3:
                getTotalPrice(checkinId);
                System.out.println();
                getItemizedReceipt(checkinId);
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }

    }

    private void getTotalPrice(String checkinId) throws SQLException {
        
        String query = "SELECT SUM( temp." + Constants.PRICE + " )*( CASE WHEN P." + Constants.PAYMENT_INFOS_PAYMENT_METHOD + " <> '" + Constants.PAY_METHOD_HOTEL_CREDIT + "' THEN 1 else 0.95 end) as " + Constants.TOTAL_PRICE + " FROM (SELECT B." + Constants.BUYS_PRICE + " as " + Constants.PRICE + 
        		" ,C." + Constants.CHECK_INS_CHECKINID + " FROM " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B," + Constants.SERVICES_TABLE + " S WHERE C." + Constants.CHECK_INS_CHECKINID + " = B." + Constants.BUYS_CHECKINID + " AND B." + Constants.BUYS_SERVICEID + 
        		" = S." + Constants.SERVICES_ID + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId +
        		" UNION " +
        		"SELECT RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF( C." + Constants.CHECK_INS_ENDDATE + " , C." +Constants.CHECK_INS_STARTDATE + " ) as " + Constants.PRICE + " , C." + Constants.CHECK_INS_CHECKINID + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " +
        		Constants.CHECK_INS_TABLE + " C WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER + " AND C." + Constants.HOTELS_ID + " = R." + Constants.ROOMS_HOTELID + " AND R." + Constants.ROOMS_MAXOCCUPANCY + " = RP." + Constants.ROOMS_MAXOCCUPANCY + " AND R." + 
        		Constants.ROOMS_CATEGORY + " = RP." + Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId + " ) temp, " + Constants.CHECK_INS_TABLE + " C, " + Constants.PAYMENT_INFOS_TABLE + " P WHERE P." + Constants.PAYMENT_INFOS_ID + " = C." + Constants.CHECK_INS_PAYMENTID + 
        		" AND C." + Constants.CHECK_INS_CHECKINID + " = temp." + Constants.CHECK_INS_CHECKINID;  
         
        ResultSet result = DBUtil.executeQuery(query);

        if (result.next()) {
            int totalPrice = result.getInt(Constants.TOTAL_PRICE);

            System.out.println(Constants.TOTAL_PRICE);
            System.out.println("$" + totalPrice);
        }
    }

    private void getItemizedReceipt(String checkinId) throws SQLException {

        String query = "SELECT S." + Constants.SERVICES_NAME + " AS " + Constants.DESCRIPTION + " , S." + Constants.SERVICES_BASE_PRICE + " , cast( B." + Constants.BUYS_PRICE + " / S." + Constants.SERVICES_BASE_PRICE +
                " AS INTEGER) AS " + Constants.NUMBER + " , B." + Constants.BUYS_PRICE + " FROM " + Constants.CHECK_INS_TABLE + " C, " + Constants.BUYS_TABLE + " B, " + Constants.SERVICES_TABLE + " S WHERE C." +
                Constants.CHECK_INS_CHECKINID + " = B." + Constants.BUYS_CHECKINID + " AND B." + Constants.BUYS_SERVICEID + " = S." + Constants.SERVICES_ID + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId +
                " UNION " +
                "SELECT '" + Constants.DESCRIPTION_VALUE + "' AS " + Constants.DESCRIPTION + " , RP." + Constants.ROOM_PRICES_PRICE + " AS " + Constants.BASE_PRICE + " , DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , C." +
                Constants.CHECK_INS_STARTDATE + ") AS " + Constants.NUMBER + " , RP." + Constants.ROOM_PRICES_PRICE + "* DATEDIFF(C." + Constants.CHECK_INS_ENDDATE + " , C." + Constants.CHECK_INS_STARTDATE + ") AS " +
                Constants.PRICE + " FROM " + Constants.ROOMS_TABLE + " R, " + Constants.ROOM_PRICES_TABLE + " RP, " + Constants.CHECK_INS_TABLE + " C WHERE C." + Constants.CHECK_INS_ROOMNUMBER + " = R." + Constants.ROOMS_ROOMNUMBER +
                " AND C." + Constants.CHECK_INS_HOTELID + " = R." + Constants.HOTELS_ID + " AND R." + Constants.ROOMS_MAXOCCUPANCY + " = RP." + Constants.ROOM_PRICES_MAXOCCUPANCY + " AND R." + Constants.ROOMS_CATEGORY + " = RP." +
                Constants.ROOM_PRICES_CATEGORY + " AND C." + Constants.CHECK_INS_CHECKINID + " = " + checkinId;

        ResultSet result = DBUtil.executeQuery(query);

        System.out.println("Itemized recipt");
        System.out.println(Constants.DESCRIPTION + " |" + Constants.BASE_PRICE + " |" + Constants.NUMBER + " |" + Constants.PRICE);
        System.out.println();

        while (result.next()) {

            String description = result.getString(Constants.DESCRIPTION);
            int basePrice = result.getInt(Constants.BASE_PRICE);
            int number = result.getInt(Constants.NUMBER);
            int price = result.getInt(Constants.PRICE);

            System.out.println(description + " |" + basePrice + " |" + number + " |" + price);
        }
    }


}
