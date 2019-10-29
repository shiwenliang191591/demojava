package ch02.datafilter;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FilterData {
    private static final int MIN_AREA = 1000000;

    public static void main(String [] args) {
        File file = new File("data/filterdata.dat");
        Set<Country> set = readDataset(file);

        for(Country country : set) {
            if(country.landlocked && country.area >= MIN_AREA) {
                System.out.println(country);
            }
        }
    }

    public static Set readDataset(File file) {
        Set<Country> set = new HashSet<Country>();
        try {
            Scanner input = new Scanner(file);
            input.nextLine();
            while(input.hasNextLine()) {
                set.add(new Country(input));
            }
            input.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        return set;
    }
}
