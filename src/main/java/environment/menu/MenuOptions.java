package environment.menu;

import agents.Agent;
import agents.AgentsFactory;
import agents.goals.RescuePeopleGoal;
import environment.Environment;
import environment.EnvironmentState;

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
                EnvironmentState.getInstance().setNumberOfAgents(numberOfAgents);
                EnvironmentState.getInstance().setAgents(new ArrayList<>());
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
                EnvironmentState state = EnvironmentState.getInstance();
                if (state.getNumberOfAgents() == 0 || state.getAgents().size() == 0) {
                    System.out.println("You don't have active agents");
                } else {
                    e.getSimulator().start();
                    state.setGraph(Environment.getGraph());
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
        //        CreateHumanAgent {
//            @Override
//            public String toString() {
//                return this.ordinal() + 1 + ". Create Human Agent";
//            }
//            @Override
//            public void perform(Environment e) {
//                System.out.println("Creating human agent");
//                EnvironmentState.getInstance().getAgents().add(AgentsFactory.createHumanAgent());
//            }
//        },
//        CreateGreedyAgent {
//            @Override
//            public String toString() {
//                return this.ordinal() + 1 + ". Create Greedy Agent";
//            }
//            @Override
//            public void perform(Environment e) {
//                System.out.println("Creating greedy agent");
//                EnvironmentState.getInstance().getAgents().add(AgentsFactory.createGreedyAgent());
//            }
//        },
//        CreateSaboteurAgent {
//            @Override
//            public String toString() {
//                return this.ordinal() + 1 + ". Create Saboteur Agent";
//            }
//            @Override
//            public void perform(Environment e) {
//                System.out.println("Creating Saboteur agent");
//                EnvironmentState.getInstance().getAgents().add(AgentsFactory.createSaboteurAgent());
//            }
//        },
        CreateGreedySearchAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create Greedy Search Agent";
            }

            @Override
            public void perform(Environment e) {
                System.out.println("Creating Greedy Search Agent");
                Agent a = AgentsFactory.createGreedySearchAgent();
                a.setGoal(new RescuePeopleGoal());
                EnvironmentState.getInstance().getAgents().add(a);
            }
        },
        CreateAStarSearchAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create A* Search Agent";
            }

            @Override
            public void perform(Environment e) {
                System.out.println("Creating A* Search Agent");
                Agent a = AgentsFactory.createAStarSearchAgent();
                a.setGoal(new RescuePeopleGoal());
                EnvironmentState.getInstance().getAgents().add(a);
            }
        },
        CreateRealTimeAStarSearchAgent {
            @Override
            public String toString() {
                return this.ordinal() + 1 + ". Create Real Time A* Search Agent";
            }

            @Override
            public void perform(Environment e) {
                System.out.println("Creating Real Time A* Search Agent");
                Agent a = AgentsFactory.createRealTimeAStarSearchAgent();
                a.setGoal(new RescuePeopleGoal());
                EnvironmentState.getInstance().getAgents().add(a);
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