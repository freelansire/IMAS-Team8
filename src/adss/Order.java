package adss;

import weka.core.Instances;

/**
 * Abstract class as parent for training and testing orders
 */
abstract public class Order {

    /**
     * Dataset included in order (for training or prediction)
     */
    private Instances dataset;

    /**
     * Constructor
     */
    public Order() {
    }

    /**
     * Add dataset to the order
     *
     * @param dataset Transported dataset in this order
     */
    public void setDataset(Instances dataset) {
        this.dataset = dataset;
    }

    /**
     * Get transported dataset
     *
     * @return Transported dataset in this order
     */
    public Instances getDataset() {
        return this.dataset;
    }
}
