package agents.state;

import datatypes.Vertex;

import java.util.*;

public class State {

    protected Vertex currentVertex;
    protected Map<Integer, Long> vertexTime;
    protected Set<Vertex> visited;

    public State() {
    }

    public State(Vertex currentVertex, Map<Integer, Long> vertexTime) {
        this.currentVertex = currentVertex;
        this.vertexTime = new HashMap<>(vertexTime);
        this.visited = new HashSet<>();
    }

    public State(Vertex currentVertex, Map<Integer, Long> vertexTime, List<Vertex> visited) {
        this.currentVertex = currentVertex;
        this.vertexTime = new HashMap<>(vertexTime);
        this.visited = new HashSet<>(visited);
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

    public void setCurrentVertex(Vertex currentVertex) {
        this.currentVertex = currentVertex;
    }

    @Override
    public String toString() {
        return "State {\n" +
                "\tcurrentVertex = " + currentVertex +
                ", \n\tvertexTime = " + vertexTime +
//                ", \n\tvisited = " + visited +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(currentVertex, state.currentVertex) &&
                Objects.equals(vertexTime, state.vertexTime) &&
                Objects.equals(visited, state.visited);
    }

    @Override
    public int hashCode() {
        return currentVertex.getId();
    }
}
