package datascience.ch04;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.Random;

public class WekaCVTest {
    Instances iris = null;
    NaiveBayes nb;

    public void loadArff(String arffInput) {
        DataSource source = null;
        try {
            source = new DataSource(arffInput);
            iris = source.getDataSet();
            if(iris.classIndex() == -1)
                iris.setClassIndex(iris.numAttributes() - 1);
        } catch (Exception e1) {

        }
    }

    public void generateModel() {
        nb = new NaiveBayes();
        try {
            nb.buildClassifier(iris);
        } catch (Exception e) {

        }
    }

    public void saveModel(String modelPath) {
        try {
            SerializationHelper.write(modelPath, nb);
        } catch(Exception e) {

        }
    }

    public void crossValidate() {
        Evaluation eval = null;
        try {
            eval = new Evaluation(iris);
            eval.crossValidateModel(nb, iris, 10, new Random(1));
            System.out.println(eval.toSummaryString());
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        WekaCVTest test = new WekaCVTest();
        test.loadArff("/Users/shiwenliang/Downloads/tools/weka-3-6-15/data/iris.arff");
        test.generateModel();
        test.saveModel("/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/nb.model");
        test.crossValidate();
    }
}
