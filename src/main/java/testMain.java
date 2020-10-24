import agents.GraphMovementAction;
import agents.GreedyAgent;
import agents.Perception;
import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class testMain {
    public static void main(String[] args){
        Graph<Vertex, Edge> g=new DefaultUndirectedGraph<Vertex,Edge>(Edge.class);
        Vertex v1=new Vertex(1,0);
        Vertex v2=new Vertex(2,0);
        Vertex v3=new Vertex(3,0);
        Vertex v4=new Vertex(4,49);
        Vertex v5=new Vertex(5,44);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        Edge e1=new Edge(1);
        Edge e2=new Edge(2);
        Edge e3=new Edge(3);
        Edge e4=new Edge(4);
        g.addEdge(v1,v2,e1);
        g.addEdge(v2,v4,e2);
        g.addEdge(v1,v3,e3);
        g.addEdge(v3,v5,e4);
        g.setEdgeWeight(e1,5);
        g.setEdgeWeight(e2,3);
        g.setEdgeWeight(e3,2);
        g.setEdgeWeight(e4,9);
        GreedyAgent agent=new GreedyAgent();
        GraphMovementAction action=agent.processNextAction(new Perception(v1,g));
        System.out.println(action.getToVertex());
    }
}
