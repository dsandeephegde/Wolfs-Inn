package edu.csc.dbms;

public class NS {

	//Hotel Table
	static final String HOTELS_TABLE = "hotels";
	
	static final String HOTELS_ID = "hotelId";
	static final String HOTELS_NAME = "Name";
	static final String HOTELS_ADDRESS = "Address";
	static final String HOTELS_CITY = "City";
	static final String HOTELS_STATE = "State";
	static final String HOTELS_COUNTRY = "Country";
	static final String HOTELS_PH_NUMBER = "phoneNumber";
	static final String HOTELS_MANAGER_ID = "managerId";
	
	//Staff Table
	static final String STAFFS_TABLE = "staffs";
	
	static final String STAFFS_ID = "staffId";
	static final String STAFFS_NAME = "name";
	static final String STAFFS_AGE = "age";
	static final String STAFFS_JOB_TITLE = "jobTitle";
	static final String STAFFS_PH_NUMBER = "phoneNumber";
	static final String STAFFS_ADDRESS = "Address";
	static final String STAFFS_CITY = "City";
	static final String STAFFS_STATE = "State";
	static final String STAFFS_COUNTRY = "Country";
	static final String STAFFS_HOTEL_ID = "hotelId";
	
	//Customer Table
	static final String CUSTOMERS_TABLE = "customers";
	
	static final String CUSTOMERS_ID = "customerId";
	static final String CUSTOMERS_NAME = "name";
	static final String CUSTOMERS_DOB = "dateOfBirth";
	static final String CUSTOMERS_PH_NUMBER = "phoneNumber";
	static final String CUSTOMERS_EMAIL = "email";
	
	//Services Table
	static final String SERVICES_TABLE = "services";
	
	static final String SERVICES_ID = "serviceId";
	static final String SERVICES_NAME = "name";
	static final String SERVICES_BASE_PRICE = "basePrice";
	
	//Payment_Infos Table
	static final String PAYMENT_INFOS_TABLE = "payment_infos";
	
	static final String PAYMENT_INFOS_ID = "paymentId";
	static final String PAYMENT_INFOS_SSN = "SSN";
	static final String PAYMENT_INFOS_BILLING_ADDRESS = "billingAddress";
	static final String PAYMENT_INFOS_CITY = "City";
	static final String PAYMENT_INFOS_STATE = "State";
	static final String PAYMENT_INFOS_COUNTRY = "Country";
	static final String PAYMENT_INFOS_PAYMENT_METHOD = "paymentMethod";
	static final String PAYMENT_INFOS_CARD_NUM = "cardNumber";
	static final String PAYMENT_INFOS_CUSTOMER_ID = "customerId";

	//Room Table
	static final String ROOMS_TABLE = "rooms";

	static final String ROOMS_ROOMNUMBER = "roomNumber";
	static final String ROOMS_HOTELID = "hotelId";
	static final String ROOMS_CATEGORY = "category";
	static final String ROOMS_MAXOCCUPANCY = "maxOccupancy";
	static final String ROOMS_AVAILABILITY = "availability";
	
	//Room Prices Table
	static final String ROOM_PRICES_TABLE = "room_prices";

	static final String ROOM_PRICES_CATEGORY = "category";
	static final String ROOM_PRICES_MAXOCCUPANCY = "maxOccupancy";
	static final String ROOM_PRICES_PRICE = "price";

	//CheckIns Table
	static final String CHECK_INS_TABLE = "checkins";

	static final String CHECK_INS_CHECKINID = "checkinId"
	static final String CHECK_INS_STARTDATE = "startDate";
	static final String CHECK_INS_ENDDATE = "endDate";
	static final String CHECK_INS_CHECKINTIME = "checkinTime";
	static final String CHECK_INS_CHECKOUTTIME = "checkoutTIme";
	static final String CHECK_INS_NUMBEROFGUESTS = "numberOfGuests";
	static final String CHECK_INS_TOTAL = "total";
	static final String CHECK_INS_CUSTOMERID = "customerId";
	static final String CHECK_INS_HOTELID = "hotelId";
	static final String CHECK_INS_ROOMNUMBER = "roomNumber";
	static final String CHECK_INS_PAYMENTID = "paymentId";

}
//(startDate, endDate, checkinTime, checkoutTime, numberOfGuests, total, customerId, hotelId, roomNumber, paymentId)
