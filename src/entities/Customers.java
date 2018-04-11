package entities;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Customers implements CRUD {

	@Override
	public void retrieve() throws SQLException {
		
		String query = "select * from " + NS.CUSTOMERS_TABLE;
		
		ResultSet result = execute(query);
		
		if(result != null) {
			
			System.out.println("CustomerId" + " |"+ "name" + " |" + "dateOfBirth" + " |" + "phNumber" + " |" + "email");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int customerId = result.getInt(NS.CUSTOMERS_ID);
				String name = result.getString(NS.CUSTOMERS_NAME);
				Date dob = result.getDate(NS.CUSTOMERS_DOB);
                String phNumber = result.getString(NS.CUSTOMERS_PH_NUMBER);
                String email = result.getString(NS.CUSTOMERS_EMAIL);
                		
                System.out.println(customerId + " |" + name + " |" + dob + " |" + phNumber + " |" + email );
			}
		}

	}

	@Override
	public void create(Scanner scan) throws SQLException {
		
		System.out.println("Enter Customer name : ");
		String name = scan.nextLine();
		System.out.println("Enter DOB(YYYY-MM-DD) : ");
		String dob = scan.nextLine();
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		System.out.println("Enter email : ");
		String email = scan.nextLine();
		
		String query = "Insert into " + NS.CUSTOMERS_TABLE + "(" + NS.CUSTOMERS_NAME + "," + NS.CUSTOMERS_DOB + ","
				+ NS.CUSTOMERS_PH_NUMBER + "," + NS.CUSTOMERS_EMAIL + ") values('" + name + "','" + dob + "','" + phNum
				+ "','" + email + "')";
		execute(query);

	}

	@Override
	public void update(Scanner scan) throws SQLException {
		
		System.out.println("Enter customer ID to update : ");
		String customerId = scan.nextLine();
		
		String updateString = new String();
		
		System.out.println("Enter only update values");
		System.out.println("Enter customer name : ");
		String name = scan.nextLine();
		
		if(!name.isEmpty())
			updateString += NS.CUSTOMERS_NAME + " = '" +  name + "'";
		
		System.out.println("Enter DOB : ");
		String dob = scan.nextLine();
		
		if(!dob.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.CUSTOMERS_DOB + " = '" +  dob + "'";
		}
		
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		
		if(!phNum.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.CUSTOMERS_PH_NUMBER + " = '" +  phNum + "'";
		}
		
		System.out.println("Enter email : ");
		String email = scan.nextLine();
		
		if(!email.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.CUSTOMERS_EMAIL + " = '" +  email + "'";
		}
		
		String query = "Update " + NS.CUSTOMERS_TABLE + " set " + updateString + " where " + NS.CUSTOMERS_ID + " = " + customerId;
		execute(query);

	}

	@Override
	public void delete(Scanner scan) throws SQLException {
		
		System.out.println("Enter customer ID : ");
		String customerId = scan.nextLine();
		
		String query = "Delete from " + NS.CUSTOMERS_TABLE + " where " + NS.CUSTOMERS_ID +" = " + customerId;
		execute(query);

	}

}
