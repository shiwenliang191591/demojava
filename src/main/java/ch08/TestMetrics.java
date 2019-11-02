package ch08;

import org.apache.commons.math3.ml.distance.*;

public class TestMetrics {
    public static void main(String [] args) {
        double[] x = {1, 3}, y = {5, 6};
        EuclideanDistance eD = new EuclideanDistance();
        System.out.printf("Euclidean distance = %.2f%n", eD.compute(x, y));

        ManhattanDistance md = new ManhattanDistance();
        System.out.printf("Manhattan distance = %.2f%n", md.compute(x, y));

        ChebyshevDistance cd = new ChebyshevDistance();
        System.out.printf("Chebyshev distance = %.2f%n", cd.compute(x, y));

        CanberraDistance cad = new CanberraDistance();
        System.out.printf("Canberra distance = %.2f%n", cad.compute(x, y));
    }
}
