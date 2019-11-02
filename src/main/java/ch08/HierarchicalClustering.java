package ch08;
import java.util.HashSet;
public class HierarchicalClustering {
    private static final double[][] DATA = {{1,1}, {1,3}, {1,5}, {2,6}, {3,2},
            {3,4}, {4,3}, {5,6}, {6,3}, {6,4}, {7,1}, {7,5}, {7,6}};
    private static final int M = DATA.length;  // number of points
    private static final int K = 3;            // number of clusters

    public static void main(String[] args) {
        HashSet<Cluster> clusters = load(DATA);
        for (int i = 0; i < M - K; i++) {
            System.out.printf("%n%2d clusters:%n", M-i-1);
            coalesce(clusters);
            System.out.println(clusters);
        }
    }

    private static HashSet<Cluster> load(double[][] data) {
        HashSet<Cluster> clusters = new HashSet();
        for (double[] datum : DATA) {
            clusters.add(new Cluster(datum[0], datum[1]));
        }
        return clusters;
    }

    private static void coalesce(HashSet<Cluster> clusters) {
        Cluster cluster1=null, cluster2=null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Cluster c1 : clusters) {
            for (Cluster c2 : clusters) {
                if (!c1.equals(c2) && Cluster.distance(c1, c2) < minDist) {
                    cluster1 = c1;
                    cluster2 = c2;
                    minDist = Cluster.distance(c1, c2);
                }
            }
        }
        clusters.remove(cluster1);
        clusters.remove(cluster2);
        clusters.add(Cluster.union(cluster1, cluster2));
    }
}
