package datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {

    private String comment;

    private boolean blocked;

    public Edge() {
        super();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    @Override
    public double getWeight() {
        return super.getWeight();
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public Object getTarget() {
        return super.getTarget();
    }

    @Override
    public String toString() {
        return "Edge {\n" +
                "\tsource = " + getSource() + "," +
                "\ttaget = " + getTarget() + "," +
                "\tweight = " + getWeight() + "," +
                "\tblocked = " + blocked + "," +
                "\tcomment = '" + comment + '\'' +
                "\n}";
    }
}
