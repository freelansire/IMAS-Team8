package adss;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.util.Logger;

public class ClassifierAgent extends Agent {

    /**
     * Logger to use
     */
    private Logger myLogger = Logger.getMyLogger(getClass().getName());

    /**
     * Setup classifier agent:
     *  - Add waker behaviour (to instantiate correct Weka algorithm)
     *  - Add training behaviour
     *  - Add prediction behaviour
     */
    protected void setup() {
        // Prepare registration with the DF
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(this.getClass().getName());
        sd.setName(this.getName());
        dfd.setName(this.getAID());
        dfd.addServices(sd);

        // Register behaviours
        try {
            DFService.register(this, dfd);

            // TODO Waker behaviuor

            // Training behaviuor
            TrainingOrderBehaviour trainBehaviour = new  TrainingOrderBehaviour(this);
            this.addBehaviour(trainBehaviour);

            // TODO Prediction behaviuor

        } catch (FIPAException e) {
            myLogger.log(Logger.SEVERE, "Agent " + this.getLocalName() + " - Cannot register with DF", e);
            this.doDelete();
        }
    }


    /**
     * Training order behavior reacts on training orders from manager agent
     */
    private class TrainingOrderBehaviour extends CyclicBehaviour {

        public TrainingOrderBehaviour(Agent a) {
            super(a);
        }

        public void action() {
            ACLMessage msg = myAgent.receive();

            if(msg != null){
                // TODO Training of algorithm
            } else {
                this.block();
            }
        }
    }
}