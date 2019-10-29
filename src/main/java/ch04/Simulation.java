package ch04;

import java.util.Random;

public class Simulation {
    static final Random RANDOM = new Random();
    static final int n = 5;
    static final int N = 1000000;

    public static void main(String [] args) {
        double[] dist = new double[n + 1];
        for(int i = 0; i < N; i ++) {
            int x = numRedDown(n);
            ++ dist[x];
        }

        for(int i = 0; i <= n; i ++) {
            System.out.printf("%4d%8.4f%n", i, dist[i] / N);
        }
    }

    static boolean redDown() {
        int m = RANDOM.nextInt(4);
        return (m == 0);
    }

    static int numRedDown(int n) {
        int numRed = 0;
        for (int i = 1; i < n; i ++) {
            if(redDown()) {
                ++ numRed;
            }
        }
        return numRed;
    }
}
