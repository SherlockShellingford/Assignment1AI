package environment;

import agents.Agent;
import datatypes.Edge;
import datatypes.Vertex;
import environment.menu.MenuOptions;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import parser.WorldLoader;
import simulator.Simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Environment {

    private boolean shutdown = false;
    private Scanner scanner;

    private int numberOfAgents = 0;
    private List<Agent> agents;

    private Simulator simulator;
    private Graph<Vertex, Edge> graph;

    public Environment() {
        scanner = new Scanner(System.in);
        agents = new ArrayList<>();
    }

    // Main menu for the program
    // Agents factory
    // Implement main simulation loop
    // Score calculating
    //
    public void start() {
        System.out.println("==========================");
        System.out.println("Welcome to the world of AI");
        System.out.println("==========================");
        WorldLoader loader = new WorldLoader("/Users/igorvinokur/Development/Dev/Study/Assignment1AI/src/main/resources/graph.json");
        graph = loader.loadWorld();
        while (!shutdown) {
            printMenu("Main", MenuOptions.MainMenuOptions.values());
            MenuOptions.MenuAction choice = getUserInput(MenuOptions.MainMenuOptions.values());
            if (choice != null) {
                choice.perform(this);
            }
        }
    }

    public void startAgentsCreateMenu() {
        while (!shutdown && numberOfAgents > agents.size()) {
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

    public int getNumberOfAgents() {
        return numberOfAgents;
    }

    public void setNumberOfAgents(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }
}
