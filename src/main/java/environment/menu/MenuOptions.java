package environment.menu;

import agents.AgentsFactory;
import environment.Environment;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuOptions {

    public interface MenuAction {
        void perform(Environment e);
    }

    public enum MainMenuOptions implements MenuAction {
        SetNumberOfAgents {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Set number of agents";
            }

            @Override
            public void perform(Environment e) {
                System.out.print("Please enter number of agents: ");
                Scanner s = new Scanner(System.in);
                int numberOfAgents = s.nextInt();
                e.setNumberOfAgents(numberOfAgents);
                e.setAgents(new ArrayList<>());
                e.startAgentsCreateMenu();
            }
        },
        StartSimulation {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Start simulation";
            }
            @Override
            public void perform(Environment e) {
                System.out.println("Starting simulation...");
                if (e.getNumberOfAgents() == 0 || e.getAgents().size() == 0) {
                    System.out.println("You don't have active agents");
                }
                else {
                    e.getSimulator().start();
                }
            }
        },
        Exit {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Exit";
            }
            @Override
            public void perform(Environment e) {
                e.setShutdown(true);
                System.out.println("Bye");
            }
        }
    }

    public enum AgentActionOptions implements MenuAction {
        CreateHumanAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create Human Agent";
            }
            @Override
            public void perform(Environment e) {
                System.out.println("Creating human agent");
                e.getAgents().add(AgentsFactory.createHumanAgent());
            }
        },
        CreateGreedyAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create Greedy Agent";
            }
            @Override
            public void perform(Environment e) {
                System.out.println("Creating greedy agent");
                e.getAgents().add(AgentsFactory.createGreedyAgent());
            }
        },
        CreateSaboteurAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create Saboteur Agent";
            }
            @Override
            public void perform(Environment e) {
                System.out.println("Creating Saboteur agent");
                e.getAgents().add(AgentsFactory.createSaboteurAgent());
            }
        },
        Exit {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Exit";
            }
            @Override
            public void perform(Environment e) {
                e.setShutdown(true);
                System.out.println("Bye");
            }
        }
    }

}