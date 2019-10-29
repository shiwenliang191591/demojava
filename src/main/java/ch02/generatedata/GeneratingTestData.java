package ch02.generatedata;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class GeneratingTestData {
    private static final int ROWS = 8, COLS = 5;
    private static final Random RANDOM = new Random();

    public static void main(String [] args) {
        File outputFile = new File("data/Output.csv");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for(int i = 0; i < ROWS; i ++) {
                for(int j = 0; j < COLS; j ++) {
                    writer.printf("%.6f,",RANDOM.nextDouble());
                }
                writer.printf("%.6f%n",RANDOM.nextDouble());
            }
            writer.close();
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}
