package datascience.ch04;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;

import java.io.*;

public class WekaTrainTest {
    NaiveBayes nb;
    Instances train, test, labeled;

    public void loadModel(String modelPath) {
        try {
            nb = (NaiveBayes) SerializationHelper.read(modelPath);
        } catch(Exception e) {

        }
    }

    public void loadDatasets(String training, String testing) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(training));
            train = new Instances(reader);
            train.setClassIndex(train.numAttributes() - 1);
        } catch(Exception e) {

        }

        try {
            reader = new BufferedReader(new FileReader(testing));
            test = new Instances(reader);
            test.setClassIndex(test.numAttributes() - 1);
        } catch(Exception e) {

        }

        try {
            reader.close();
        } catch(Exception e) {

        }
    }

    public void classify() {
        try {
            nb.buildClassifier(train);
        } catch(Exception e) {

        }

        labeled = new Instances(test);

        for(int i = 0; i < test.numInstances(); i ++) {
            double clsLabel;
            try {
                clsLabel = nb.classifyInstance(test.instance(i));
                labeled.instance(i).setClassValue(clsLabel);
                double[] predict = nb.distributionForInstance(test.instance(i));
                double predictPro = predict[1];
                System.out.println(predictPro);
            } catch(Exception e) {

            }
        }
    }

    public void writeArff(String outarff) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outarff));
            writer.write(labeled.toString());
            writer.close();
        } catch(Exception e) {

        }
    }

    public static void main(String[] args) {
        WekaTrainTest test = new WekaTrainTest();
        test.loadModel("/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/nb.model");
        test.loadDatasets("/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/iris.arff","/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/iris-test.arff");
        test.classify();
        test.writeArff("/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/trainarff.arff");
    }
}
