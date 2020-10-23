import java.util.LinkedList;
import java.util.List;

public abstract class Agent {
    //TODO Add State, Goal and Problem classes

    List<Action> seq=new LinkedList<Action>();
    Goal goal=null;
    Problem problemFormulation;
    public abstract Action processNextAction(Perception perception);


    public List<Action> getSeq() {
        return seq;
    }

    public void setSeq(List<Action> seq) {
        this.seq = seq;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Problem getProblemFormulation() {
        return problemFormulation;
    }

    public void setProblemFormulation(Problem problemFormulation) {
        this.problemFormulation = problemFormulation;
    }
}
