package ch07;

public class ComputeGain {

    public static double g(int[] m, int[] n) {
        int sm = 0, sn = 0;
        double nsh = 0.0;
        for(int i = 0; i < m.length; i++) {
            sm += m[i];
            sn += n[i];
            nsh += n[i] * h(m[i], n[i]);
        }
        return h(sm, sn) - nsh / sn;
    }

    public static double h(int m, int n) {
        if(m == 0 || m == n) {
            return 0;
        }
        double p = (double) m / n, q = 1 - p;
        return -p * lg(p) - q * lg(q);
    }

    public static double lg(double x) {
        return Math.log(x) / Math.log(2);
    }

    public static void main(String [] args) {
        System.out.printf("h(11,16) = %.4f%n", h(11, 16));
        System.out.printf("Gain(Size):");
        System.out.printf("\th(3,5) = %.4f%n", h(3, 5));
        System.out.printf("\th(6,7) = %.4f%n", h(6, 7));
        System.out.printf("\th(2,4) = %.4f%n", h(2, 4));
        System.out.printf("\tg({3,6,2},{5,7,4}) = %.4f%n", g(new int[]{3, 6, 2}, new int[]{5, 7, 4}));
        System.out.printf("Gain(Color):");
        System.out.printf("\th(3,4) = %.4f%n", h(3, 4));
        System.out.printf("\th(3,5) = %.4f%n", h(3, 5));
        System.out.printf("\th(2,3) = %.4f%n", h(2, 3));
        System.out.printf("\th(2,4) = %.4f%n", h(2, 4));
        System.out.printf("\tg({3,3,2,2},{4,5,3,4}) = %.4f%n", g(new int[]{3,3,2,2}, new int[]{4,5,3,4}));
    }
}
