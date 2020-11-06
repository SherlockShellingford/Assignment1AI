package agents;

import datatypes.Vertex;
import environment.EnvironmentState;

import java.util.Scanner;

public class AgentsFactory {

    public static Agent createHumanAgent() {
        getName();
        getInitPosition();
        return new HumanAgent();
    }

    public static Agent createGreedyAgent() {
        getName();
        getInitPosition();
        return new GreedyAgent();
    }

    public static Agent createSaboteurAgent() {
        getName();
        getInitPosition();
        return new SaboteurAgent();
    }

    public static Agent createGreedySearchAgent() {
        int id = getInitPosition();
        for (Vertex v : EnvironmentState.getInstance().getGraph().vertexSet()) {
            if (v.getId() == id) {
                EnvironmentState.getInstance().setInit(v);
                break;
            }
        }
        return new GreedyHeuristicAgent(EnvironmentState.getInstance().getGraph(), EnvironmentState.getInstance().getInit());
    }

    public static Agent createAStarSearchAgent() {
        int id = getInitPosition();
        for (Vertex v : EnvironmentState.getInstance().getGraph().vertexSet()) {
            if (v.getId() == id) {
                EnvironmentState.getInstance().setInit(v);
                break;
            }
        }
        return new AStarAgent(EnvironmentState.getInstance().getGraph(), EnvironmentState.getInstance().getInit());
    }

    public static Agent createRealTimeAStarSearchAgent() {
        int id = getInitPosition();
        for (Vertex v : EnvironmentState.getInstance().getGraph().vertexSet()) {
            if (v.getId() == id) {
                EnvironmentState.getInstance().setInit(v);
                break;
            }
        }
        return new RealTimeAStarAgent(EnvironmentState.getInstance().getGraph(), EnvironmentState.getInstance().getInit());
    }



    private static int getInitPosition() {
        System.out.print("Please set agent initial position (vertex id):");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String getName() {
        System.out.print("Please set agent name:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
