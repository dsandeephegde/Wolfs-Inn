package edu.csc.dbms;

import java.sql.*;
import java.util.Scanner;

import entities.*;

public class MainClass {

	public static void main(String[] args) {
	
		int option = 0;
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\\n");
		
		//Entity Objects
		Hotels hotels = new Hotels();
		Staffs staffs = new Staffs();
		Customers customers = new Customers();
		Services services = new Services();
		PaymentInfos paymentInfos = new PaymentInfos();
		Rooms rooms = new Rooms();
		RoomPrices roomPrices = new RoomPrices();
		CheckIns checkIns = new CheckIns();
		Buys buys = new Buys();
		Serves serves = new Serves();
		
		
		try {
			while(option != 100) {
				
				NS.listTasks();
				
				System.out.print("Select the task :");
				option = Integer.parseInt(scan.nextLine());
				
				System.out.println();
				System.out.println();
				
				switch(option) {
				
				case 1://Show All Hotels
					hotels.retrieve();
					break;
					
				case 2://Add new Hotel
					hotels.create(scan);
					hotels.retrieve();
					break;
					
				case 3://Delete Hotel
					hotels.retrieve();
					hotels.delete(scan);
					hotels.retrieve();
					break;
				
				case 4://Update Hotel
					hotels.retrieve();
					hotels.update(scan);
					hotels.retrieve();
					break;
					
				case 5://Show All Staffs
					staffs.retrieve();
					break;
				
				case 6://Add new Hotel
					staffs.create(scan);
					staffs.retrieve();
					break;
					
				case 7://Delete Staff
					staffs.retrieve();
					staffs.delete(scan);
					staffs.retrieve();
				
				case 8://Update Staff
					staffs.retrieve();
					staffs.update(scan);
					staffs.retrieve();
					break;	
				
				case 9://Show All Customers
					customers.retrieve();
					break;
				
				case 10://Add new Customer
					customers.create(scan);
					customers.retrieve();
					break;
					
				case 11://Delete Staff
					customers.retrieve();
					customers.delete(scan);
					customers.retrieve();
					break;
				
				case 12://Update Hotel
					customers.retrieve();
					customers.update(scan);
					customers.retrieve();
					break;
				
				case 13://Show All Services
					services.retrieve();
					break;
				
				case 14://Add new Service
					services.create(scan);
					services.retrieve();
					break;
					
				case 15://Delete Service
					services.retrieve();
					services.delete(scan);
					services.retrieve();
					break;
				
				case 16://Update Service
					services.retrieve();
					services.update(scan);
					services.retrieve();
					break;
				
				case 17://Show All PaymentInfo
					paymentInfos.retrieve();
					break;
				
				case 18://Add new PaymentInfo
					paymentInfos.create(scan);
					paymentInfos.retrieve();
					break;
					
				case 19://Delete PaymentInfo
					paymentInfos.retrieve();
					paymentInfos.delete(scan);
					paymentInfos.retrieve();
					break;
				
				case 20://Update PaymentInfo
					paymentInfos.retrieve();
					paymentInfos.update(scan);
					paymentInfos.retrieve();
					break;

				case 21:
					rooms.retrieve();
					break;

				case 22:
					rooms.create(scan);
					rooms.retrieve();
					break;

				case 23:
					rooms.retrieve();
					rooms.delete(scan);
					rooms.retrieve();
					break;

				case 24://Update Room
					rooms.retrieve();
					rooms.update(scan);
					rooms.retrieve();
					break;
				case 25:
					roomPrices.retrieve();
					break;

				case 26:
					roomPrices.create(scan);
					roomPrices.retrieve();
					break;

				case 27:
					roomPrices.retrieve();
					roomPrices.delete(scan);
					roomPrices.retrieve();
					break;

				case 28://Update Room prices
					roomPrices.retrieve();
					roomPrices.update(scan);
					roomPrices.retrieve();
					break;

				case 29://Show CheckIns
					checkIns.retrieve();
					break;

				case 30://Add CheckIns
					checkIns.create(scan);
					checkIns.retrieve();
					break;

				case 31:// Delete CheckIns
					checkIns.retrieve();
					checkIns.delete(scan);
					checkIns.retrieve();
					break;

				case 32://Update CheckIns
					checkIns.retrieve();
					checkIns.update(scan);
					checkIns.retrieve();
					break;
				case 33://Show Buys
					buys.retrieve();
					break;

				case 34://Add Buys
					buys.create(scan);
					buys.retrieve();
					break;

				case 35:// Delete Buys
					buys.retrieve();
					buys.delete(scan);
					buys.retrieve();
					break;

				case 36://Update Buys
					buys.retrieve();
					buys.update(scan);
					buys.retrieve();
					break;
				case 37://Show Serves
					serves.retrieve();
					break;

				case 38://Add Serves
					serves.create(scan);
					serves.retrieve();
					break;

				case 39:// Delete Serves
					serves.retrieve();
					serves.delete(scan);
					serves.retrieve();
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
	
}
