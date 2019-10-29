package ch02.sortdata;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;

public class SortingData {

    public static void main(String [] args) {
        File file = new File("data/sortdata.dat");
        TreeMap<Integer, String> dataset = new TreeMap<Integer, String>();
        try {
            Scanner input = new Scanner(file);
            while(input.hasNext()) {
                String x = input.next();
                int y = input.nextInt();
                dataset.put(y, x);
            }
            input.close();
        } catch(Exception e) {
            System.out.println(e);
        }
        print(dataset);
    }

    public static void print(TreeMap<Integer, String> map) {
        for(Integer key : map.keySet()) {
            System.out.printf("%,12d %-16s%n", key, map.get(key));
        }
    }
}
