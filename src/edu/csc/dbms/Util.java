package edu.csc.dbms;

import java.util.Scanner;

public class Util {

    public static int getOption() {
        int option;
        Scanner scan = new Scanner(System.in);
        try {
            option = Integer.parseInt(scan.nextLine());
        } catch (Exception e) {
            return -1;
        }
        return option;
    }

}
