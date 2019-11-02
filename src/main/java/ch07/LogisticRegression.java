package ch07;

import org.apache.commons.math3.analysis.function.*;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class LogisticRegression {
    static int n = 6;
    static double[] x = {5, 15, 25, 35, 45, 55};
    static double[] p = {2./6, 2./5, 4./8, 5./9, 3./5, 4./5};
    static double[] y = new double[n];

    public static void main(String[] args) {
        Logit logit = new Logit();
        for(int i = 0; i < n; i ++) {
            y[i] = logit.value(p[i]);
        }

        double[][] data = new double[n][n];
        for (int i = 0; i < n; i ++) {
            data[i][0] = x[i];
            data[i][1] = y[i];
        }

        SimpleRegression sr = new SimpleRegression();
        sr.addData(data);
        System.out.printf("slope = %.4f, intercept = %.4f%n%n", sr.getSlope(), sr.getIntercept());

        for (int i = 0; i < n; i ++) {
            System.out.printf("x = %2.0f, y = %7.4f%n", x[i], sr.predict(x[i]));
        }

        System.out.println();

        Sigmoid sigmoid = new Sigmoid();
        for (int i = 0; i < n; i ++) {
            double p = sr.predict(x[i]);
            System.out.printf("x = %2.0f, y = %6.4f%n", x[i], sigmoid.value(p));
        }
    }
}
