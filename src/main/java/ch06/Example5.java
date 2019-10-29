package ch06;

import org.apache.commons.math3.linear.*;

public class Example5 {
    static double[] x = {10, 9, 12, 10, 9, 10, 8, 11};
    static double[] y = {59, 57, 61, 52, 48, 55, 51, 62};
    static double[] z = {71, 68, 76, 56, 57, 77, 55, 67};
    static int n = z.length;  // 8

    public static void main(String[] args) {
        double[][] a = new double[3][3];
        double[] w = new double[3];
        deriveNormalEquations(a, w);
        printNormalEquations(a, w);
        double[] b = solveNormalEquations(a, w);
        printResults(b);
    }

    public static void deriveNormalEquations(double[][] a, double[] w) {
        int n = y.length;
        for(int i = 0; i < n; i ++) {
            double xi = x[i];
            double yi = y[i];
            double zi = z[i];
            a[0][0] = n;
            a[0][1] = a[1][0] += xi;
            a[0][2] = a[2][0] += yi;
            a[1][1] += xi*xi;
            a[1][2] = a[2][1] += xi*yi;
            a[2][2] += yi*yi;
            w[0] += zi;
            w[1] += xi*zi;
            w[2] += yi*zi;
        }
    }

    public static void printNormalEquations(double[][] a, double[] w) {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%6.0fx0 + %4.0fx1 + %5.0fx2 = %5.0f%n",
                    a[i][0], a[i][1], a[i][2], w[i]);
        }
    }

    private static double[] solveNormalEquations(double[][] a, double[] w) {
        RealMatrix m = new Array2DRowRealMatrix(a, false);
        LUDecomposition lud = new LUDecomposition(m);
        DecompositionSolver solver = lud.getSolver();
        RealVector v = new ArrayRealVector(w, false);
        return solver.solve(v).toArray();
    }

    private static void printResults(double[] b) {
        System.out.printf("f(s, t) = %.2f + %.2fs + %.2ft%n", b[0], b[1], b[2]);
        System.out.printf("f(10, 59) = %.1f%n", f(10, 59, b));
        System.out.printf("f(9, 57) = %.1f%n", f(9, 57, b));
        System.out.printf("f(11, 64) = %.1f%n", f(11, 64, b));
    }

    private static double f(double s, double t, double[] b) {
        return b[0] + b[1]*s + b[2]*t;
    }

}
