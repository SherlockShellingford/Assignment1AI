package agents;

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
