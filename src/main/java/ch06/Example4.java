package ch06;

import org.apache.commons.math3.linear.*;

/**
 * 正则方程组
 */
public class Example4 {

    static int n = 6;
    static double[] x = {20, 30, 40, 50, 60, 70};
    static double[] y = {52, 87, 136, 203, 290, 394};
    static double[][] a;
    static double[] b;

    public static void main(String [] args) {
        double[][] a = new double[3][3];
        double[] w = new double[3];
        deriveNormalEquations(a, w);
        printNormalEquations(a, w);
        solveNormalEquations(a, w);
        printResults(b);
    }

    /**
     * 正则方程的推导
     * @param a
     * @param w
     */
    public static void deriveNormalEquations(double[][] a, double[] w) {
        int n = y.length;
        for(int i = 0; i < n; i++) {
            double xi = x[i];
            double yi = y[i];
            a[0][0] = n;
            a[0][1] = a[1][0] += xi;
            a[0][2] = a[1][1] = a[2][0] += xi * xi;
            a[1][2] = a[2][1] += xi * xi * xi;
            a[2][2] = xi * xi * xi * xi;
            w[0] += yi;
            w[1] += xi * yi;
            w[2] += xi * xi * yi;
        }
    }

    public static void printNormalEquations(double[][] a, double[] w) {
        for(int i = 0; i < 3; i++) {
            System.out.printf("%8.0fb0 + %6.0fb1 + %8.0fb2 = %7.0f%n",
                    a[i][0], a[i][1], a[i][2], w[i]);
        }
    }

    /**
     * 求解正则方程组
     * @param a
     * @param w
     * @return
     */
    private static double[] solveNormalEquations(double[][] a, double[] w) {
        RealMatrix m = new Array2DRowRealMatrix(a, false);
        LUDecomposition lud = new LUDecomposition(m);
        DecompositionSolver solver = lud.getSolver();
        RealVector v = new ArrayRealVector(w, false);
        return solver.solve(v).toArray();
    }

    private static void printResults(double[] b) {
        System.out.printf("f(t) = %.2f + %.3ft + %.5ft^2%n", b[0], b[1], b[2]);
        System.out.printf("f(55) = %.1f%n", f(55, b));
    }

    private static double f(double t, double[] b) {
        return b[0] + b[1] * t + b[2] * t * t;
    }
}
