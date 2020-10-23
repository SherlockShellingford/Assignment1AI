package datatypes;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {

    private String comment;

    public Edge() {
        super();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Edge {\n" +
                "\tsource = " + getSource() + "," +
                "\ttaget = " + getTarget() + "," +
                "\tweight = " + getWeight() + "," +
                "\tcomment = '" + comment + '\'' +
                "\n}";
    }
}
