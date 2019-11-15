package adss;

import jade.core.Agent;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;

import java.util.ArrayList;

public class ManagerAgent extends Agent {

    /**
     * Amount created classifier agents
     */
    int numCreatedClassifiers = 0;

    /**
     * Setup agent
     */
    protected void setup() {
        // TODO Setup all behaviours
        //  1. Reaction on training order from UserAgent
        //  2. Reaction on prediction order from UserAgenti

        // TEST
        for (int i = 0; i < 10; i++) {
            this.createClassifierAgent();
        }
    }

    /**
     * Create a classifier agent
     */
    private void createClassifierAgent() {
        ContainerController cc = this.getContainerController();
        try {
            AgentController ac = cc.createNewAgent("c" + this.numCreatedClassifiers, ClassifierAgent.class.getName(), null);
            ac.start();
            this.numCreatedClassifiers++;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
