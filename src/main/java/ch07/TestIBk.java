package ch07;

import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.*;

public class TestIBk {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances instances = source.getDataSet();
        instances.setClassIndex(3);
        IBk ibk = new IBk();
        ibk.buildClassifier(instances);

        for(int i = 0; i < instances.numInstances(); i++) {
            Instance instance = instances.instance(i);
            double prediction = ibk.classifyInstance(instance);
            System.out.printf("%4.0f%4.0f%n", instance.classValue(), prediction);
        }
    }
}
