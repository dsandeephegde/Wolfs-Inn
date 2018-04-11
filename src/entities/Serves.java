package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.NS;

public class Serves implements CRUD {

	@Override
	public void retrieve() throws SQLException {
		
		String query = "select * from " + NS.SERVES_TABLE;
		
		ResultSet result = execute(query);
		
		if(result != null) {
			
			System.out.println("staffId" + " |"+ "checkinId");
			System.out.println("---------------------------------------------------------------");
			
			while(result.next()) {
				
				int staffId = result.getInt(NS.SERVES_STAFFID);
				int checkinId = result.getInt(NS.SERVES_CHECKINID);
                		
                System.out.println(staffId + " |" + checkinId );
			}
		}

	}

	@Override
	public void create(Scanner scan) throws SQLException {
		
		System.out.println("Enter staffId: ");
		String staffId = scan.nextLine();
		System.out.println("Enter checkinId: ");
		String checkinId = scan.nextLine();
	
		String query = "Insert into " + NS.SERVES_TABLE + "(" + NS.SERVES_STAFFID + "," + NS.SERVES_CHECKINID +
				") values('" + staffId + "','" + checkinId + "')";
		execute(query);

	}

	@Override
	public void update(Scanner scan) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Scanner scan) throws SQLException {
		
		System.out.println("Enter staffId : ");
		String staffId = scan.nextLine();
		System.out.println("Enter checkinId : ");
		String checkinId = scan.nextLine();
		
		String query = "Delete from " + NS.SERVES_TABLE + " where " + NS.SERVES_STAFFID +" = " + staffId + " and " + NS.SERVES_CHECKINID +" = " + checkinId;
		execute(query);

	}

}
