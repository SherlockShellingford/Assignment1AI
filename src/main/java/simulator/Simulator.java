package simulator;

import agents.Action;
import agents.Agent;
import environment.EnvironmentState;

import java.util.ArrayList;
import java.util.List;

public class Simulator {

    boolean terminate = false;

    public void start() {
        terminate = false;
        do {
            List<Agent> agentsToRemove = new ArrayList<>();
            for (Agent a : EnvironmentState.getInstance().getAgents()) {
                if (a.isFailed()) {
                    agentsToRemove.add(a);
                    continue;
                }
                Action action = a.processNextAction(null);
                a.updateState(action);
                System.out.print("Move to: ");
                System.out.println(action);
                if (a.getGoal().isGoalSucceeded()) {
                    terminate = true;
//                    for (Action aa : a.getSeq()) {
//                    }
                    agentsToRemove.add(a);
                }
            }
            EnvironmentState.getInstance().getAgents().removeAll(agentsToRemove);
            agentsToRemove.clear();
        }while (!needTermination());
        System.out.println("Done");
    }

    private boolean needTermination() {
        return terminate;
    }
}
