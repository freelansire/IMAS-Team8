package adss;

/**
 * Represents T-order, which:
 *  - Is passed from UserAgent to ManagerAgent,
 *  - Then from ManagerAgent to ClassifierAgents
 *  - And finally backwards
 */
public class TrainingOrder extends Order {

    /**
     * Algorithm to use for training and also for prediction later.
     * This information must:
     *  - be set by UserAgent
     *  - then transported to ManagerAgent, which creates ClassifierAgents for this algorithm
     */
    private String algorithm = "J48";

    /**
     * Set classification algorithm name
     *
     * @param algorithm Classification algorithm name from configuration file
     */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Get classification algorithm name
     *
     * @return Classification algorithm name from configuration file
     */
    public String getAlgorithm() {
        return algorithm;
    }
}
