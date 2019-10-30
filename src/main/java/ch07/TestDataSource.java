package ch07;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.*;

public class TestDataSource {
    public static void main(String [] args) throws Exception {
        DataSource source = new DataSource("data/fruit.arff");

        Instances instances = source.getDataSet();
        instances.setClassIndex(instances.numAttributes() - 1);
        System.out.println(instances.attribute(2));
        Instance instance = instances.instance(3);
        System.out.println(instance);
        System.out.println(instance.stringValue(0));
        System.out.println(instance.stringValue(2));
    }
}
