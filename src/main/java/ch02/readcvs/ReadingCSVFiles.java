package ch02.readcvs;

import java.io.File;
import java.util.Scanner;

public class ReadingCSVFiles {
    public static void main(String [] args) {
        File datafile = new File("data/Countries.csv");
        try {
            Scanner input = new Scanner(datafile);
            input.useDelimiter(",|\\s");
            String column01 = input.next();
            String column02 = input.next();
            System.out.printf("%-10s%12s%n", column01, column02);
            while(input.hasNext()) {
                String country = input.next();
                int pop = input.nextInt();
                System.out.printf("%-10s%,12d%n", country, pop);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
