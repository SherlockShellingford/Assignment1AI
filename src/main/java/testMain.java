import agents.GreedyHeuristicAgent;
import agents.goals.RescuePeopleGoal;
import datatypes.Edge;
import datatypes.Vertex;
import environment.EnvironmentState;
import org.jgrapht.Graph;
import org.jgrapht.graph.WeightedMultigraph;
import simulator.Simulator;

public class testMain {
    public static void main(String[] args){
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

//        GreedyAgent agent=new GreedyAgent();
//        GraphMovementAction action=agent.processNextAction(new Perception(v1,g));
        EnvironmentState.getInstance().setGraph(g);
        RescuePeopleGoal rpg = new RescuePeopleGoal();
        GreedyHeuristicAgent a = new GreedyHeuristicAgent(g, v1);
//        AStarAgent a = new AStarAgent(g, v1);
//        RealTimeAStarAgent a = new RealTimeAStarAgent(g, v1);
        a.setGoal(rpg);
//        a.setGoal(rpg);
//        a.processNextAction(null);
        EnvironmentState.getInstance().getAgents().add(a);
        Simulator s = new Simulator();
        s.start();
//        System.out.println(a.getInternal().edgeSet());
    }
}
