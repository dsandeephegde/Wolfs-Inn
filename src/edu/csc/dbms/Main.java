package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int option = 0;
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");

        try {
            while (option != 100) {

                System.out.println("1. CRUD operations");
                System.out.println("2. Task1 - Information Processing");
                System.out.println("3. Task2 - Maintaining Service Records");
                System.out.println("4. Task3 - Maintaining Billing Accounts");
                System.out.println("5. Task4 - Reports");

                System.out.println();
                System.out.print("Select the task :");

                option = Integer.parseInt(scan.nextLine());

                System.out.println();

                switch (option) {

                    case 1:
                        Entities.crudOperations();
                        break;

                    case 2:
                    		Info_Processing ip =new Info_Processing();
                    		ip.info_processing();
                    		break;

                    case 3:
                        Maintaining_Service_Records maintaining_service_records =new Maintaining_Service_Records();
                        maintaining_service_records.operations();
                        break;

                    case 4:
                    		Maintaining_Billing_Accounts billing = new Maintaining_Billing_Accounts();
                    		billing.operations();
                    		break;	
                    	
                    case 5:
                    		Reports reports = new Reports();
                    		reports.operations();
                    		break;

                    default:
                        System.out.println("Please Enter a valid option");
                }

                System.out.println();
            }
        } catch (SQLException se) {
            System.out.println("SQL Exception " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception " + e.getLocalizedMessage());
            e.printStackTrace();
        }


        System.out.println("Thank you for using Wolfs Inn");
    }
    

}
