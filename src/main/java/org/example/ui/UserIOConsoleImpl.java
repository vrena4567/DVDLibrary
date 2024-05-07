package org.example.ui;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    Scanner myScanner = new Scanner(System.in);
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(myScanner.nextLine());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double input;
        do {
            System.out.println(prompt + " Between " + min + " and " + max);
            input = Double.parseDouble(myScanner.nextLine());
        } while (input < min || input > max);
        return input;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(myScanner.nextLine());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float input;
        do {
            System.out.println(prompt + " Between " + min + " and " + max);
            input = Float.parseFloat(myScanner.nextLine());
        } while (input < min || input > max);
        return input;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(myScanner.nextLine());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.println(prompt + " Between " + min + " and " + max);
            input = Integer.parseInt(myScanner.nextLine());
        } while (input < min || input > max);
        return input;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(myScanner.nextLine());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long input;
        do {
            System.out.println(prompt + " Between " + min + " and " + max);
            input = Long.parseLong(myScanner.nextLine());
        } while (input < min || input > max);
        return input;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return myScanner.nextLine();
    }
}
