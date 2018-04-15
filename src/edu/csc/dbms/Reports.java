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
        System.out.println("6. Occupancy by hotel");
        System.out.println("7. Occupancy by city");
        System.out.println("8. Occupancy by category");
        System.out.println("9. Occupancy for a Date Range");
        System.out.println("10. Get Occupancy");

        System.out.println();
        System.out.print("Select the Report to be generated :");
        System.out.println();

        int option = Util.getOption();
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
            case 6:
                getOccupancyBy("Hotel");
                break;
            case 7:
                getOccupancyBy("City");
                break;
            case 8:
                getOccupancyBy("Category");
                break;
            case 9:
                getOccupancyByDateRange();
                break;
            case 10:
                getOccupancies();
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

                System.out.println(staffId + " |" + name + " |" + age + " |" + jobTitle + " |" + phNumber + " |"
                        + address + " |" + city + " |" + state + " |" + country + " |" + hotelId);
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
        System.out.println("Enter startDate (YYYY-MM-DD)  : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
        String endDate = scan.nextLine();

        String query = "SELECT h.hotelId, h.Name, (CASE WHEN temp.hotelId=h.hotelId THEN temp.Revenue ELSE 0 END) as Revenue " +
                "FROM " + Constants.HOTELS_TABLE + " h, (SELECT hotelId, sum(total) AS Revenue FROM " +
                Constants.CHECK_INS_TABLE + " WHERE endDate > '" + startDate + "' AND endDate < '" + endDate + "') temp";
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
        System.out.println("Enter startDate (YYYY-MM-DD) : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) :  ");
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

    private void getOccupancyBy(String criteria) throws SQLException {
        String query;
        switch (criteria) {
            case "Hotel":
                query = "SELECT Name AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY hotelId";
                break;
            case "City":
                query = "SELECT city AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY city";
                break;
            case "Category":
                query = "SELECT category AS " + criteria +
                        ",(COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 AS OccupancyPercentage, " +
                        "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM "
                        + Constants.ROOMS_TABLE + " NATURAL JOIN " + Constants.HOTELS_TABLE + " GROUP BY category";
                break;
            default:
                return;
        }
        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println(criteria + " |" + "Occupancy Percentage" + " |" + "Total Occupancy");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                String name = result.getString(criteria);
                float occupancyPercentage = result.getFloat("OccupancyPercentage");
                int totalOccupancy = result.getInt("TotalOccupancy");

                System.out.println(name + " |" + occupancyPercentage + " |" + totalOccupancy);
            }
        }
    }

    private void getOccupancyByDateRange() throws SQLException {
        System.out.println("Enter startDate (YYYY-MM-DD)  : ");
        String startDate = scan.nextLine();
        System.out.println("Enter endDate (YYYY-MM-DD) : ");
        String endDate = scan.nextLine();

        String query = "SELECT (occupied.counter/room.counter)*100 as OccupancyPercentage, occupied.counter as TotalOccupancy FROM " +
                "(SELECT count(*) as counter FROM Rooms r, Hotels h, Checkins c " +
                "WHERE h.hotelId=r.hotelId and r.roomNumber = c.roomNumber and r.hotelId = c.hotelId and " +
                "((endDate BETWEEN '" + startDate + "' AND '" + endDate + "') or " +
                "(startDate BETWEEN '" + startDate + "' AND '" + endDate + "') or " +
                "(endDate > '" + endDate + "' AND startDate < '" + startDate + "'))) occupied, " +
                "(SELECT count(*) as counter FROM Rooms) room";

        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            System.out.println("Occupancy Percentage" + " |" + "Total Occupancy");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                float occupancyPercentage = result.getFloat("OccupancyPercentage");
                int totalOccupancy = result.getInt("TotalOccupancy");

                System.out.println(occupancyPercentage + " |" + totalOccupancy);
            }
        }
    }

    private void getOccupancies() throws SQLException {
        String query;
        query = "SELECT (COUNT(CASE WHEN availability = false THEN 1 ELSE null END)/COUNT(*))*100 OccupancyPercentage, " +
                "COUNT(CASE WHEN availability = false THEN 1 ELSE null END) AS TotalOccupancy FROM Rooms NATURAL JOIN Hotels";
        String whereClause = "";
        String message = "Occupancy for ";

        System.out.println("Enter hotel ID: ");
        String hotelId = scan.nextLine();
        if (!hotelId.isEmpty()) {
            whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
            whereClause += Constants.HOTELS_ID + " = " + hotelId;
            message += Constants.HOTELS_ID + " " + hotelId + " ";
        }

        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
            whereClause += Constants.HOTELS_CITY + " = '" + city + "'";
            message += Constants.HOTELS_CITY + " " + city + " ";
        }

        System.out.println("Enter Category : ");
        String category = scan.nextLine();

        if (!category.isEmpty()) {
            whereClause += (!whereClause.isEmpty()) ? " AND " : " WHERE ";
            whereClause += Constants.ROOMS_CATEGORY + " = '" + category + "'";
            message += Constants.ROOMS_CATEGORY + " " + category + " ";
        }
        query += whereClause;

        ResultSet result = DBUtil.executeQuery(query);
        if (result != null) {
            if (!message.equals("Occupancy for ")) {
                System.out.println(message);
            } else {
                System.out.println("Occupancy for all the hotels");
            }
            System.out.println();
            System.out.println("Occupancy Percentage" + " |" + "Total Occupancy");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {
                float occupancyPercentage = result.getFloat("OccupancyPercentage");
                int totalOccupancy = result.getInt("TotalOccupancy");
                System.out.println(occupancyPercentage + " |" + totalOccupancy);
            }
        }
    }
}
