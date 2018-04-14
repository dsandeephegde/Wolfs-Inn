package edu.csc.dbms;

import entities.Buys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Maintaining_Service_Records {
    protected Scanner scan = new Scanner(System.in);
    private Buys buys = new Buys();
    
    public void operations() throws SQLException {
        System.out.println("1. Add/Update a service availed by the customer.");
        //System.out.println("2. Update prices of services availed");
        System.out.println("2. Delete a service not availed by customer");

        int option = Util.getOption();

        switch (option) {
            case 1:
                add_update_service();
                buys.retrieve();
                break;
                /*
            case 2:
                add_service();
                buys.retrieve();
                break;
                */
            case 2:
                delete_service();
                buys.retrieve();
                break;
            default:
                System.out.println("Please Enter a valid option!");

        }
    }

    public void add_update_service() throws SQLException{
        System.out.println("Enter serviceId: ");
        int serviceId = Integer.parseInt(scan.nextLine());
        System.out.println("Enter checkinId: ");
        int checkinId = Integer.parseInt(scan.nextLine());

        int new_price;

        int price = get_service_price(serviceId);

        int old_price = get_price(serviceId,checkinId);


        if (old_price != 0) {
            new_price = old_price + price;
            String query = "Update " + Constants.BUYS_TABLE + " set " + Constants.BUYS_PRICE + " = '" + new_price + "'" + " where "
                    + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
            DBUtil.executeQuery(query);
        }
        else{
            String query = "Insert into " + Constants.BUYS_TABLE + "(" + Constants.BUYS_SERVICEID + "," + Constants.BUYS_CHECKINID + ","
                    + Constants.BUYS_PRICE + ") values(" + serviceId + "," + checkinId + "," + price + ")";
            DBUtil.executeQuery(query);
        }
    }

    public void delete_service() throws SQLException{
        System.out.println("Enter serviceId: ");
        int serviceId = Integer.parseInt(scan.nextLine());
        System.out.println("Enter checkinId: ");
        int checkinId = Integer.parseInt(scan.nextLine());

        int new_price;

        int price = get_service_price(serviceId);

        int old_price = get_price(serviceId,checkinId);


        if (old_price - price == 0) {
            String query = "Delete from " + Constants.BUYS_TABLE + " where " + Constants.BUYS_SERVICEID + " = "
                    + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
            DBUtil.executeQuery(query);
        }
        else if (old_price - price > 0){
            new_price = old_price - price;
            String query = "Update " + Constants.BUYS_TABLE + " set " + Constants.BUYS_PRICE + " = '" + new_price + "'" + " where "
                    + Constants.BUYS_SERVICEID + " = " + serviceId + " and " + Constants.BUYS_CHECKINID + " = " + checkinId;
            DBUtil.executeQuery(query);
        }
    }

    public int get_service_price(int serviceId) throws SQLException{
        int price = 0;
        String query = "select * from " + Constants.SERVICES_TABLE + " where " + Constants.SERVICES_ID + " = " + serviceId;

        ResultSet result = DBUtil.executeQuery(query);

        if (result != null) {
            while (result.next()) {
                price = result.getInt(Constants.SERVICES_BASE_PRICE);
            }
        }
        return price;
    }

    public int get_price(int serviceId, int checkinId) throws SQLException{
        int old_price = 0;
        String query = "select * from " + Constants.BUYS_TABLE + " where " + Constants.BUYS_CHECKINID + " = " + checkinId + " and "
                + Constants.BUYS_SERVICEID + " = " + serviceId;

        ResultSet result_buys = DBUtil.executeQuery(query);

        if(result_buys != null) {
            while (result_buys.next()) {
                old_price = result_buys.getInt(Constants.BUYS_PRICE);
            }
        }
        return old_price;
    }

}