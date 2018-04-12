package entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import edu.csc.dbms.CRUD;
import edu.csc.dbms.Constants;
import edu.csc.dbms.DBUtil;

public class Serves implements CRUD {

    @Override
    public void retrieve() throws SQLException {

        String query = "select * from " + Constants.SERVES_TABLE;

        ResultSet result = DBUtil.executeQuery(query);

        if (result != null) {

            System.out.println("staffId" + " |" + "checkinId");
            System.out.println("---------------------------------------------------------------");

            while (result.next()) {

                int staffId = result.getInt(Constants.SERVES_STAFFID);
                int checkinId = result.getInt(Constants.SERVES_CHECKINID);

                System.out.println(staffId + " |" + checkinId);
            }
        }

    }

    @Override
    public void create(Scanner scan) throws SQLException {

        System.out.println("Enter staffId: ");
        String staffId = scan.nextLine();
        System.out.println("Enter checkinId: ");
        String checkinId = scan.nextLine();

        String query = "Insert into " + Constants.SERVES_TABLE + "(" + Constants.SERVES_STAFFID + "," + Constants.SERVES_CHECKINID +
                ") values('" + staffId + "','" + checkinId + "')";
        DBUtil.executeQuery(query);

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

        String query = "Delete from " + Constants.SERVES_TABLE + " where " + Constants.SERVES_STAFFID + " = " + staffId + " and " + Constants.SERVES_CHECKINID + " = " + checkinId;
        DBUtil.executeQuery(query);

    }

}
