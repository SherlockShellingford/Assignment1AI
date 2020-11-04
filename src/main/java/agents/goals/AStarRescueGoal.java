package agents.goals;

import datatypes.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStarRescueGoal implements Goal {

    Map<Integer, Boolean> toRescue;

    public AStarRescueGoal(List<Vertex> vertices) {
        toRescue = new HashMap<>();
        for (Vertex v : vertices) {
            toRescue.put(v.getId(), true);
        }
    }

    @Override
    public boolean isGoalSucceeded() {
        for (Boolean b : toRescue.values()) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    public void visit(Vertex v) {
        if(toRescue.keySet().contains(v.getId())) {
            toRescue.put(v.getId(), false);
        }
    }
}
