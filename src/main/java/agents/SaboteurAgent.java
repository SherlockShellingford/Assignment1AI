package agents;

public class SaboteurAgent extends Agent {
    private boolean sabotageTurn=true;
    @Override
    public Action processNextAction(Perception perception) {
//        if(sabotageTurn) {
//            Set<Edge> neighbors = perception.getGraph().edgesOf(perception.getVertex());
//            System.out.println("The neighbors are:");
//
//            Iterator<Edge> it = neighbors.iterator();
//            int currIndex = 0;
//            double minimal = Double.POSITIVE_INFINITY;
//            Edge toBlock = null;
//            while (it.hasNext()) {
//                Edge e = it.next();
//                double weight = e.getWeight();
//                if (minimal > weight || (minimal == weight && (e.getId() < toBlock.getId() || toBlock == null))) {
//                    minimal = weight;
//                    toBlock = e;
//                }
//
//            }
//            if (minimal == Double.POSITIVE_INFINITY) {
//                return new agents.GraphMovementAction(true);
//            }
//            sabotageTurn = false;
//            toBlock.setBlocked(true);
//            return new agents.GraphMovementAction(perception.getVertex());
//        }
//        else{
//            Set<Edge> neighbors = perception.getGraph().edgesOf(perception.getVertex());
//            System.out.println("The neighbors are:");
//
//            Iterator<Edge> it = neighbors.iterator();
//            int currIndex = 0;
//            double minimal = Double.POSITIVE_INFINITY;
//            Edge next = null;
//            while (it.hasNext()) {
//                Edge e = it.next();
//                double weight = e.getWeight();
//                if (minimal > weight || (minimal == weight && (e.getId() < next.getId() || next == null))) {
//                    minimal = weight;
//                    next = e;
//                }
//
//            }
//            if (minimal == Double.POSITIVE_INFINITY) {
//                return new agents.GraphMovementAction(true);
//            }
//            sabotageTurn = true;
//            if(next.getSource().equals(perception.getVertex())){
//                return new agents.GraphMovementAction(next.getTarget());
//            }
//            else{
//                return new agents.GraphMovementAction(next.getSource());
//            }
//            return new agents.GraphMovementAction(perception.getVertex());
//        }
        return null;
    }
}