package edu.csc.dbms;
import java.sql.*;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
	
		int option = 0;
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\\n");
		MainClass mc = new MainClass();
		try {
			while(option != 100) {
				
				mc.listTasks();
				
				System.out.print("Select the task :");
				option = Integer.parseInt(scan.nextLine());
				
				System.out.println();
				System.out.println();
				
				switch(option) {
				
				case 1://Show All Hotels
					mc.showHotels();
					break;
					
				case 2://Add new Hotel
					mc.addHotel(scan);
					mc.showHotels();
					break;
					
				case 3://Delete Hotel
					mc.showHotels();
					mc.deleteHotelById(scan);
					mc.showHotels();
					break;
				
				case 4://Update Hotel
					mc.showHotels();
					mc.updateHotel(scan);
					mc.showHotels();
					break;
					
				case 5://Show All Staffs
					mc.showStaffs();
					break;
				
				case 6://Add new Hotel
					mc.addStaff(scan);
					mc.showStaffs();
					break;
					
				case 7://Delete Staff
					mc.showStaffs();
					mc.deleteStaffById(scan);
					mc.showStaffs();
					break;
				
				case 8://Update Staff
					mc.showStaffs();
					mc.updateStaff(scan);
					mc.showStaffs();
					break;	
				
				case 9://Show All Customers
					mc.showCustomers();
					break;
				
				case 10://Add new Customer
					mc.addCustomer(scan);
					mc.showCustomers();
					break;
					
				case 11://Delete Staff
					mc.showCustomers();
					mc.deleteCustomerById(scan);
					mc.showCustomers();
					break;
				
				case 12://Update Hotel
					mc.showCustomers();
					mc.updateCustomer(scan);
					mc.showCustomers();
					break;
					
				default:
					System.out.println("case default");
				
				}
				System.out.println();
			}
		}catch (SQLException se){
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
		}
		
		
		System.out.println("Thank you for using Wolfs Inn");
	}

	private void listTasks() {
		
		System.out.println("------------------------------");
		System.out.println("1. List all Hotels");
		System.out.println("2. Add new Hotel");
		System.out.println("3. Delete existing Hotel");
		System.out.println("4. Update existing Hotel");
		System.out.println("5. List All Staff");
		System.out.println("6. Add new Staff");
		System.out.println("7. Delete existing Staff");
		System.out.println("8. Update existing Staff");
		System.out.println("9. List All Customers");
		System.out.println("10. Add new Customer");
		System.out.println("11. Delete existing Customer");
		System.out.println("12. Update existing Customer");
		System.out.println("------------------------------");
	}
	
	private ResultSet getResult(String query) {
		
		//URLs
		final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
	    final String DB_URL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/araja2";

	    //  Database credentials
	    final String USER = "araja2";
	    final String PASS = "200203475";
		
	    Connection conn;
        Statement stmt;
        ResultSet result = null;
	    
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			stmt = conn.createStatement();

			result = stmt.executeQuery(query);
			
		}catch (SQLException se){
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        }catch (Exception e){
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
		}

		return result;

	}
	
	private void showHotels() throws SQLException {
		
		String query = "select * from hotels";
		
		ResultSet result = getResult(query);
		
		if(result != null) {
			
			System.out.println("HotelId" + " |"+ "name" + " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "phNumber");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int hotelId = result.getInt(NS.HOTELS_ID);
				String name = result.getString(NS.HOTELS_NAME);
                String address = result.getString(NS.HOTELS_ADDRESS);
                String city = result.getString(NS.HOTELS_CITY);
                String state = result.getString(NS.HOTELS_STATE);
                String country = result.getString(NS.HOTELS_COUNTRY);
                String phNumber = result.getString(NS.HOTELS_PH_NUMBER);
                int managerId = result.getInt(NS.HOTELS_MANAGER_ID);
                		
                System.out.println(hotelId + " |" + name + " |" + address + " |" + city + " |" + state + " |" + country + " |" + phNumber + " |" + managerId);
			}
		}
	}
	
	private void addHotel(Scanner scan) throws SQLException {
		
		System.out.println("Enter hotel name : ");
		String name = scan.nextLine();
		System.out.println("Enter line 1 address : ");
		String address = scan.nextLine();
		System.out.println("Enter City : ");
		String city = scan.nextLine();
		System.out.println("Enter State : ");
		String state = scan.nextLine();
		System.out.println("Enter Country : ");
		String country = scan.nextLine();
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		
		
		String query = "Insert into hotels(" + NS.HOTELS_NAME + "," + NS.HOTELS_ADDRESS + "," + NS.HOTELS_CITY + ","
				+ NS.HOTELS_STATE + "," + NS.HOTELS_COUNTRY + "," + NS.HOTELS_PH_NUMBER + ") values('" + name + "','"
				+ address + "','" + city + "','" + state + "','" + country + "'," + phNum + ")";
		getResult(query);
		
	}
	
	private void deleteHotelById(Scanner scan) throws SQLException {
		
		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();
		
		String query = "Delete from hotels where " + NS.HOTELS_ID +" = " + hotelId;
		getResult(query);
	}
	
	private void updateHotel(Scanner scan) throws SQLException {
		
		System.out.println("Enter hotel ID to update : ");
		String hotelId = scan.nextLine();
		
		String updateString = new String();
		
		System.out.println("Enter only update values");
		System.out.println("Enter hotel name : ");
		String name = scan.nextLine();
		
		if(!name.isEmpty())
			updateString += NS.HOTELS_NAME + " = '" +  name + "'";
		
		System.out.println("Enter line 1 address : ");
		String address = scan.nextLine();
		
		if(!address.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_ADDRESS + " = '" +  address + "'";
		}
			
		
		System.out.println("Enter City : ");
		String city = scan.nextLine();
		
		if(!city.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_CITY + " = '" +  city + "'";
		}
		
		System.out.println("Enter State : ");
		String state = scan.nextLine();
		
		if(!state.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_STATE + " = '" +  state + "'";
		}
		
		System.out.println("Enter Country : ");
		String country = scan.nextLine();
		
		if(!country.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_COUNTRY + " = '" +  country + "'";
		}
		
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		
		if(!phNum.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_PH_NUMBER + " = '" +  phNum + "'";
		}
		
		System.out.println("Enter Manager ID : ");
		String managerId = scan.nextLine();
		
		if(!country.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.HOTELS_MANAGER_ID + " = " +  managerId + "";
		}
		
		String query = "Update hotels set " + updateString + " where " + NS.HOTELS_ID + " = " + hotelId;
		getResult(query);
		
	}

	private void showStaffs() throws SQLException {
		
		String query = "select * from staffs";
		
		ResultSet result = getResult(query);
		
		if(result != null) {
			
			System.out.println("StaffId" + " |" + "name" + " |" + "age" + " |" + "jobTitle" + " |" + "phoneNumber"
					+ " |" + "address" + " |" + "city" + " |" + "state" + " |" + "country" + " |" + "hotelId");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int staffId = result.getInt(NS.STAFFS_ID);
				String name = result.getString(NS.STAFFS_NAME);
				int age = result.getInt(NS.STAFFS_AGE);
				String jobTitle = result.getString(NS.STAFFS_JOB_TITLE);
				String phNumber = result.getString(NS.STAFFS_PH_NUMBER);
                String address = result.getString(NS.STAFFS_ADDRESS);
                String city = result.getString(NS.STAFFS_CITY);
                String state = result.getString(NS.STAFFS_STATE);
                String country = result.getString(NS.STAFFS_COUNTRY);
                int hotelId = result.getInt(NS.STAFFS_HOTEL_ID);
                		
                System.out.println(staffId + " |" + name + " |" + age + " |" + jobTitle + " |" + phNumber + " |" + address + " |" + city + " |" + state + " |" + country + " |" + hotelId);
			}
		}
	}

	private void deleteStaffById(Scanner scan) throws SQLException {
		
		System.out.println("Enter staff ID : ");
		String staffId = scan.nextLine();
		
		String query = "Delete from staffs where " + NS.STAFFS_ID +" = " + staffId;
		getResult(query);
	}
	
	
	private void addStaff(Scanner scan) throws SQLException {
		
		System.out.println("Enter Staff name : ");
		String name = scan.nextLine();
		System.out.println("Enter age : ");
		String age = scan.nextLine();
		System.out.println("Enter jobTitle : ");
		String jobTitle = scan.nextLine();
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		System.out.println("Enter line 1 address : ");
		String address = scan.nextLine();
		System.out.println("Enter City : ");
		String city = scan.nextLine();
		System.out.println("Enter State : ");
		String state = scan.nextLine();
		System.out.println("Enter Country : ");
		String country = scan.nextLine();
		System.out.println("Enter Hotel Id : ");
		String hotelId = scan.nextLine();
		
		
		String query = "Insert into staffs(" + NS.STAFFS_NAME + "," + NS.STAFFS_AGE + "," + NS.STAFFS_JOB_TITLE + ","
				+ NS.STAFFS_PH_NUMBER + "," + NS.STAFFS_ADDRESS + "," + NS.STAFFS_CITY + "," + NS.STAFFS_STATE + ","
				+ NS.STAFFS_COUNTRY + "," + NS.STAFFS_HOTEL_ID + ") values('" + name + "'," + age + ",'" + jobTitle
				+ "','" + phNum + "','" + address + "','" + city + "','" + state + "','" + country + "'," + hotelId
				+ ")";
		getResult(query);
		
	}
	
	private void updateStaff(Scanner scan) throws SQLException {
		
		System.out.println("Enter Staff ID to update : ");
		String staffId = scan.nextLine();
		
		String updateString = new String();
		
		System.out.println("Enter only update values");
		System.out.println("Enter staff name : ");
		String name = scan.nextLine();
		
		if(!name.isEmpty())
			updateString += NS.STAFFS_NAME + " = '" +  name + "'";
		
		System.out.println("Enter age : ");
		String age = scan.nextLine();
		
		if(!age.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.STAFFS_AGE + " = " +  age ;
		}
		
		System.out.println("Enter Job Title : ");
		String jobTitle = scan.nextLine();
		
		if(!jobTitle.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_JOB_TITLE + " = '" +  jobTitle + "'";
		}
		
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		
		if(!phNum.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_PH_NUMBER + " = '" +  phNum + "'";
		}
		
		System.out.println("Enter line 1 address : ");
		String address = scan.nextLine();
		
		if(!address.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_ADDRESS + " = '" +  address + "'";
		}
		
		System.out.println("Enter City : ");
		String city = scan.nextLine();
		
		if(!city.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_CITY + " = '" +  city + "'";
		}
		
		System.out.println("Enter State : ");
		String state = scan.nextLine();
		
		if(!state.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_STATE + " = '" +  state + "'";
		}
		
		System.out.println("Enter Country : ");
		String country = scan.nextLine();
		
		if(!country.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_COUNTRY + " = '" +  country + "'";
		}
		
		System.out.println("Enter Hotel ID : ");
		String hotelId = scan.nextLine();
		
		if(!hotelId.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.STAFFS_HOTEL_ID + " = " +  hotelId + "";
		}
		
		String query = "Update staffs set " + updateString + " where " + NS.STAFFS_ID + " = " + staffId;
		getResult(query);
		
	}
	
	private void showCustomers() throws SQLException {
		
		String query = "select * from customers";
		
		ResultSet result = getResult(query);
		
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

	private void addCustomer(Scanner scan) throws SQLException {
		
		System.out.println("Enter Customer name : ");
		String name = scan.nextLine();
		System.out.println("Enter DOB(YYYY-MM-DD) : ");
		String dob = scan.nextLine();
		System.out.println("Enter phone number : ");
		String phNum = scan.nextLine();
		System.out.println("Enter email : ");
		String email = scan.nextLine();
		
		String query = "Insert into customers(" + NS.CUSTOMERS_NAME + "," + NS.CUSTOMERS_DOB + ","
				+ NS.CUSTOMERS_PH_NUMBER + "," + NS.CUSTOMERS_EMAIL + ") values('" + name + "','" + dob + "','" + phNum
				+ "','" + email + "')";
		getResult(query);
		
	}
	
	private void deleteCustomerById(Scanner scan) throws SQLException {
		
		System.out.println("Enter customer ID : ");
		String customerId = scan.nextLine();
		
		String query = "Delete from customers where " + NS.CUSTOMERS_ID +" = " + customerId;
		getResult(query);
	}
	
	private void updateCustomer(Scanner scan) throws SQLException {
		
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
		
		String query = "Update customers set " + updateString + " where " + NS.CUSTOMERS_ID + " = " + customerId;
		getResult(query);
		
	}
	
}
