package agents;

import datatypes.Edge;
import datatypes.Vertex;

import java.util.*;

public class State {
    private Vertex currentVertex;
    private Edge currentEdge;
    private int waitingTime = 0;
    private Map<Integer, Long> vertexTime;
    private Set<Vertex> visited;

    public State(Vertex currentVertex, Edge currentEdge, int waitingTime, Map<Integer, Long> vertexTime) {
        this.currentVertex = currentVertex;
        this.currentEdge = currentEdge;
        this.waitingTime = waitingTime;
        this.vertexTime = new HashMap<>(vertexTime);
    }

    public State(Vertex currentVertex, Edge currentEdge, int waitingTime, Map<Integer, Long> vertexTime, List<Vertex> visited) {
        this.currentVertex = currentVertex;
        this.currentEdge = currentEdge;
        this.waitingTime = waitingTime;
        this.vertexTime = new HashMap<>(vertexTime);
        this.visited = new HashSet<>(visited);
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

    public Map<Integer, Long> getVertexTime() {
        return vertexTime;
    }

    public void setVertexTime(Map<Integer, Long> vertexTime) {
        this.vertexTime = vertexTime;
    }

    public Set<Vertex> getVisited() {
        return visited;
    }

    public void setVisited(Set<Vertex> visited) {
        this.visited = visited;
    }
}
