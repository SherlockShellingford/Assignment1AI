package environment;

import environment.menu.MenuOptions;
import org.apache.commons.lang3.StringUtils;
import parser.WorldLoader;
import simulator.Simulator;

import java.util.Scanner;

public class Environment {

    private boolean shutdown = false;
    private Scanner scanner;

    private Simulator simulator;

    public Environment() {
        scanner = new Scanner(System.in);
    }


    public void start() {
        System.out.println("==========================");
        System.out.println("Welcome to the world of AI");
        System.out.println("==========================");
        WorldLoader loader = new WorldLoader("/Users/igorvinokur/Development/Dev/Study/Assignment1AI/src/main/resources/graph.json");
        EnvironmentState.getInstance().setGraph(loader.loadWorld());
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

}
