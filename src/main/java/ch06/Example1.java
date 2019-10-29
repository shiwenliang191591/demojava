package ch06;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.io.File;
import java.util.Scanner;

public class Example1 {
    public static void main(String [] args) {
        SimpleRegression sr = getData("data/Data1.dat");
        double m = sr.getSlope();
        double b = sr.getIntercept();
        double r = sr.getR();
        double r2 = sr.getRSquare();
        double sse = sr.getSumSquaredErrors();
        double tss = sr.getTotalSumSquares();

        System.out.printf("y = %.6fx + %.4f%n", m, b);//回归线
        System.out.printf("r = %.6f%n", r);
        System.out.printf("r2 = %.6f%n", r2);
        System.out.printf("EV = %.5f%n", tss - sse);//解释的变异
        System.out.printf("UV = %.4f%n", sse);//未解释的变异
        System.out.printf("TV = %.3f%n", tss);//总变异
    }

    public static SimpleRegression getData(String data) {
        SimpleRegression sr = new SimpleRegression();
        try {
            Scanner fileScanner = new Scanner(new File(data));
            fileScanner.nextLine();
            int n = fileScanner.nextInt();
            fileScanner.nextLine();
            fileScanner.nextLine();
            for(int i = 0; i < n; i ++) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("\\t");
                double x = lineScanner.nextDouble();
                double y = lineScanner.nextDouble();
                sr.addData(x, y);
            }
        } catch(Exception e) {
            System.err.println(e);
        }
        return sr;
    }
}
