package agents;

import agents.goals.Goal;
import agents.state.State;

import java.util.LinkedList;
import java.util.List;

public abstract class Agent {

    protected List<Action> seq = new LinkedList<>();
    protected Goal goal;
    protected Problem problemFormulation;
    protected boolean failed;
    protected State state;

    public abstract Action processNextAction(Perception perception);
    public abstract void updateState(Action action);

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

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
