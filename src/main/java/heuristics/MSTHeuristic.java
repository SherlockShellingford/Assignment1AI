package heuristics;

import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Set;

public class MSTHeuristic {
    Graph<Vertex, DefaultWeightedEdge> g;
    double normalize;
    public MSTHeuristic(Graph<Vertex,DefaultWeightedEdge>  originalGraph){
        g=new DefaultUndirectedWeightedGraph(DefaultWeightedEdge.class);
        KruskalMinimumSpanningTree<Vertex,DefaultWeightedEdge> krk=new KruskalMinimumSpanningTree<>(originalGraph);
        Set<DefaultWeightedEdge> edgeSet = krk.getSpanningTree().getEdges();
        int numOfEdges=originalGraph.vertexSet().size()-1;
        double sumEdgeWeight=0;
        for (DefaultWeightedEdge e:
             edgeSet) {
            g.addEdge(originalGraph.getEdgeTarget(e), originalGraph.getEdgeSource(e));
            sumEdgeWeight+=g.getEdgeWeight(e);

        }
        normalize=sumEdgeWeight/numOfEdges;

    }

    public double h(Vertex source,Vertex target){
        if(g.getEdge(source,target)==null){
            return Double.POSITIVE_INFINITY;
        }
        if(target.getNumberOfPeople()>0){
            return 0;
        }
        return normalize;
    }

}
