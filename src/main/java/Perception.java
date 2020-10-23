import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Perception {
    //Replace DummyVertex with vertex
    private Vertex vertex;
    private Graph<Vertex, Edge> graph;

    public Perception(Vertex v, Graph g){
        this.graph=g;
        this.vertex=v;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

}
