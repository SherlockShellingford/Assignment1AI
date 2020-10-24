package agents;

import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GreedyAgent extends Agent {
    private Queue<Vertex> currPath=new LinkedBlockingQueue<>();
    public GreedyAgent(){

    }

    public GraphMovementAction processNextAction(Perception perception){
        if(currPath.isEmpty()) {
            DijkstraShortestPath<Vertex, Edge> dijkstra = new DijkstraShortestPath<Vertex, Edge>(perception.getGraph());
            ShortestPathAlgorithm.SingleSourcePaths<Vertex, Edge> paths = dijkstra.getPaths(perception.getVertex());
            Iterator<Vertex> vertexIterator = perception.getGraph().vertexSet().iterator();
            double minimal = Double.POSITIVE_INFINITY;
            Vertex minVertex = new Vertex(Integer.MAX_VALUE,Integer.MIN_VALUE);
            while (vertexIterator.hasNext()) {
                Vertex v = vertexIterator.next();
                if (v.getNumberOfPeople() != 0) {
                    double pathWeight = paths.getWeight(v);
                    if (minimal > pathWeight||(minimal==pathWeight&&v.getId()<minVertex.getId())) {
                        minimal = pathWeight;
                        minVertex = v;
                    }
                }

            }
            if(minimal==Double.POSITIVE_INFINITY){
                return new GraphMovementAction(true);
            }
            GraphPath<Vertex, Edge> goalPath = paths.getPath(minVertex);
            List<Vertex> pathList = goalPath.getVertexList();
            for (Vertex v : pathList
            ) {
                currPath.add(v);
            }

        }

        return new GraphMovementAction(currPath.poll());

    }
}
