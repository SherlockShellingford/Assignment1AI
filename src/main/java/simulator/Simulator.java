package simulator;

import agents.Action;
import agents.Agent;
import environment.EnvironmentState;

public class Simulator {

    boolean terminate = false;
    public void start() {
        do {
            for (Agent a : EnvironmentState.getInstance().getAgents()) {
                // TODO get perception
                // TODO get next action
                Action action = a.processNextAction(null);
                a.updateState(action);
                // TODO update state
                if (a.getGoal().isGoalSucceeded()) {
                    terminate = true;
                    for (Action aa : a.getSeq()) {
                        System.out.println(aa);
                    }
                }
            }
        }while (!needTermination());
        System.out.println("Done");
    }

    private boolean needTermination() {
        return terminate;
    }
}
