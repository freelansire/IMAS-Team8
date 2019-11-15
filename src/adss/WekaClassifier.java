package adss;

import weka.classifiers.Classifier;
import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Wrapper class to train and test Weka classifiers
 */
public class WekaClassifier {

    /**
     * Classifier to use for training and prediction
     */
    private Classifier classifier;

    /**
     * Amount of all predicted instances during lifetime of this classifier
     */
    private int numPredictedInstances = 0;
    private int numCorrectlyPredictedInstances = 0;

    /**
     * @param classifier Classifier from Weka library to use
     */
    public WekaClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    /**
     * Get internal used classifier algorithm
     *
     * @return Internal Weka classifier
     */
    public Classifier getClassifier() {
        return classifier;
    }

    /**
     * Train classifier using provided dataset in the order
     *
     * @param order Training order containing a dataset
     */
    public void train(TrainingOrder order) {
        // Unset amount of predicted instances
        this.numPredictedInstances = 0;
        this.numCorrectlyPredictedInstances = 0;
        // Get dataset
        Instances trainData = order.getDataset();
        try {
            // Train classifier
            this.classifier.buildClassifier(trainData);
        } catch (Exception e) {
            System.out.println("Not able to train classifier "
                    + this.classifier.getClass().getName() + ": "
                    + e.toString()
            );
        }
    }

    /**
     * Make classification of given instances in the prediction order
     *
     * @param order Prediction order containing a dataset
     * @return List of predicted labels with same value convention as class attribute
     */
    public ArrayList<String> predict(PredictionOrder order) {
        // Get dataset
        Instances testData = order.getDataset();

        // Get class attribute definition to gain label values
        Attribute label = testData.classAttribute();

        // Predict instance and collect labels
        ArrayList<String> predictedLabelValues = new ArrayList<String>();
        for (int i = 0; i < testData.numInstances(); i++) {
            try {
                Instance instance = testData.instance(i);
                double val = this.classifier.classifyInstance(instance);
                predictedLabelValues.add(label.value((int) val));

                // Correct amount of predicted instances
                this.numPredictedInstances++;

                // Correct prediction precision
                if (instance.classValue() == val) {
                    this.numCorrectlyPredictedInstances++;
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return predictedLabelValues;
    }

    /**
     * Get prediction precision recorded during the lifetime of this classifier
     *
     * @return Prediction precision
     */
    public double getPredictionPrecision() {
        if (this.numPredictedInstances < 1) {
            return 0.0;
        }
        return ((double) this.numCorrectlyPredictedInstances) / ((double) this.numPredictedInstances);
    }
}
