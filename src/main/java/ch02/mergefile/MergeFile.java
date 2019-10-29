package ch02.mergefile;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergeFile {
    public static void main(String [] args) {
        File infile1 = new File("data/Countries1.dat");
        File infile2 = new File("data/Countries2.dat");
        File outfile = new File("data/mergefile.dat");
        try {
            Scanner in1 = new Scanner(infile1);
            Scanner in2 = new Scanner(infile2);
            PrintWriter out = new PrintWriter(outfile);
            Country country1 = new Country(in1);
            Country country2 = new Country(in2);
            while(!country1.isNull() && !country2.isNull()) {
                if(country1.compareTo(country2) < 0) {
                    out.println(country1);
                    country1 = new Country(in1);
                } else {
                    out.println(country2);
                    country2 = new Country(in2);
                }
            }
            while(!country1.isNull()) {
                out.println(country1);
                country1 = new Country(in1);
            }
            while(!country2.isNull()) {
                out.println(country2);
                country2 = new Country(in2);
            }
            in1.close();
            in2.close();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
