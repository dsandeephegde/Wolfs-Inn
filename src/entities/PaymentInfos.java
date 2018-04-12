package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;

public class PaymentInfos implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.PAYMENT_INFOS_TABLE;

        ResultSet result = execute(query);

        if (result != null) {

            System.out.println("PaymentId" + " |" + "SSN" + " |" + "billingAddress" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "paymentMethod" + " |" + "cardNumber" + " |" + "customerId");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int paymentId = result.getInt(Constants.PAYMENT_INFOS_ID);
                String ssn = result.getString(Constants.PAYMENT_INFOS_SSN);
                String address = result.getString(Constants.PAYMENT_INFOS_BILLING_ADDRESS);
                String city = result.getString(Constants.PAYMENT_INFOS_CITY);
                String state = result.getString(Constants.PAYMENT_INFOS_STATE);
                String country = result.getString(Constants.PAYMENT_INFOS_COUNTRY);
                String paymentMethod = result.getString(Constants.PAYMENT_INFOS_PAYMENT_METHOD);
                String cardNum = result.getString(Constants.PAYMENT_INFOS_CARD_NUM);
                int customerId = result.getInt(Constants.PAYMENT_INFOS_CUSTOMER_ID);

                System.out.println(paymentId + " |" + ssn + " |" + address + " |" + city + " |" + state + " |" + country + " |" + paymentMethod + " |" + cardNum + " |" + customerId);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

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
        execute(query);

    }

    @Override
    public void update(Scanner scan) throws SQLException {

        System.out.println("Enter payment ID to update : ");
        String paymentId = scan.nextLine();

        String updateString = "";

        System.out.println("Enter only update values");
        System.out.println("Enter payer SSN : ");
        String ssn = scan.nextLine();

        if (!ssn.isEmpty())
            updateString += Constants.PAYMENT_INFOS_SSN + " = '" + ssn + "'";

        System.out.println("Enter line 1 billing address : ");
        String address = scan.nextLine();

        if (!address.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_BILLING_ADDRESS + " = '" + address + "'";
        }


        System.out.println("Enter City : ");
        String city = scan.nextLine();

        if (!city.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CITY + " = '" + city + "'";
        }

        System.out.println("Enter State : ");
        String state = scan.nextLine();

        if (!state.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_STATE + " = '" + state + "'";
        }

        System.out.println("Enter Country : ");
        String country = scan.nextLine();

        if (!country.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_COUNTRY + " = '" + country + "'";
        }

        System.out.println("Enter payment method : ");
        String paymentMethod = scan.nextLine();

        if (!paymentMethod.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_PAYMENT_METHOD + " = '" + paymentMethod + "'";
        }

        System.out.println("Enter card number : ");
        String cardNum = scan.nextLine();

        if (!cardNum.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CARD_NUM + " = '" + cardNum + "'";
        }

        System.out.println("Enter Customer ID : ");
        String customerId = scan.nextLine();

        if (!customerId.isEmpty()) {
            updateString += (!updateString.isEmpty()) ? " , " : "";
            updateString += Constants.PAYMENT_INFOS_CUSTOMER_ID + " = " + customerId + "";
        }

        String query = "Update " + Constants.PAYMENT_INFOS_TABLE + " set " + updateString + " where " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
        execute(query);

    }

    @Override
    public void delete(Scanner scan) throws SQLException {

        System.out.println("Enter payment ID : ");
        String paymentId = scan.nextLine();

        String query = "Delete from " + Constants.PAYMENT_INFOS_TABLE + " where " + Constants.PAYMENT_INFOS_ID + " = " + paymentId;
        execute(query);

    }

}
