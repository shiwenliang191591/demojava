package datascience.ch04;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class WekaArffTest {
    public static void main(String[] args) throws Exception {
        ArrayList<Attribute> attributes;
        ArrayList<String> classVals;
        Instances data;
        double[] values;

        //创建属性
        attributes = new ArrayList<Attribute>();

        //数值属性
        attributes.add(new Attribute("age"));

        //字符串属性
        ArrayList<String> empty = null;
        attributes.add(new Attribute("name", empty));

        //日期属性
        attributes.add(new Attribute("dob", "yyyy-MM-dd"));
        classVals = new ArrayList<String>();
        for(int i = 0; i < 5; i ++) {
            classVals.add("class" + (i + 1));
        }

        Attribute classVal = new Attribute("class", classVals);
        attributes.add(classVal);

        //创建实例对象
        data = new Instances("MyRelation", attributes, 0);

        //填充日期
        //第一个实例
        values = new double[data.numAttributes()];
        values[0] = 35;
        values[1] = data.attribute(1).addStringValue("Harry Potter");
        values[2] = data.attribute(2).parseDate("1986-07-05");
        values[3] = classVals.indexOf("class1");

        //添加
        data.add(new DenseInstance(1.0, values));

        //第二个实例
        values = new double[data.numAttributes()];
        values[0] = 35;
        values[1] = data.attribute(1).addStringValue("John");
        values[2] = data.attribute(2).parseDate("1983-07-05");
        values[3] = classVals.indexOf("class3");

        //添加
        data.add(new DenseInstance(1.0, values));

        //把arff文件写到磁盘
        BufferedWriter writter = new BufferedWriter(new FileWriter("/Users/shiwenliang/Downloads/project/dataanalysis/demojava/data/train0122.arff"));
        writter.write(data.toString());
        writter.close();

        System.out.println(data);

    }
}
