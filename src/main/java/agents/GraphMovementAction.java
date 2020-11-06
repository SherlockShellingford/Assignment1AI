package agents;

import agents.state.State;
import datatypes.Vertex;

public class GraphMovementAction extends Action {
    private Vertex toVertex;
    private boolean terminate=false;
    private State state;
    private int originalNumberOfPeople;

    public GraphMovementAction(Vertex toVertex) {
        this.toVertex = toVertex;
        this.originalNumberOfPeople = toVertex.getNumberOfPeople();
    }

    public GraphMovementAction(Vertex toVertex, State state) {
        this.toVertex = toVertex;
        this.state = state;
        this.originalNumberOfPeople = toVertex.getNumberOfPeople();
    }

    public GraphMovementAction(boolean terminate) {
        this.terminate = terminate;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public void setToVertex(Vertex toVertex) {
        this.toVertex = toVertex;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Vertex - " + toVertex.getId() + " with " + originalNumberOfPeople + " people";
    }
}
