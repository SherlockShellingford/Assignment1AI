package agents.goals;

import agents.Goal;
import datatypes.Edge;
import datatypes.Vertex;
import environment.EnvironmentState;
import org.jgrapht.Graph;

public class RescuePeopleGoal implements Goal {
    @Override
    public boolean isGoalSucceeded() {
        Graph<Vertex, Edge> g = EnvironmentState.getInstance().getGraph();
        int needToRescue = 0;
        for (Vertex v : g.vertexSet()) {
            needToRescue += v.getNumberOfPeople();
        }
        return needToRescue == 0;
    }
}
