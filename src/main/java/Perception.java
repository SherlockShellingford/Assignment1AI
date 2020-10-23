import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

public class Perception {
    //Replace DummyVertex with vertex
    private DummyVertex vertex;
    private Graph<DummyVertex,> graph;

    public Perception(DummyVertex v, Graph g){
        this.graph=g;
        this.vertex=v;
    }

    public DummyVertex getVertex() {
        return vertex;
    }

    public void setVertex(DummyVertex vertex) {
        this.vertex = vertex;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

}
