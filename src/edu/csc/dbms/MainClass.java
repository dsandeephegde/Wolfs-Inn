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
				
				case 13://Show All Services
					mc.showServices();
					break;
				
				case 14://Add new Service
					mc.addService(scan);
					mc.showServices();
					break;
					
				case 15://Delete Service
					mc.showServices();
					mc.deleteServiceById(scan);
					mc.showServices();
					break;
				
				case 16://Update Service
					mc.showServices();
					mc.updateService(scan);
					mc.showServices();
					break;
				
				case 17://Show All PaymentInfo
					mc.showPaymentInfos();
					break;
				
				case 18://Add new PaymentInfo
					mc.addPaymentInfo(scan);
					mc.showPaymentInfos();
					break;
					
				case 19://Delete PaymentInfo
					mc.showPaymentInfos();
					mc.deletePaymentInfoById(scan);
					mc.showPaymentInfos();
					break;
				
				case 20://Update PaymentInfo
					mc.showPaymentInfos();
					mc.updatePaymentInfo(scan);
					mc.showPaymentInfos();
					break;

				case 21:
					mc.showRooms();
					break;

				case 22:
					mc.addRoom(scan);
					mc.showRooms();
					break;

				case 23:
					mc.showRooms();
					mc.deleteRoomByRoomNumberAndHotelId(scan);
					mc.showRooms();
					break;

				case 24://Update Room
					mc.showRoomPrices();
					mc.updateRoomPrices(scan);
					mc.showRoomPrices();
					break;
				case 25:
					mc.showRoomPrices();
					break;

				case 26:
					mc.addRoomPrices(scan);
					mc.showRoomPrices();
					break;

				case 27:
					mc.showRoomPrices();
					mc.deleteRoomPrices(scan);
					mc.showRoomPrices();
					break;

				case 28://Update Room prices
					mc.showRoomPrices();
					mc.updateRoomPrices(scan);
					mc.showRoomPrices();
					break;

				case 29://Show CheckIns
					mc.showCheckIns();
					break;

				case 30://Add CheckIns
					mc.addCheckIns(scan);
					mc.showCheckIns();
					break;

				case 31:// Delete CheckIns
					mc.showCheckIns();
					mc.deleteCheckIn(scan);
					mc.showCheckIns();
					break;

				case 32://Update CheckIns
					mc.showCheckIns();
					mc.updateCheckIn(scan);
					mc.showCheckIns();
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
		System.out.println("13. List All Services");
		System.out.println("14. Add new Service");
		System.out.println("15. Delete existing Service");
		System.out.println("16. Update existing Service");
		System.out.println("17. List All PaymentInfos");
		System.out.println("18. Add new PaymentInfo");
		System.out.println("19. Delete existing PaymentInfo");
		System.out.println("20. Update existing PaymentInfo");
		System.out.println("21. List All Rooms");
		System.out.println("22. Add new Room");
		System.out.println("23. Delete existing Room");
		System.out.println("24. Update existing Room");
		System.out.println("25. List All RoomPrices");
		System.out.println("26. Add new RoomPrice");
		System.out.println("27. Delete existing RoomPrice");
		System.out.println("28. Update existing RoomPrice");
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
		
		String query = "select * from " + NS.HOTELS_TABLE;
		
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
		
		
		String query = "Insert into " + NS.HOTELS_TABLE + "(" + NS.HOTELS_NAME + "," + NS.HOTELS_ADDRESS + "," + NS.HOTELS_CITY + ","
				+ NS.HOTELS_STATE + "," + NS.HOTELS_COUNTRY + "," + NS.HOTELS_PH_NUMBER + ") values('" + name + "','"
				+ address + "','" + city + "','" + state + "','" + country + "'," + phNum + ")";
		getResult(query);
		
	}
	
	private void deleteHotelById(Scanner scan) throws SQLException {
		
		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();
		
		String query = "Delete from " +  NS.HOTELS_TABLE + " where " + NS.HOTELS_ID +" = " + hotelId;
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
		
		String query = "Update " + NS.HOTELS_TABLE + " set " + updateString + " where " + NS.HOTELS_ID + " = " + hotelId;
		getResult(query);
		
	}

	private void showStaffs() throws SQLException {
		
		String query = "select * from " + NS.STAFFS_TABLE;
		
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
		
		String query = "Delete from " + NS.STAFFS_TABLE  + " where " + NS.STAFFS_ID +" = " + staffId;
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
		
		
		String query = "Insert into " + NS.STAFFS_TABLE + "(" + NS.STAFFS_NAME + "," + NS.STAFFS_AGE + "," + NS.STAFFS_JOB_TITLE + ","
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
		
		String query = "Update " + NS.STAFFS_TABLE + " set " + updateString + " where " + NS.STAFFS_ID + " = " + staffId;
		getResult(query);
		
	}
	
	private void showCustomers() throws SQLException {
		
		String query = "select * from " + NS.CUSTOMERS_TABLE;
		
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
		
		String query = "Insert into " + NS.CUSTOMERS_TABLE + "(" + NS.CUSTOMERS_NAME + "," + NS.CUSTOMERS_DOB + ","
				+ NS.CUSTOMERS_PH_NUMBER + "," + NS.CUSTOMERS_EMAIL + ") values('" + name + "','" + dob + "','" + phNum
				+ "','" + email + "')";
		getResult(query);
		
	}
	
	private void deleteCustomerById(Scanner scan) throws SQLException {
		
		System.out.println("Enter customer ID : ");
		String customerId = scan.nextLine();
		
		String query = "Delete from " + NS.CUSTOMERS_TABLE + " where " + NS.CUSTOMERS_ID +" = " + customerId;
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
		
		String query = "Update " + NS.CUSTOMERS_TABLE + " set " + updateString + " where " + NS.CUSTOMERS_ID + " = " + customerId;
		getResult(query);
		
	}
	
	private void showServices() throws SQLException {
		
		String query = "select * from " + NS.SERVICES_TABLE;
		
		ResultSet result = getResult(query);
		
		if(result != null) {
			
			System.out.println("ServiceId" + " |"+ "name" + " |" + "basePrice");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int serviceId = result.getInt(NS.SERVICES_ID);
				String name = result.getString(NS.SERVICES_NAME);
                int basePrice = result.getInt(NS.SERVICES_BASE_PRICE);
                		
                System.out.println(serviceId + " |" + name + " |" + basePrice );
			}
		}
	}
	
	private void addService(Scanner scan) throws SQLException {
		
		System.out.println("Enter Service name : ");
		String name = scan.nextLine();
		System.out.println("Enter base price : ");
		String basePrice = scan.nextLine();
	
		String query = "Insert into " + NS.SERVICES_TABLE + "(" + NS.SERVICES_NAME + "," + NS.SERVICES_BASE_PRICE + ") values('" + name + "'," + basePrice + ")";
		getResult(query);
		
	}
	
	
	private void deleteServiceById(Scanner scan) throws SQLException {
		
		System.out.println("Enter service ID : ");
		String serviceId = scan.nextLine();
		
		String query = "Delete from " + NS.SERVICES_TABLE + " where " + NS.SERVICES_ID +" = " + serviceId;
		getResult(query);
	}
	
	
	private void updateService(Scanner scan) throws SQLException {
		
		System.out.println("Enter service ID to update : ");
		String serviceId = scan.nextLine();
		
		String updateString = new String();
		
		System.out.println("Enter only update values");
		System.out.println("Enter service name : ");
		String name = scan.nextLine();
		
		if(!name.isEmpty())
			updateString += NS.SERVICES_NAME + " = '" +  name + "'";
		
		System.out.println("Enter base price : ");
		String basePrice = scan.nextLine();
		
		if(!basePrice.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ; 
			updateString += NS.SERVICES_BASE_PRICE + " = " +  basePrice ;
		}
		
		String query = "Update " + NS.SERVICES_TABLE + " set " + updateString + " where " + NS.SERVICES_ID + " = " + serviceId;
		getResult(query);
		
	}
	
	
	private void showPaymentInfos() throws SQLException {
		
		String query = "select * from " + NS.PAYMENT_INFOS_TABLE;
		
		ResultSet result = getResult(query);
		
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
	
	
	private void addPaymentInfo(Scanner scan) throws SQLException {
		
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
		getResult(query);
		
	}
	
	private void deletePaymentInfoById(Scanner scan) throws SQLException {
		
		System.out.println("Enter payment ID : ");
		String paymentId = scan.nextLine();
		
		String query = "Delete from " +  NS.PAYMENT_INFOS_TABLE + " where " + NS.PAYMENT_INFOS_ID +" = " + paymentId;
		getResult(query);
	}
	
	
	private void updatePaymentInfo(Scanner scan) throws SQLException {
		
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
		getResult(query);
		
	}

	private void showRooms() throws SQLException {

		String query = "select * from "+NS.ROOMS_TABLE;

		ResultSet result = getResult(query);

		if(result != null) {

			System.out.println("roomNumber" + " |"+ "hotelId" + " |" + "category" + " |" + "maxOccupancy" + " |" + "availability");
			System.out.println("---------------------------------------------------------------");

			while(result.next()) {

				int roomNumber = result.getInt(NS.ROOMS_ROOMNUMBER);
				int hotelId = result.getInt(NS.ROOMS_HOTELID);
				String category = result.getString(NS.ROOMS_CATEGORY);
				int maxOccupancy = result.getInt(NS.ROOMS_MAXOCCUPANCY);
				boolean availability = result.getBoolean(NS.ROOMS_AVAILABILITY);

				System.out.println(roomNumber + " |" + hotelId + " |" + category + " |" + maxOccupancy + " |" + availability);
			}
		}
	}

	private void addRoom(Scanner scan) {


		System.out.println("Enter room number : ");
		String roomNumber = scan.nextLine();
		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();
		System.out.println("Enter category : ");
		String category = scan.nextLine();
		System.out.println("Enter max occupancy : ");
		String maxOccupancy = scan.nextLine();
		System.out.println("Enter availability : ");
		String availability = scan.nextLine();

		String query = "Insert into " + NS.ROOMS_TABLE + "(" + NS.ROOMS_ROOMNUMBER + "," + NS.ROOMS_HOTELID + "," + NS.ROOMS_CATEGORY + ","
				+ NS.ROOMS_MAXOCCUPANCY + "," + NS.ROOMS_AVAILABILITY + ") values('" + roomNumber + "','"
				+ hotelId + "','" + category + "','" + maxOccupancy + "','" + availability +"')";
		getResult(query);

	}

	private void deleteRoomByRoomNumberAndHotelId(Scanner scan) {

		System.out.println("Enter room number : ");
		String roomNumber = scan.nextLine();

		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();

		String query = "Delete from " + NS.ROOMS_TABLE + " where " + NS.ROOMS_ROOMNUMBER + " = " + roomNumber + " AND " + NS.ROOMS_HOTELID + " = " + hotelId;
		getResult(query);
	}

	private void updateRoom(Scanner scan) throws SQLException {

		System.out.println("Enter room number : ");
		String roomNumber = scan.nextLine();

		System.out.println("Enter hotelId : ");
		String hotelId = scan.nextLine();

		String updateString = new String();

		System.out.println("Enter only update values");
		System.out.println("Enter category : ");
		String category = scan.nextLine();

		if(!category.isEmpty())
			updateString += NS.ROOMS_CATEGORY + " = '" +  category + "'";

		System.out.println("Enter max occupancy : ");
		String maxOccupancy = scan.nextLine();

		if(!maxOccupancy.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.ROOMS_MAXOCCUPANCY + " = '" +  maxOccupancy + "'";
		}


		System.out.println("Enter availability : ");
		String availability = scan.nextLine();

		if(!availability.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.ROOMS_AVAILABILITY + " = '" +  availability + "'";
		}

		String query = "Update " + NS.ROOMS_TABLE + " set " + updateString + " where " + NS.ROOMS_ROOMNUMBER + " = " + roomNumber + " and " + NS.ROOMS_HOTELID + " = " + hotelId;
		getResult(query);

	}
	private void showRoomPrices() throws SQLException {

		String query = "select * from "+NS.ROOM_PRICES_TABLE;

		ResultSet result = getResult(query);

		if(result != null) {

			System.out.println("category" + " |"+ "maxOccupancy" + " |" + "price");
			System.out.println("---------------------------------------------------------------");

			while(result.next()) {

				String category = result.getString(NS.ROOM_PRICES_CATEGORY);
				int maxOccupancy = result.getInt(NS.ROOM_PRICES_MAXOCCUPANCY);
				int price = result.getInt(NS.ROOM_PRICES_PRICE);

				System.out.println(category + " |" + maxOccupancy + " |" + price );
			}
		}
	}

	private void addRoomPrices(Scanner scan) {


		System.out.println("Enter category : ");
		String category = scan.nextLine();
		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();
		System.out.println("Enter price : ");
		String price = scan.nextLine();

		String query = "Insert into " + NS.ROOM_PRICES_TABLE + "(" + NS.ROOM_PRICES_CATEGORY + "," + NS.ROOM_PRICES_MAXOCCUPANCY + "," + NS.ROOM_PRICES_PRICE
				+ ") values('" + category + "','"
				+ maxOccupancy + "','" + price +"')";
		getResult(query);

	}

	private void deleteRoomPrices(Scanner scan) {

		System.out.println("Enter category : ");
		String category = scan.nextLine();

		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();

		String query = "Delete from " + NS.ROOM_PRICES_TABLE + " where " + NS.ROOM_PRICES_CATEGORY + " = '" + category + "' AND " + NS.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy +"'";
		getResult(query);
	}

	private void updateRoomPrices(Scanner scan) throws SQLException {

		System.out.println("Enter category : ");
		String category = scan.nextLine();

		System.out.println("Enter maxOccupancy : ");
		String maxOccupancy = scan.nextLine();

		String updateString = new String();

		System.out.println("Enter only update values");
		System.out.println("Enter price : ");
		String price = scan.nextLine();

		if(!price.isEmpty())
			updateString += NS.ROOM_PRICES_PRICE + " = " +  price;

		String query = "Update " + NS.ROOM_PRICES_TABLE + " set " + updateString + " where " + NS.ROOM_PRICES_CATEGORY + " LIKE '" + category + "' and " + NS.ROOM_PRICES_MAXOCCUPANCY + " = '" + maxOccupancy +"'";
		getResult(query);

	}

	private void showCheckIns() throws SQLException {

		String query = "select * from " + NS.CHECK_INS_TABLE;

		ResultSet result = getResult(query);

		if(result != null) {

			System.out.println("checkinId" +"startDate" + " |"+ "endDate" + " |" + "checkinTime" + " |" + "checkoutTIme" + " |" + "numberOfGuests" + " |" + "total" + " |" + "customerId" + " |" + "hotelId" + " |" + "roomNumber" + " |" + "paymentId");
			System.out.println("---------------------------------------------------------------");

			while(result.next()) {

				int checkinId = result.getInt(NS.CHECK_INS_CHECKINID);
				String startDate = result.getInt(NS.CHECK_INS_STARTDATE);
				String endDate = result.getString(NS.CHECK_INS_ENDDATE);
				String checkinTime = result.getString(NS.CHECK_INS_CHECKINTIME);
				String checkoutTime = result.getString(NS.CHECK_INS_CHECKOUTTIME);
				int numberOfGuests = result.getInt(NS.CHECK_INS_NUMBEROFGUESTS);
				int total = result.getInt(NS.CHECK_INS_TOTAL);
				int customerId = result.getInt(NS.CHECK_INS_CUSTOMERID);
				int hotelId = result.getString(NS.CHECK_INS_HOTELID);
				int roomNumber = result.getInt(NS.CHECK_INS_ROOMNUMBER);
				int paymentId = result.getInt(NS.CHECK_INS_PAYMENTID);

				System.out.println(checkinId + " |" +startDate + " |" + endDate + " |" + checkinTime + " |" + checkoutTime + " |" + numberOfGuests + " |" + total + " |" + customerId + " |" + hotelId + " |" + roomNumber+ " |" + paymentId);
			}
		}
	}


	private void addCheckIns(Scanner scan) throws SQLException {

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
				+ "," + NS.CHECK_INS_HOTELID + "," + NS.CHECK_INS_ROOMNUMBER + "," + NS.CHECK_INS_PAYMENTID +") values('" + startDate + "','" + endDate + "','" + checkinTime + "','"
				+ checkoutTime + "','" + numberOfGuests + "','" + total + "','" + customerId + "'," + hotelId + "','" + roomNumber + "'," + paymentId + ")";
		getResult(query);

	}

	private void deleteCheckIn(Scanner scan) throws SQLException {

		System.out.println("Enter checkin Id : ");
		String checkinId = scan.nextLine();

		String query = "Delete from " +  NS.CHECK_INS_TABLE + " where " + NS.CHECK_INS_CHECKINID +" = " + checkinId;
		getResult(query);
	}


	private void updateCheckIn(Scanner scan) throws SQLException {

		System.out.println("Enter checkin ID to update : ");
		String checkinId = scan.nextLine();

		String updateString = new String();

		System.out.println("Enter only update values");
		System.out.println("Enter startDate : ");
		String startDate = scan.nextLine();

		if(!startDate.isEmpty())
			updateString += NS.CHECK_INS_STARTDATE + " = '" +  startDate + "'";

		System.out.println("Enter endDate : ");
		String endDate = scan.nextLine();

		if(!endDate.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_ENDDATE + " = '" +  endDate + "'";
		}


		System.out.println("Enter checkinTime : ");
		String checkinTime = scan.nextLine();

		if(!checkinTime.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_CHECKINTIME + " = '" +  checkinTime + "'";
		}

		System.out.println("Enter checkoutTime : ");
		String checkoutTime = scan.nextLine();

		if(!checkoutTime.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_CHECKOUTTIME + " = '" +  checkoutTime + "'";
		}

		System.out.println("Enter numberOfGuests : ");
		String numberOfGuests = scan.nextLine();

		if(!numberOfGuests.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_NUMBEROFGUESTS + " = '" +  numberOfGuests + "'";
		}

		System.out.println("Enter total : ");
		String total = scan.nextLine();

		if(!total.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_TOTAL + " = '" +  total + "'";
		}

		System.out.println("Enter customerId : ");
		String customerId = scan.nextLine();

		if(!customerId.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_CUSTOMERID + " = '" +  customerId + "'";
		}

		System.out.println("Enter hotelId : ");
		String hotelId = scan.nextLine();

		if(!hotelId.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_HOTELID + " = " +  hotelId + "";
		}

		System.out.println("Enter roomNumber : ");
		String roomNumber = scan.nextLine();

		if(!roomNumber.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_ROOMNUMBER + " = '" +  roomNumber + "'";
		}

		System.out.println("Enter paymentId : ");
		String paymentId = scan.nextLine();

		if(!paymentId.isEmpty()) {
			updateString += (!updateString.isEmpty())? " , ":"" ;
			updateString += NS.CHECK_INS_PAYMENTID + " = " +  paymentId + "";
		}

		String query = "Update " + NS.CHECK_INS_TABLE + " set " + updateString + " where " + NS.CHECK_INS_CHECKINID + " = " + checkinId;
		getResult(query);

	}
}
