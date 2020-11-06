package agents.heuristic;

import agents.Agent;
import datatypes.Edge;
import datatypes.Vertex;
import heuristics.Heuristic;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

import java.util.Map;

public abstract class HeuristicAgent extends Agent {

    protected Graph<Vertex, Edge> internal;
    protected Map<Edge, GraphPath<Vertex, Edge>> edgeMap;
    protected Heuristic heuristic;

    public HeuristicAgent(Graph<Vertex, Edge> g, Vertex init) {

    }
}
