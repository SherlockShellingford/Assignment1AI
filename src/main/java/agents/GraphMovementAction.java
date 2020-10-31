package agents;

import datatypes.Vertex;

public class GraphMovementAction extends Action{
    private Vertex toVertex;



    private boolean terminate=false;
    public GraphMovementAction(Vertex toVertex) {
        this.toVertex = toVertex;
    }
    public GraphMovementAction(boolean terminate) {
        this.terminate = terminate;
    }

    public Vertex getToVertex() {
        return toVertex;
    }

    public void setToVertex(Vertex toVertex) {
        this.toVertex = toVertex;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    @Override
    public String toString() {
        return "GraphMovementAction {\n" +
                "\ttoVertex = " + toVertex +
                ", \n\tterminate = " + terminate +
                "\n}";
    }
}
