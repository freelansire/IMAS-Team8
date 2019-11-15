package adss;

import weka.classifiers.trees.J48;

/**
 * Factory service to get different Weka classifiers
 */
public class WekaService {

    /**
     * Creates a simple J48 classifier
     *
     * @return J48 classifier
     */
    public static WekaClassifier createClassifierJ48() {
        return new WekaClassifier(new J48());
    }
}
