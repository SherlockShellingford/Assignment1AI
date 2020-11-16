package environment;

import datatypes.Edge;
import datatypes.Vertex;
import environment.menu.MenuOptions;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedMultigraph;
import parser.WorldLoader;
import simulator.Simulator;

import java.util.Scanner;

public class Environment {

    private boolean shutdown = false;
    private Scanner scanner;

    private Simulator simulator;

    public Environment() {
        scanner = new Scanner(System.in);
        simulator = new Simulator();
    }


    public void start() {
        System.out.println("==========================");
        System.out.println("Welcome to the world of AI");
        System.out.println("==========================");
        WorldLoader loader = new WorldLoader("/Users/igorvinokur/Development/Dev/Study/Assignment1AI/src/main/resources/graph-test.json");
        EnvironmentState.getInstance().setGraph(loader.loadWorld());
//        EnvironmentState.getInstance().setGraph(getGraph());
//        EnvironmentState.getInstance().setGraph(getGraph1());
        while (!shutdown) {
            printMenu("Main", MenuOptions.MainMenuOptions.values());
            MenuOptions.MenuAction choice = getUserInput(MenuOptions.MainMenuOptions.values());
            if (choice != null) {
                choice.perform(this);
            }
        }
    }

    public void startAgentsCreateMenu() {
        EnvironmentState state = EnvironmentState.getInstance();
        while (!shutdown && state.getNumberOfAgents() > state.getAgents().size()) {
            printMenu("Agents", MenuOptions.AgentActionOptions.values());
            MenuOptions.MenuAction choice = getUserInput(MenuOptions.AgentActionOptions.values());
            if (choice != null) {
                choice.perform(this);
            }
        }
    }

    private void printMenu(String title, MenuOptions.MenuAction[] values) {
        System.out.println("------------ " + title + " ------------");
        for (MenuOptions.MenuAction o : values) {
            System.out.println(o);
        }
        System.out.println(StringUtils.repeat('-', 26 + title.length()));
    }

    private MenuOptions.MenuAction getUserInput(MenuOptions.MenuAction[] values) {
        int option = scanner.nextInt();
        return values[option - 1];
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public static Graph<Vertex, Edge> getGraphFromFile() {
        WorldLoader loader = new WorldLoader("/Users/igorvinokur/Development/Dev/Study/Assignment1AI/src/main/resources/graph1.json");
        return loader.loadWorld();
    }

    public static Graph<Vertex, Edge> getGraph() {
        Graph<Vertex, Edge> g=new WeightedMultigraph<>(Edge.class);
        Vertex v1=new Vertex(1,0);
        Vertex v2=new Vertex(2,0);
        Vertex v3=new Vertex(3,1);
        Vertex v4=new Vertex(4,4);
        Vertex v5=new Vertex(5,0);
        Vertex v6=new Vertex(6,0);
        Vertex v7=new Vertex(7,3);
        Vertex v8=new Vertex(8,2);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        Edge e1=new Edge(1);
        Edge e2=new Edge(2);
        Edge e3=new Edge(3);
        Edge e4=new Edge(4);
        Edge e5=new Edge(5);
        Edge e6=new Edge(6);
        Edge e7=new Edge(7);
        Edge e8=new Edge(8);
        Edge e9=new Edge(9);
        Edge e10=new Edge(10);
        g.addEdge(v1,v2,e1);
        g.setEdgeWeight(e1,2);

        g.addEdge(v2,v4,e2);
        g.setEdgeWeight(e2,8);

        g.addEdge(v2,v3,e3);
        g.setEdgeWeight(e3,5);

        g.addEdge(v1,v5,e4);
        g.setEdgeWeight(e4,3);

        g.addEdge(v4,v5,e5);
        g.setEdgeWeight(e5,6);

        g.addEdge(v3,v6,e6);
        g.setEdgeWeight(e6,7);

        g.addEdge(v4,v6,e7);
        g.setEdgeWeight(e7,4);

        g.addEdge(v4,v8,e8);
        g.setEdgeWeight(e8,9);

        g.addEdge(v4,v7,e9);
        g.setEdgeWeight(e9,1);

        g.addEdge(v7,v8,e10);
        g.setEdgeWeight(e10,1);

        return g;
    }

    public static Graph<Vertex, Edge> getGraph1() {
        Graph<Vertex, Edge> g=new WeightedMultigraph<>(Edge.class);
        Vertex v1=new Vertex(1,0);
        Vertex v2=new Vertex(2,6);
        Vertex v3=new Vertex(3,0);
        Vertex v4=new Vertex(4,0);
        Vertex v5=new Vertex(5,3);
        Vertex v6=new Vertex(6,10);
        Vertex v7=new Vertex(7,0);
        Vertex v8=new Vertex(8,4);
        Vertex v9=new Vertex(9,0);
        Vertex v10=new Vertex(10,0);
        Vertex v11=new Vertex(11,2);
        Vertex v12=new Vertex(12,0);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);
        g.addVertex(v10);
        g.addVertex(v11);
        g.addVertex(v12);
        Edge e1=new Edge(1);
        Edge e2=new Edge(2);
        Edge e3=new Edge(3);
        Edge e4=new Edge(4);
        Edge e5=new Edge(5);
        Edge e6=new Edge(6);
        Edge e7=new Edge(7);
        Edge e8=new Edge(8);
        Edge e9=new Edge(9);
        Edge e10=new Edge(10);
        Edge e11=new Edge(11);
        Edge e12=new Edge(12);
        Edge e13=new Edge(13);
        Edge e14=new Edge(14);
        g.addEdge(v1,v2,e1);
        g.setEdgeWeight(e1,12);

        g.addEdge(v1,v6,e2);
        g.setEdgeWeight(e2,20);

        g.addEdge(v2,v3,e3);
        g.setEdgeWeight(e3,5);

        g.addEdge(v2,v7,e4);
        g.setEdgeWeight(e4,1);

        g.addEdge(v3,v4,e5);
        g.setEdgeWeight(e5,6);

        g.addEdge(v4,v5,e6);
        g.setEdgeWeight(e6,4);

        g.addEdge(v4,v11,e7);
        g.setEdgeWeight(e7,3);

        g.addEdge(v5,v12,e8);
        g.setEdgeWeight(e8,5);

        g.addEdge(v6,v7,e9);
        g.setEdgeWeight(e9,8);

        g.addEdge(v7,v8,e10);
        g.setEdgeWeight(e10,3);

        g.addEdge(v7,v10,e11);
        g.setEdgeWeight(e11,11);

        g.addEdge(v9,v10,e12);
        g.setEdgeWeight(e12,9);

        g.addEdge(v9,v11,e13);
        g.setEdgeWeight(e13,1);

        g.addEdge(v9,v12,e14);
        g.setEdgeWeight(e14,7);

        return g;
    }

}
