package heuristics;

import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;

import java.util.Map;
import java.util.Set;

public class MSTHeuristic implements Heuristic {
    Graph<Vertex, Edge> g;
    double normalize;

    public MSTHeuristic(Graph<Vertex, Edge> originalGraph) {
        g = new DefaultUndirectedWeightedGraph(Edge.class);
        for (Vertex v : originalGraph.vertexSet()) {
            g.addVertex(v);

        }
        KruskalMinimumSpanningTree<Vertex, Edge> krk = new KruskalMinimumSpanningTree<>(originalGraph);
        Set<Edge> edgeSet = krk.getSpanningTree().getEdges();
        int numOfEdges = originalGraph.vertexSet().size() - 1;
        double sumEdgeWeight = 0;
        for (Edge e : edgeSet) {
            g.addEdge(originalGraph.getEdgeTarget(e), originalGraph.getEdgeSource(e));
            sumEdgeWeight += g.getEdgeWeight(e);

        }
        normalize = sumEdgeWeight / numOfEdges;

    }

    @Override
    public double h(Vertex source, Vertex target, Map<Integer, Long> vertexTime) {
        if (g.getEdge(source, target) == null) {
            return Double.POSITIVE_INFINITY;
        }
        if (target.getNumberOfPeople() > 0) {
            return 0;
        }
        return normalize;
    }

}
