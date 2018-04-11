package edu.csc.dbms;

import java.sql.SQLException;
import java.util.Scanner;

import entities.*;

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

    public static void listEntities(Scanner scan) throws SQLException {

        System.out.println("1. Hotels");
        System.out.println("2. Staffs");
        System.out.println("3. Customers");
        System.out.println("4. CheckIns");
        System.out.println("5. PaymentInfos");
        System.out.println("6. Rooms");
        System.out.println("7. RooomPrices");
        System.out.println("8. Services");
        System.out.println("9. Buys");
        System.out.println("10. Serves");

        System.out.println();
        System.out.print("Select the table :");
        System.out.println();

        int option = Integer.parseInt(scan.nextLine());
        CRUD entityObject = null;

        switch (option) {

            case 1:
                entityObject = new Hotels();
                break;
            case 2:
                entityObject = new Staffs();
                break;
            case 3:
                entityObject = new Customers();
                break;
            case 4:
                entityObject = new CheckIns();
                break;
            case 5:
                entityObject = new PaymentInfos();
                break;
            case 6:
                entityObject = new Rooms();
                break;
            case 7:
                entityObject = new RoomPrices();
                break;
            case 8:
                entityObject = new Services();
                break;
            case 9:
                entityObject = new Buys();
                break;
            case 10:
                entityObject = new Serves();
                break;
        }

        CRUD.select(entityObject, scan);

    }
}

