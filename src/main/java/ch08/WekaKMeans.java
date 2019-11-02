package ch08;
import java.util.ArrayList;
import weka.clusterers.SimpleKMeans;
import weka.core.*;

public class WekaKMeans {
    private static final double[][] DATA = {{1,1}, {1,3}, {1,5}, {2,6}, {3,2},
            {3,4}, {4,3}, {5,6}, {6,3}, {6,4}, {7,1}, {7,5}, {7,6}};
    private static final int M = DATA.length;  // number of points
    private static final int K = 3;            // number of clusters

    public static void main(String[] args) {
        Instances dataset = load(DATA);
        SimpleKMeans skm = new SimpleKMeans();
        System.out.printf("%d clusters:%n", K);
        try {
            skm.setNumClusters(K);
            skm.buildClusterer(dataset);
            for (int i = 0; i < dataset.numInstances(); i++) {
                Instance instance = dataset.instance(i);
                System.out.printf("(%.0f,%.0f): %s%n",
                        instance.value(0), instance.value(1),
                        skm.clusterInstance(instance));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private static Instances load(double[][] data) {
        FastVector attributes = new FastVector();
        attributes.addElement(new Attribute("X"));
        attributes.addElement(new Attribute("Y"));
        Instances dataset = new Instances("Dataset", attributes, M);
        for (double[] datum : data) {
            Instance instance = new SparseInstance(2);
            instance.setValue(0, datum[0]);
            instance.setValue(1, datum[1]);
            dataset.add(instance);
        }
        return dataset;
    }

}
