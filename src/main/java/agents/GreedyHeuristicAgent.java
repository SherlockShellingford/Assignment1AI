package agents;

import agents.state.State;
import datatypes.Edge;
import datatypes.Vertex;
import heuristics.Heuristic;
import heuristics.MSTHeuristic;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import java.util.*;

public class GreedyHeuristicAgent extends Agent {

    private Graph<Vertex, Edge> internal;
    private Map<Edge, GraphPath<Vertex, Edge>> edgeMap;
    private Heuristic heuristic;
    private Map<Integer, Long> vertexTime = new HashMap<>();
    private List<Edge> currentPath;

    public GreedyHeuristicAgent(Graph<Vertex, Edge> g, Vertex init) {
        internal = new DefaultDirectedWeightedGraph<>(Edge.class);
        edgeMap = new HashMap<>();
        List<Vertex> verticesInGraph = new LinkedList<>();
        verticesInGraph.add(init);
        internal.addVertex(init);
        for (Object v : g.vertexSet()) {
            Vertex vv = (Vertex) v;
            if (vv.getNumberOfPeople() > 0 && !vv.getId().equals(init.getId())) {
                verticesInGraph.add(vv);
                internal.addVertex(vv);
            }
        }
        for (Vertex v : verticesInGraph) {
            DijkstraShortestPath<Vertex, Edge> dijkstra = new DijkstraShortestPath<>(g);
            ShortestPathAlgorithm.SingleSourcePaths<Vertex, Edge> paths = dijkstra.getPaths(v);
            for (Vertex vv : verticesInGraph) {
                if (!v.equals(vv)) {
                    Edge e = new Edge();
                    edgeMap.put(e, paths.getPath(vv));
                    internal.addEdge(v, vv, e);
                    internal.setEdgeWeight(e, paths.getPath(vv).getWeight() / (vv.getNumberOfPeople() + 1)); // For minimal spanning tree
                    e.setComment("Path weight: " + paths.getPath(vv).getWeight());
                }
            }
        }
        this.heuristic = new MSTHeuristic(internal);
        this.state = new State(init, vertexTime);
        for (Vertex v : this.internal.vertexSet()) {
            Long time = vertexTime.getOrDefault(v.getId(), 1l);
            vertexTime.put(v.getId(), time++);
        }
    }

    @Override
    public Action processNextAction(Perception perception) {
        if (currentPath == null || currentPath.size() == 0) {
            for (Vertex v : this.internal.vertexSet()) {
                Long time = vertexTime.getOrDefault(v.getId(), 1l);
                vertexTime.put(v.getId(), time++);
            }
            Edge edge = null;
            double min = Double.POSITIVE_INFINITY;
            for (Edge e : this.internal.edgesOf(state.getCurrentVertex())) {
                double h = heuristic.h(e.getSource(), e.getTarget(), vertexTime);
                if (h < min) {
                    edge = e;
                    min = h;
                }
            }
            currentPath = new ArrayList<>(edgeMap.get(edge).getEdgeList());
        }
        int index = 0;
        for (int i=0;i<currentPath.size();i++) {
            if (currentPath.get(i).getSource().equals(state.getCurrentVertex()) || currentPath.get(i).getTarget().equals(state.getCurrentVertex())) {
                index = i;
                break;
            }
        }
        Edge e = currentPath.get(index);
        currentPath.remove(e);
        Vertex v = e.getTarget().equals(state.getCurrentVertex()) ? e.getSource() : e.getTarget();
        vertexTime.put(v.getId(), 1l);
        return new GraphMovementAction(e.getTarget().equals(state.getCurrentVertex()) ? e.getSource() : e.getTarget());
    }

    @Override
    public void updateState(Action action) {
        Vertex v = ((GraphMovementAction)action).getToVertex();
        v.setNumberOfPeople(0);
        this.getSeq().add(action);
        this.state = new State(v, vertexTime);
    }

    public Graph<Vertex, Edge> getInternal() {
        return internal;
    }

    public Map<Edge, GraphPath<Vertex, Edge>> getEdgeMap() {
        return edgeMap;
    }

}
