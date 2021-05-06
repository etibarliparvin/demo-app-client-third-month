package main;

import java.util.Scanner;

public class Util {

    public static String requireText(String text) {
        System.out.println(text);
        return new Scanner(System.in).nextLine();
    }
}
