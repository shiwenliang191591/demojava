package ch07;

import java.util.List;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Prediction;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;
import weka.core.converters.ConverterUtils.DataSource;


public class TestWekaBayes {
    public static void main(String[] args) throws Exception {
//        ConverterUtils.DataSource source = new ConverterUtils.DataSource("data/AnonFruit.arff");
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances train = source.getDataSet();
        train.setClassIndex(3);  // target attribute: (Sweet)
        //build model
        NaiveBayes model=new NaiveBayes();
        model.buildClassifier(train);

        //use
        Instances test = train;
        Evaluation eval = new Evaluation(test);
        eval.evaluateModel(model,test);
        FastVector predictions = eval.predictions();
        int k = 0;
        for(int i = 0; i < test.numInstances(); i++)
        {
            Instance instance = test.instance(i);
            double actual = instance.classValue();
            double prediction = eval.evaluateModelOnce(model, instance);
            System.out.printf("%2d.%4.0f%4.0f", ++k, actual, prediction);
            System.out.println(prediction != actual? " *": "");
        }
    }

}
