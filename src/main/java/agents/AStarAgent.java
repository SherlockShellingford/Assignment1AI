package agents;

import agents.goals.AStarRescueGoal;
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

public class AStarAgent extends Agent {

    private static final int MAX_TIME = 10000;

    private Graph<Vertex, Edge> internal;
    private Map<Edge, GraphPath<Vertex, Edge>> edgeMap;
    private Map<Integer, Long> vertexTime = new HashMap<>();
    private List<Edge> currentPath;
    private Heuristic heuristic;

    private AStarRescueGoal rescueGoal;

    public AStarAgent(Graph<Vertex, Edge> g, Vertex init) {
        this.state = new State(init, null, 0, vertexTime, Arrays.asList(init));
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

        this.rescueGoal = new AStarRescueGoal(verticesInGraph);

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
        this.Astar();
    }

    State s;

    private void Astar() {
        Map<State, State> childFather = new HashMap<>();
        childFather.put(this.state, this.state);
        Map<State, Double> pathWeight = new HashMap<>();
        PriorityQueue<State> open = new PriorityQueue<>(new StateComparator(pathWeight, childFather));
        open.add(this.state);
        pathWeight.put(this.state, 0.0);
        Set<State> closed = new HashSet<>();
        int count = 0;
        do {
            if (open.size() == 0) {
                System.out.println("Failed");
                break;
            }
            s = open.poll();
            if (s.getVisited().size() == internal.vertexSet().size()) {
                System.out.println("Goal");
                break;
            }
            if (count >= MAX_TIME) {
                System.out.println("Failed");
                return;
            }
//            if (/*!closed.contains(s) || */ isSmallerExists(s, closed, childFather, pathWeight)) {
                closed.add(s);
                System.out.println(s);
                this.rescueGoal.visit(s.getCurrentVertex());
                open.addAll(getChildren(s, childFather, pathWeight));
//            }
            count++;
        }
        while (true);
        System.out.println(closed);
    }

    private List<State> getChildren(State s, Map<State, State> childFather, Map<State, Double> pathWeight) {
        Set<Edge> edges = internal.edgesOf(s.getCurrentVertex());
        List<State> children = new ArrayList<>();
        for (Edge e : edges) {
            Long time = vertexTime.getOrDefault(e.getSource().getId(), 1l);
            vertexTime.put(e.getSource().getId(), time++);
            List<Vertex> visited = new ArrayList<>(s.getVisited());
            visited.add(e.getTarget().equals(s.getCurrentVertex()) ? e.getSource() : e.getTarget());
            State state = new State(e.getTarget().equals(s.getCurrentVertex()) ? e.getSource() : e.getTarget(),
                    e, 0, vertexTime, visited);
            children.add(state);
            childFather.put(state, s);
            pathWeight.put(state, pathWeight.get(s) + e.getWeight());
        }
        return children;
    }

    private boolean isSmallerExists(State s, Set<State> closed, Map<State, State> childFather, Map<State, Double> pathWeight) {
        System.out.print("Expanded ");
        System.out.println(s.getCurrentVertex());
        if (childFather.get(s) == null || closed.size() == 0) {
            return true;
        }
        double f_s = f(pathWeight.getOrDefault(s, 0.0), this.heuristic.h(s.getCurrentVertex(), childFather.get(s).getCurrentVertex(), s.getVertexTime()));
        for (State cs : closed) {
            double f_cs = f(pathWeight.getOrDefault(cs, 0.0), this.heuristic.h(cs.getCurrentVertex(), childFather.get(cs).getCurrentVertex(), cs.getVertexTime()));
            if (f_s < f_cs) {
                System.out.print("Child ");
                System.out.println(cs.getCurrentVertex());
                return true;
            }
        }
        return false;
    }

    private double f(double g, double h) {
        return g + h;
    }

    @Override
    public Action processNextAction(Perception perception) {

        return null;
    }

    @Override
    public void updateState(Action action) {
        Vertex v = ((GraphMovementAction)action).getToVertex();
        v.setNumberOfPeople(0);
        this.getSeq().add(action);
        this.state = new State(v, null, 0, vertexTime);
    }

    public class StateComparator implements Comparator<State> {

        private Map<State, Double> pathWeight;
        private Map<State, State> childFather;

        public StateComparator(Map<State, Double> pathWeight, Map<State, State> childFather) {
            this.pathWeight = pathWeight;
            this.childFather = childFather;
        }

        @Override
        public int compare(State o1, State o2) {
            if (childFather.get(o1) == null) {
                return 1;
            }
            if (childFather.get(o2) == null) {
                return -1;
            }
            double f_o1 = f(pathWeight.getOrDefault(o1, 0.0), AStarAgent.this.heuristic.h(s, o1, o1.getVertexTime()));
            double f_o2 = f(pathWeight.getOrDefault(o2, 0.0), AStarAgent.this.heuristic.h(s, o2, o2.getVertexTime()));
            if ( f_o1 > f_o2) return 1;
            else if (f_o2 > f_o1) return -1;
            return 0;
        }
    }
}
