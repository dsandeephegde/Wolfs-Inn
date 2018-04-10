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
				
				case 1:
					mc.showHotels();
					break;
					
				case 2: 
					mc.addHotel(scan);
					mc.showHotels();
					break;
					
				case 3:
					mc.showHotels();
					mc.deleteHotelById(scan);
					mc.showHotels();
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
	
	private void addHotel(Scanner scan) {
		
		
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
	
	private void deleteHotelById(Scanner scan) {
		
		System.out.println("Enter hotel ID : ");
		String hotelId = scan.nextLine();
		
		String query = "Delete from hotels where " + NS.HOTELS_ID +" = " + hotelId;
		getResult(query);
	}
	
}
