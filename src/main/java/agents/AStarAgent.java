package agents;

import agents.state.State;
import datatypes.Edge;
import datatypes.Vertex;
import environment.EnvironmentState;
import heuristics.Heuristic;
import heuristics.MSTHeuristic;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import java.util.*;

public class AStarAgent extends Agent {

    private static final int MAX_TIME = 10000;

    private Graph<Vertex, Edge> internal;
    private Map<Integer, Long> vertexTime = new HashMap<>();
    private List<State> pathStates;
    private List<Edge> currentPath;
    private Heuristic heuristic;

    public AStarAgent(Graph<Vertex, Edge> g, Vertex init) {
        this.state = new State(init, vertexTime, Arrays.asList(init));
        internal = new DefaultDirectedWeightedGraph<>(Edge.class);
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
                    internal.addEdge(v, vv, e);
                    internal.setEdgeWeight(e, paths.getPath(vv).getWeight() / (vv.getNumberOfPeople() + 1)); // For minimal spanning tree
                    e.setComment("Path weight: " + paths.getPath(vv).getWeight());
                }
            }
        }
        this.heuristic = new MSTHeuristic(internal);
        this.AStar();
    }

    private void AStar() {
        Map<State, State> childFather = new HashMap<>();
        childFather.put(this.state, this.state);
        Map<State, Double> pathWeight = new HashMap<>();
        PriorityQueue<State> open = new PriorityQueue<>((state1, state2) -> {
            if (childFather.get(state1) == null) {
                return 1;
            }
            if (childFather.get(state2) == null) {
                return -1;
            }
            double fState1 = f(pathWeight.getOrDefault(state1, 0.0), this.heuristic.h(this.state, state1, state1.getVertexTime()));
            double fState2 = f(pathWeight.getOrDefault(state2, 0.0), this.heuristic.h(this.state, state2, state2.getVertexTime()));
            if (fState1 > fState2) return 1;
            else if (fState2 > fState1) return -1;
            return 0;
        });
        open.add(this.state);
        pathWeight.put(this.state, 0.0);
        LinkedHashSet<State> closed = new LinkedHashSet<>();
        int count = 0;
        do {
            if (open.size() == 0) {
                System.out.println("Failed");
                closed = null;
                break;
            }
            this.state = open.poll();
            if (this.state.getVisited().size() == internal.vertexSet().size()) {
                System.out.println("Found path");
                break;
            }
            if (count >= MAX_TIME) {
                System.out.println("Failed");
                closed = null;
                break;
            }
            closed.add(this.state);
            open.addAll(getChildren(this.state, childFather, pathWeight));
            for (State s : open) {
                System.out.println(s.getCurrentVertex().getId() + " - " + this.state.getCurrentVertex().getId() + " h=" + this.heuristic.h(this.state, s, s.getVertexTime()));
            }
            System.out.println("====================");
            count++;
        }
        while (true);
        pathStates = new ArrayList<>();
        pathStates.add(this.state);
        while (!this.state.equals(childFather.get(this.state))) {
            pathStates.add(childFather.get(this.state));
            this.state = childFather.get(this.state);
        }
        Collections.reverse(pathStates);
        this.state = pathStates.remove(0);
    }

    private List<State> getChildren(State s, Map<State, State> childFather, Map<State, Double> pathWeight) {
        Set<Edge> edges = internal.edgesOf(s.getCurrentVertex());
        List<State> children = new ArrayList<>();
        for (Integer id : vertexTime.keySet()) {
            vertexTime.put(id, vertexTime.get(id) + 1);
        }
        for (Edge e : edges) {
            Vertex c = e.getSource().equals(s.getCurrentVertex()) ? e.getTarget() : e.getSource();
            vertexTime.put(c.getId(), 1l);
            List<Vertex> visited = new ArrayList<>(s.getVisited());
            visited.add(e.getTarget().equals(s.getCurrentVertex()) ? e.getSource() : e.getTarget());
            State state = new State(e.getTarget().equals(s.getCurrentVertex()) ? e.getSource() : e.getTarget(),
                    vertexTime, visited);
            children.add(state);
            childFather.put(state, s);
            pathWeight.put(state, pathWeight.get(s) + e.getWeight());
        }
        return children;
    }

    private double f(double g, double h) {
        return g + h;
    }

    @Override
    public Action processNextAction(Perception perception) {

        if (currentPath == null || currentPath.size() == 0) {
            Graph<Vertex, Edge> g = EnvironmentState.getInstance().getGraph();
            DijkstraShortestPath<Vertex, Edge> dijkstra = new DijkstraShortestPath<>(g);
            State next = this.pathStates.remove(0);
            GraphPath<Vertex, Edge> path = dijkstra.getPath(this.state.getCurrentVertex(), next.getCurrentVertex());
            currentPath = new ArrayList<>(path.getEdgeList());
        }
        int index = 0;
        for (int i = 0; i < currentPath.size(); i++) {
            if (currentPath.get(i).getSource().equals(state.getCurrentVertex()) || currentPath.get(i).getTarget().equals(state.getCurrentVertex())) {
                index = i;
                break;
            }
        }
        Edge e = currentPath.get(index);
        currentPath.remove(e);
        return new GraphMovementAction(e.getTarget().equals(state.getCurrentVertex()) ? e.getSource() : e.getTarget());
    }

    @Override
    public void updateState(Action action) {
        Vertex v = ((GraphMovementAction) action).getToVertex();
        v.setNumberOfPeople(0);
        this.getSeq().add(action);
        this.state = new State(v, vertexTime);
    }


}
