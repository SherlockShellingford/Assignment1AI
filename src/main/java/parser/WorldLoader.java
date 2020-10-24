package parser;

import datatypes.Edge;
import datatypes.Vertex;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.nio.json.JSONImporter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WorldLoader {

    private String jsonFile;
    private Integer worldSize;
    private Double worldTimout;

    public WorldLoader(String file) {
        this.jsonFile = file;
    }

    public WeightedMultigraph<Vertex, Edge> loadWorld() {
        try {
            JSONObject o = (JSONObject) new JSONParser().parse(new FileReader(new File(this.jsonFile)));
            worldTimout = (Double) o.get("timeout");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return loadGraph();
    }

    private WeightedMultigraph<Vertex, Edge> loadGraph() {
        System.out.println("Loading simulation world....");
        WeightedMultigraph<Vertex, Edge> graph = new WeightedMultigraph<>(Edge.class);
        JSONImporter<Vertex, Edge> importer = new JSONImporter<>();
        importer.addVertexAttributeConsumer((vertexStringPair, attribute) -> {
            if (vertexStringPair.getSecond().equals("ID")) {
                vertexStringPair.getFirst().setId(Integer.parseInt(attribute.getValue()));
            }
            else if (vertexStringPair.getSecond().equals("numberOfPeople")) {
                vertexStringPair.getFirst().setNumberOfPeople(Integer.parseInt(attribute.getValue()));
            }
            else if (vertexStringPair.getSecond().equals("comment")) {
                vertexStringPair.getFirst().setComment(attribute.getValue());
            }
        });
        importer.addEdgeAttributeConsumer((edgeStringPair, attribute) -> {
            if (edgeStringPair.getSecond().equals("comment")) {
                edgeStringPair.getFirst().setComment(attribute.getValue());
            }
        });
        importer.setVertexFactory(s -> new Vertex());
        importer.importGraph(graph, new File(jsonFile));
        this.worldSize = graph.vertexSet().size();
        return graph;
    }

    public Integer getWorldSize() {
        return worldSize;
    }

    public void setWorldSize(Integer worldSize) {
        this.worldSize = worldSize;
    }

    public Double getWorldTimout() {
        return worldTimout;
    }

    public void setWorldTimout(Double worldTimout) {
        this.worldTimout = worldTimout;
    }
}
