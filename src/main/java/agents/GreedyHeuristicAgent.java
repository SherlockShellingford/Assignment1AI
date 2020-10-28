package agents;

import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GreedyHeuristicAgent {
    private Graph internal;
    public GreedyHeuristicAgent(Graph g, Vertex init){
        internal=new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        Map<DefaultWeightedEdge, GraphPath<Vertex,Edge>> edgeMap=new HashMap<>();
        List<Vertex> verticesInGraph=new LinkedList<>();
        verticesInGraph.add(init);
        for (Object v:
             g.vertexSet()) {
            Vertex vv=(Vertex)v;
            if(vv.getNumberOfPeople()>0&& !vv.getId().equals(init.getId())){
                verticesInGraph.add(vv);
            }

        }
        for (Vertex v:
             verticesInGraph) {
            DijkstraShortestPath<Vertex, Edge> dijkstra = new DijkstraShortestPath<Vertex, Edge>(g);
            ShortestPathAlgorithm.SingleSourcePaths<Vertex, Edge> paths = dijkstra.getPaths(v);

            for (Vertex vv:
                 verticesInGraph) {
                if(!v.equals(vv)){
                    DefaultWeightedEdge e=new DefaultWeightedEdge();
                    edgeMap.put(e,paths.getPath(vv));
                    internal.addEdge(v,vv,e);
                    internal.setEdgeWeight(e,vv.getNumberOfPeople()/paths.getPath(vv).getWeight());
                }

            }
        }




    }
}
