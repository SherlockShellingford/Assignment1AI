package agents;

import datatypes.Edge;
import datatypes.Vertex;

public class State {
    private Vertex currentVertex;
    private Edge currentEdge;
    private int waitingTime = 0;

    public State(Vertex currentVertex, Edge currentEdge, int waitingTime) {
        this.currentVertex = currentVertex;
        this.currentEdge = currentEdge;
        this.waitingTime = waitingTime;
    }

    public Edge getCurrentEdge() {
        return currentEdge;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public Vertex getCurrentVertex() {
        return currentVertex;
    }
}
