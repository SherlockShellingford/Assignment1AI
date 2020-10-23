import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.Graph;
import parser.WorldLoader;

public class Main {

    public static void main(String[] args) {

        System.out.println("Hello AI");
        WorldLoader loader = new WorldLoader("/Users/igorvinokur/Development/Dev/Study/Assignment1AI/src/main/resources/graph.json");
        Graph<Vertex, Edge> g = loader.loadWorld();
        System.out.println(g);

    }
}
