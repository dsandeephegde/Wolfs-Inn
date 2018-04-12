package edu.csc.dbms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reports {
    private static Scanner scan = new Scanner(System.in);

    public void operations() throws SQLException {
        System.out.println("1. Staff grouped by their role");
        System.out.println("2. Staff ordered by their role");
        System.out.println("3. Staff served during the customer’s stay");
        System.out.println("4. Revenue earned by all hotels");
        System.out.println("5. Revenue earned by a hotel");

        System.out.println();
        System.out.print("Select the Report to be generated :");
        System.out.println();

        int option = Integer.parseInt(scan.nextLine());
        switch (option) {
            case 1:
                staffRoles();
                break;
            case 2:
                staffs();
                break;
            case 3:
                staffsForCustomerStay();
                break;
            case 4:
                getRevenue();
                break;
            case 5:
                getRevenueByHotel();
                break;
            default:
                System.out.println("Please Enter a valid option ....");
        }
    }

    private void staffRoles() throws SQLException {
        String query = "SELECT jobTitle AS Role, COUNT(*) AS No_of_Employees FROM " + Constants.STAFFS_TABLE + " GROUP BY jobTitle";
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println("Role" + " |" + "No_of_Employees");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                String role = result.getString("Role");
                String numberOfEmployees = result.getString("No_of_Employees");

                System.out.println(role + " |" + numberOfEmployees);
            }
        }
    }

    private void staffs() throws SQLException {
        String query = "SELECT * FROM " + Constants.STAFFS_TABLE + " ORDER BY jobTitle";
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {

            System.out.println("StaffId" + " |" + "name" + " |" + "age" + " |" + "jobTitle" + " |" + "phoneNumber"
                    + " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "hotelId");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int staffId = result.getInt(Constants.STAFFS_ID);
                String name = result.getString(Constants.STAFFS_NAME);
                int age = result.getInt(Constants.STAFFS_AGE);
                String jobTitle = result.getString(Constants.STAFFS_JOB_TITLE);
                String phNumber = result.getString(Constants.STAFFS_PH_NUMBER);
                String address = result.getString(Constants.STAFFS_ADDRESS);
                String city = result.getString(Constants.STAFFS_CITY);
                String state = result.getString(Constants.STAFFS_STATE);
                String country = result.getString(Constants.STAFFS_COUNTRY);
                int hotelId = result.getInt(Constants.STAFFS_HOTEL_ID);

                System.out.println(staffId + " |" + name + " |" + age + " |" + jobTitle + " |" + phNumber + " |" + address + " |" + city + " |" + state + " |" + country + " |" + hotelId);
            }
        }
    }

    private void staffsForCustomerStay() throws SQLException {
        System.out.println("Enter checkin ID: ");
        String checkinId = scan.nextLine();
        String query = "SELECT staffId, name FROM " + Constants.SERVES_TABLE + " NATURAL JOIN " + Constants.STAFFS_TABLE + " WHERE checkinId =" + checkinId;
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println("staffId" + " |" + "name");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                String staffId = result.getString("staffId");
                String name = result.getString("name");

                System.out.println(staffId + " |" + name);
            }
        }
    }

    private void getRevenue() throws SQLException {
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

    private void getRevenueByHotel() throws SQLException {
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
