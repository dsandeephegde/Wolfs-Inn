package edu.csc.dbms;

public class NS {

	//Hotel Table
	public static final String HOTELS_TABLE = "hotels";
	
	public static final String HOTELS_ID = "hotelId";
	public static final String HOTELS_NAME = "Name";
	public static final String HOTELS_ADDRESS = "Address";
	public static final String HOTELS_CITY = "City";
	public static final String HOTELS_STATE = "State";
	public static final String HOTELS_COUNTRY = "Country";
	public static final String HOTELS_PH_NUMBER = "phoneNumber";
	public static final String HOTELS_MANAGER_ID = "managerId";
	
	//Staff Table
	public static final String STAFFS_TABLE = "staffs";
	
	public static final String STAFFS_ID = "staffId";
	public static final String STAFFS_NAME = "name";
	public static final String STAFFS_AGE = "age";
	public static final String STAFFS_JOB_TITLE = "jobTitle";
	public static final String STAFFS_PH_NUMBER = "phoneNumber";
	public static final String STAFFS_ADDRESS = "Address";
	public static final String STAFFS_CITY = "City";
	public static final String STAFFS_STATE = "State";
	public static final String STAFFS_COUNTRY = "Country";
	public static final String STAFFS_HOTEL_ID = "hotelId";
	
	//Customer Table
	public static final String CUSTOMERS_TABLE = "customers";
	
	public static final String CUSTOMERS_ID = "customerId";
	public static final String CUSTOMERS_NAME = "name";
	public static final String CUSTOMERS_DOB = "dateOfBirth";
	public static final String CUSTOMERS_PH_NUMBER = "phoneNumber";
	public static final String CUSTOMERS_EMAIL = "email";
	
	//Services Table
	public static final String SERVICES_TABLE = "services";
	
	public static final String SERVICES_ID = "serviceId";
	public static final String SERVICES_NAME = "name";
	public static final String SERVICES_BASE_PRICE = "basePrice";
	
	//Payment_Infos Table
	public static final String PAYMENT_INFOS_TABLE = "payment_infos";
	
	public static final String PAYMENT_INFOS_ID = "paymentId";
	public static final String PAYMENT_INFOS_SSN = "SSN";
	public static final String PAYMENT_INFOS_BILLING_ADDRESS = "billingAddress";
	public static final String PAYMENT_INFOS_CITY = "City";
	public static final String PAYMENT_INFOS_STATE = "State";
	public static final String PAYMENT_INFOS_COUNTRY = "Country";
	public static final String PAYMENT_INFOS_PAYMENT_METHOD = "paymentMethod";
	public static final String PAYMENT_INFOS_CARD_NUM = "cardNumber";
	public static final String PAYMENT_INFOS_CUSTOMER_ID = "customerId";

	//Room Table
	public static final String ROOMS_TABLE = "rooms";

	public static final String ROOMS_ROOMNUMBER = "roomNumber";
	public static final String ROOMS_HOTELID = "hotelId";
	public static final String ROOMS_CATEGORY = "category";
	public static final String ROOMS_MAXOCCUPANCY = "maxOccupancy";
	public static final String ROOMS_AVAILABILITY = "availability";
	
	//Room Prices Table
	public static final String ROOM_PRICES_TABLE = "room_prices";

	public static final String ROOM_PRICES_CATEGORY = "category";
	public static final String ROOM_PRICES_MAXOCCUPANCY = "maxOccupancy";
	public static final String ROOM_PRICES_PRICE = "price";

	//CheckIns Table
	public static final String CHECK_INS_TABLE = "checkins";

	public static final String CHECK_INS_CHECKINID = "checkinId";
	public static final String CHECK_INS_STARTDATE = "startDate";
	public static final String CHECK_INS_ENDDATE = "endDate";
	public static final String CHECK_INS_CHECKINTIME = "checkinTime";
	public static final String CHECK_INS_CHECKOUTTIME = "checkoutTIme";
	public static final String CHECK_INS_NUMBEROFGUESTS = "numberOfGuests";
	public static final String CHECK_INS_TOTAL = "total";
	public static final String CHECK_INS_CUSTOMERID = "customerId";
	public static final String CHECK_INS_HOTELID = "hotelId";
	public static final String CHECK_INS_ROOMNUMBER = "roomNumber";
	public static final String CHECK_INS_PAYMENTID = "paymentId";
	
	//Buys Table
	public static final String BUYS_TABLE = "buys";

	public static final String BUYS_SERVICEID = "serviceId";
	public static final String BUYS_CHECKINID = "checkinId";
	public static final String BUYS_PRICE = "price";
	
	//Serves Table
	public static final String SERVES_TABLE = "serves";
	
	public static final String SERVES_STAFFID = "staffId";
	public static final String SERVES_CHECKINID = "checkinId";

	
	public static void listTasks() {
		
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
		System.out.println("29. List All Checkins");
		System.out.println("30. Add new Checkin");
		System.out.println("31. Delete existing Checkin");
		System.out.println("32. Update existing Checkin");
		System.out.println("33. List All Buys");
		System.out.println("34. Add new Buys");
		System.out.println("35. Delete existing Buys");
		System.out.println("36. Update existing Buys");
		System.out.println("37. List All Serves");
		System.out.println("38. Add new Serves");
		System.out.println("39. Delete existing Serves");
		//System.out.println("40. Update existing Serves");
		System.out.println("------------------------------");
	}
	
}

