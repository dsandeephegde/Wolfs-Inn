package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class PaymentInfos implements CRUD {

	@Override
	public void retrieve() throws SQLException {
		
		String query = "select * from " + NS.PAYMENT_INFOS_TABLE;
		
		ResultSet result = execute(query);
		
		if(result != null) {
			
			System.out.println("PaymentId" + " |"+ "SSN" + " |" + "billingAddress" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "paymentMethod" + " |" + "cardNumber" + " |" + "customerId");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int paymentId = result.getInt(NS.PAYMENT_INFOS_ID);
				String ssn = result.getString(NS.PAYMENT_INFOS_SSN);
                String address = result.getString(NS.PAYMENT_INFOS_BILLING_ADDRESS);
                String city = result.getString(NS.PAYMENT_INFOS_CITY);
                String state = result.getString(NS.PAYMENT_INFOS_STATE);
                String country = result.getString(NS.PAYMENT_INFOS_COUNTRY);
                String paymentMethod = result.getString(NS.PAYMENT_INFOS_PAYMENT_METHOD);
                String cardNum = result.getString(NS.PAYMENT_INFOS_CARD_NUM);
                int customerId = result.getInt(NS.PAYMENT_INFOS_CUSTOMER_ID);
                		
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
		
		
		String query = "Insert into " + NS.PAYMENT_INFOS_TABLE + "(" + NS.PAYMENT_INFOS_SSN + ","
				+ NS.PAYMENT_INFOS_BILLING_ADDRESS + "," + NS.PAYMENT_INFOS_CITY + "," + NS.PAYMENT_INFOS_STATE + ","
				+ NS.PAYMENT_INFOS_COUNTRY + "," + NS.PAYMENT_INFOS_PAYMENT_METHOD + "," + NS.PAYMENT_INFOS_CARD_NUM
				+ "," + NS.PAYMENT_INFOS_CUSTOMER_ID + ") values('" + ssn + "','" + address + "','" + city + "','"
				+ state + "','" + country + "','" + paymentMethod + "','" + cardNum + "'," + customerId + ")";
		execute(query);

	}

	@Override
	public void update(Scanner scan) throws SQLException {
		
		System.out.println("Enter payment ID to update : ");
		String paymentId = scan.nextLine();
		
		String updateString = new String();
		
		System.out.println("Enter only update values");
		System.out.println("Enter payer SSN : ");
		String ssn = scan.nextLine();
		
		if(!ssn.isEmpty())
			updateString += NS.PAYMENT_INFOS_SSN + " = '" +  ssn + "'";
		
		System.out.println("Enter line 1 billing address : ");
		String address = scan.nextLine();
		
		if(!address.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_BILLING_ADDRESS + " = '" +  address + "'";
		}
			
		
		System.out.println("Enter City : ");
		String city = scan.nextLine();
		
		if(!city.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_CITY + " = '" +  city + "'";
		}
		
		System.out.println("Enter State : ");
		String state = scan.nextLine();
		
		if(!state.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_STATE + " = '" +  state + "'";
		}
		
		System.out.println("Enter Country : ");
		String country = scan.nextLine();
		
		if(!country.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_COUNTRY + " = '" +  country + "'";
		}
		
		System.out.println("Enter payment method : ");
		String paymentMethod = scan.nextLine();
		
		if(!paymentMethod.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_PAYMENT_METHOD + " = '" +  paymentMethod + "'";
		}
		
		System.out.println("Enter card number : ");
		String cardNum = scan.nextLine();
		
		if(!cardNum.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_CARD_NUM + " = '" +  cardNum + "'";
		}
		
		System.out.println("Enter Customer ID : ");
		String customerId = scan.nextLine();
		
		if(!customerId.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.PAYMENT_INFOS_CUSTOMER_ID + " = " +  customerId + "";
		}
		
		String query = "Update " + NS.PAYMENT_INFOS_TABLE + " set " + updateString + " where " + NS.PAYMENT_INFOS_ID + " = " + paymentId;
		execute(query);

	}

	@Override
	public void delete(Scanner scan) throws SQLException {
		
		System.out.println("Enter payment ID : ");
		String paymentId = scan.nextLine();
		
		String query = "Delete from " +  NS.PAYMENT_INFOS_TABLE + " where " + NS.PAYMENT_INFOS_ID +" = " + paymentId;
		execute(query);

	}

}
