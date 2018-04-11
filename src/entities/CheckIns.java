package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class CheckIns implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + NS.CHECK_INS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("checkinId" + " |" + "startDate" + " |" + "endDate" + " |" + "checkinTime" + " |" + "checkoutTIme" + " |" + "numberOfGuests" + " |" + "total" + " |" + "customerId" + " |" + "hotelId" + " |" + "roomNumber" + " |" + "paymentId");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int checkinId = result.getInt(NS.CHECK_INS_CHECKINID);
                String startDate = result.getString(NS.CHECK_INS_STARTDATE);
                String endDate = result.getString(NS.CHECK_INS_ENDDATE);
                String checkinTime = result.getString(NS.CHECK_INS_CHECKINTIME);
                String checkoutTime = result.getString(NS.CHECK_INS_CHECKOUTTIME);
                int numberOfGuests = result.getInt(NS.CHECK_INS_NUMBEROFGUESTS);
                int total = result.getInt(NS.CHECK_INS_TOTAL);
                int customerId = result.getInt(NS.CHECK_INS_CUSTOMERID);
                int hotelId = result.getInt(NS.CHECK_INS_HOTELID);
                int roomNumber = result.getInt(NS.CHECK_INS_ROOMNUMBER);
                int paymentId = result.getInt(NS.CHECK_INS_PAYMENTID);

                System.out.println(checkinId + " |" + startDate + " |" + endDate + " |" + checkinTime + " |" + checkoutTime + " |" + numberOfGuests + " |" + total + " |" + customerId + " |" + hotelId + " |" + roomNumber + " |" + paymentId);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

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
        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();
        System.out.println("Enter roomNumber : ");
        String roomNumber = scan.nextLine();
        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();


        String query = "Insert into " + NS.CHECK_INS_TABLE + "(" + NS.CHECK_INS_STARTDATE + ","
                + NS.CHECK_INS_ENDDATE + "," + NS.CHECK_INS_CHECKINTIME + "," + NS.CHECK_INS_CHECKOUTTIME + ","
                + NS.CHECK_INS_NUMBEROFGUESTS + "," + NS.CHECK_INS_TOTAL + "," + NS.CHECK_INS_CUSTOMERID
                + "," + NS.CHECK_INS_HOTELID + "," + NS.CHECK_INS_ROOMNUMBER + "," + NS.CHECK_INS_PAYMENTID + ") values('" + startDate + "','" + endDate + "','" + checkinTime + "','"
                + checkoutTime + "','" + numberOfGuests + "','" + total + "','" + customerId + "','" + hotelId + "','" + roomNumber + "','" + paymentId + "')";
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter checkin ID to update : ");
        String checkinId = scan.nextLine();

        String updateString = new String();

        System.out.println("Enter only update values");
        System.out.println("Enter startDate : ");
        String startDate = scan.nextLine();

        if (!startDate.isEmpty())
            updateString += NS.CHECK_INS_STARTDATE + " = '" + startDate + "'";

        System.out.println("Enter endDate : ");
        String endDate = scan.nextLine();

        if (!endDate.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_ENDDATE + " = '" + endDate + "'";
        }


        System.out.println("Enter checkinTime : ");
        String checkinTime = scan.nextLine();

        if (!checkinTime.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_CHECKINTIME + " = '" + checkinTime + "'";
        }

        System.out.println("Enter checkoutTime : ");
        String checkoutTime = scan.nextLine();

        if (!checkoutTime.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_CHECKOUTTIME + " = '" + checkoutTime + "'";
        }

        System.out.println("Enter numberOfGuests : ");
        String numberOfGuests = scan.nextLine();

        if (!numberOfGuests.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_NUMBEROFGUESTS + " = '" + numberOfGuests + "'";
        }

        System.out.println("Enter total : ");
        String total = scan.nextLine();

        if (!total.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_TOTAL + " = '" + total + "'";
        }

        System.out.println("Enter customerId : ");
        String customerId = scan.nextLine();

        if (!customerId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_CUSTOMERID + " = '" + customerId + "'";
        }

        System.out.println("Enter hotelId : ");
        String hotelId = scan.nextLine();

        if (!hotelId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_HOTELID + " = " + hotelId + "";
        }

        System.out.println("Enter roomNumber : ");
        String roomNumber = scan.nextLine();

        if (!roomNumber.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_ROOMNUMBER + " = '" + roomNumber + "'";
        }

        System.out.println("Enter paymentId : ");
        String paymentId = scan.nextLine();

        if (!paymentId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += NS.CHECK_INS_PAYMENTID + " = " + paymentId + "";
        }

        String query = "Update " + NS.CHECK_INS_TABLE + " set " + updateString + " where " + NS.CHECK_INS_CHECKINID + " = " + checkinId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter checkin Id : ");
        String checkinId = scan.nextLine();

        String query = "Delete from " + NS.CHECK_INS_TABLE + " where " + NS.CHECK_INS_CHECKINID + " = " + checkinId;
        execute(query);

    }

}
