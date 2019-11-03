package ch09;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Filter1 {
    private static int m;
    private static int n;

    public static void main(String[] args) {
        File purchasesFile = new File("data/Purchases1.dat");
        File utilityFile = new File("data/Utility1.dat");
        File similarityFile = new File("data/Similarity1.dat");
        try {
            int[][] u = computeUtilityMatrix(purchasesFile);
            storeUtilityMatrix(u, utilityFile);
            double[][] s = computeSimilarityMatrix(u);
            storeSimilarityMatrix(s, similarityFile);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static int[][] computeUtilityMatrix(File file) throws Exception{
        Scanner in = new Scanner(file);
        m = in.nextInt(); in.nextLine();
        n = in.nextInt(); in.nextLine();
        in.nextLine(); in.nextLine(); in.nextLine();

        int[][] u = new int[m + 1][n + 1];
        while(in.hasNext()) {
            int i = in.nextInt();
            int j = in.nextInt();
            u[i][j] = 1;
        }
        in.close();
        return u;
    }

    public static void storeUtilityMatrix(int[][] u, File file) throws Exception {
        PrintWriter out = new PrintWriter(file);
        out.printf("%d users%n", m);
        out.printf("%d items%n", n);
        for(int i = 1; i <= m; i ++) {
            for(int j = 1; j <= n; j ++) {
                out.printf("%2d", u[i][j]);
            }
            out.println();
        }
        out.close();
    }

    /**
     *  计算并存储相似性矩阵
     */
    public static double[][] computeSimilarityMatrix(int[][] u) {
        double[][] s = new double[n + 1][n + 1];
        for(int j = 1; j <= n; j ++) {
            for(int k = 1; k <= n; k ++) {
                s[j][k] = cosine(u, j, k);
            }
        }
        return s;
    }

    public static void storeSimilarityMatrix(double[][] s, File file) throws Exception {
        PrintWriter out = new PrintWriter(file);
        out.printf("%d items%n", n);
        for(int i = 1; i <= n; i ++) {
            for(int j = 1; j <= n; j ++) {
                out.printf("%6.2f", s[i][j]);
            }
            out.println();
        }
        out.close();
    }

    /**
     * 计算相似性
     * @param u
     * @param j
     * @param k
     * @return
     */
    public static double cosine(int[][] u, int j, int k) {
        double denominator = norm(u, j) * norm(u, k);
        return (denominator == 0 ? 0 : dot(u, j, k) / denominator);
    }

    public static double dot(int[][] u, int j, int k) {
        double sum = 0.0;
        for(int i = 0; i <= m; i ++) {
            sum += u[i][j] * u[i][k];
        }
        return sum;
    }

    public static double norm(int[][] u, int j) {
        return Math.sqrt(dot(u, j, j));
    }

}
