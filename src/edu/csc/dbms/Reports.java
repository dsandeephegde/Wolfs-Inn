package edu.csc.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reports {
    private static Scanner scan = new Scanner(System.in);

    public void operations() throws SQLException {
        System.out.println("1. Revenue earned by all hotels");
        System.out.println("2. Revenue earned by a hotel");

        System.out.println();
        System.out.print("Select the Report to be generated :");
        System.out.println();

        int option = Integer.parseInt(scan.nextLine());
        switch (option) {
            case 1:
                getRevenue();
                break;
            case 2:
                getRevenueByHotel();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }
    }

    public static void getRevenue() throws SQLException {
        System.out.println("Enter startDate : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate : ");
        String endDate = scan.nextLine();

        String query = "SELECT hotelId, name, sum(total) AS Revenue FROM " + Constants.CHECK_INS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " WHERE endDate > '" + startDate + "' AND endDate < '" + endDate + "' GROUP BY hotelId";
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println("HotelId" + " |" + "name" + " |" + "Revenue");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                int hotelId = result.getInt(Constants.HOTELS_ID);
                String name = result.getString(Constants.HOTELS_NAME);
                float revenue = result.getFloat("Revenue");

                System.out.println(hotelId + " |" + name + " |" + revenue);
            }
        }
    }

    public static void getRevenueByHotel() throws SQLException {
        System.out.println("Enter hotel ID : ");
        String hotelId = scan.nextLine();
        System.out.println("Enter startDate : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate : ");
        String endDate = scan.nextLine();

        String query = "SELECT hotelId, name, sum(total) AS Revenue FROM " + Constants.CHECK_INS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " WHERE endDate > '" + startDate + "' AND endDate < '" + endDate + "' AND hotelId=" + hotelId;
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println("name" + " |" + "Revenue");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                String name = result.getString(Constants.HOTELS_NAME);
                float revenue = result.getFloat("Revenue");

                System.out.println(name + " |" + revenue);
            }
        }
    }
}
