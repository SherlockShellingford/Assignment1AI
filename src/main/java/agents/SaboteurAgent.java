package agents;

import datatypes.Edge;

import java.util.Iterator;
import java.util.Set;

public class SaboteurAgent extends Agent {
    private boolean sabotageTurn=true;

    public GraphMovementAction processNextAction(Perception perception) {
        if(sabotageTurn) {
            Set<Edge> neighbors = perception.getGraph().edgesOf(perception.getVertex());

            Iterator<Edge> it = neighbors.iterator();
            int currIndex = 0;
            double minimal = Double.POSITIVE_INFINITY;
            Edge toBlock = null;
            while (it.hasNext()) {
                Edge e = it.next();
                double weight = e.getWeight();
                if (minimal > weight || (minimal == weight && (toBlock == null || e.getId() < toBlock.getId()))) {
                    minimal = weight;
                    toBlock = e;
                }

            }
            if (minimal == Double.POSITIVE_INFINITY) {
                return new agents.GraphMovementAction(true);
            }
            sabotageTurn = false;
            toBlock.setBlocked(true,perception.getGraph());
            return new agents.GraphMovementAction(perception.getVertex());
        }
        else{
            Set<Edge> neighbors = perception.getGraph().edgesOf(perception.getVertex());

            Iterator<Edge> it = neighbors.iterator();
            int currIndex = 0;
            double minimal = Double.POSITIVE_INFINITY;
            Edge next = null;
            while (it.hasNext()) {
                Edge e = it.next();
                double weight = e.getWeight();
                if (minimal > weight || (minimal == weight && (next == null) || e.getId() < next.getId() )) {
                    minimal = weight;
                    next = e;
                }

            }
            if (minimal == Double.POSITIVE_INFINITY) {
                return new agents.GraphMovementAction(true);
            }
            sabotageTurn = true;
            if(next.getSource().equals(perception.getVertex())){
                return new agents.GraphMovementAction(next.getTarget());
            }
            else{
                return new agents.GraphMovementAction(next.getSource());
            }
        }
    }

    @Override
    public void updateState(Action action) {

    }
}
