package agents;

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
    private Map<Integer, Long> vertexTime = new HashMap<>();
    private List<Edge> currentPath;
    private Heuristic heuristic;

    public GreedyHeuristicAgent(Graph<Vertex, Edge> g, Vertex init) {
        this.state = new State(init, null, 0);
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
//                    internal.setEdgeWeight(e, vv.getNumberOfPeople() / paths.getPath(vv).getWeight()); // For maximal spanning tree
                    internal.setEdgeWeight(e, paths.getPath(vv).getWeight() / (vv.getNumberOfPeople() + 1)); // For minimal spanning tree
                    e.setComment("Path weight: " + paths.getPath(vv).getWeight());
                }
            }
        }
        this.heuristic = new MSTHeuristic(internal);

    }

    @Override
    public Action processNextAction(Perception perception) {
        // choose neighbor
        // find minimum value of heuristic function
        if (currentPath == null || currentPath.size() == 0) {
            Edge edge = null;
            double min = Double.POSITIVE_INFINITY;
            for (Edge e : this.internal.edgesOf(state.getCurrentVertex())) {
                double h = heuristic.h(e.getSource(), e.getTarget());
                if (h < min) {
                    edge = e;
                    min = h;
                }
            }
            currentPath = new ArrayList<>(edgeMap.get(edge).getEdgeList());
        }
        Edge e = currentPath.get(0);
        System.out.println(currentPath.get(0));
        currentPath.remove(e);
        return new GraphMovementAction(e.getTarget().equals(state.getCurrentVertex()) ? e.getSource() : e.getTarget());
    }

    @Override
    public void updateState(Action action) {
        Vertex v = ((GraphMovementAction)action).getToVertex();
        v.setNumberOfPeople(0);
        Long time = vertexTime.getOrDefault(v.getId(), 0l);
        vertexTime.put(v.getId(), time++);
        this.state = new State(v, null, 0);
    }

    public Graph<Vertex, Edge> getInternal() {
        return internal;
    }

    public Map<Edge, GraphPath<Vertex, Edge>> getEdgeMap() {
        return edgeMap;
    }
}
