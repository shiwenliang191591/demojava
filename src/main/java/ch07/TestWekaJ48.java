package ch07;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
public class TestWekaJ48 {
    public static void main(String [] args) throws Exception {
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances instances = source.getDataSet();
        instances.setClassIndex(3);  // target attribute: (Sweet)

        J48 j48 = new J48();  // an extension of ID3
        j48.setOptions(new String[]{"-U"});  // use unpruned tree
        j48.buildClassifier(instances);

        for(int i = 0; i < instances.numInstances(); i++)
        {
            Instance instance = instances.instance(i);
            double prediction = j48.classifyInstance(instance);
            System.out.printf("%4.0f%4.0f%n",
                    instance.classValue(), prediction);
        }

    }
}
