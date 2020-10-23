import datatypes.Edge;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HumanAgent extends Agent {

public GraphMovementAction processNextAction(Perception perception){
    Set<Edge> neighbors=perception.getGraph().edgesOf(perception.getVertex());
    System.out.println("The neighbors are:");

    Iterator<Edge> showuserIt=neighbors.iterator();
    int currIndex=0;
    while(showuserIt.hasNext()){
        Edge currEdge=showuserIt.next();
        if(!currEdge.isBlocked()) {
            if (currEdge.getSource().equals(perception.getVertex())) {
                System.out.println("" + currIndex + ") " + currEdge.getTarget().toString());
            } else {
                System.out.println("" + currIndex + ") " + currEdge.getSource().toString());
            }
        }
        currIndex++;
    }
    System.out.println("Enter index of next node.");
    Scanner sc=new Scanner(System.in);
    int index=sc.nextInt();
    while(index>=neighbors.size()){
        System.out.println("Out of bounds, select again:");
        index=sc.nextInt();
    }
    Iterator<Edge> it=neighbors.iterator();
    for(int i=0;i<index;++i){
        it.next();
    }
    Edge next=it.next();
    if(next.getSource().equals(perception.getVertex())){
        return new GraphMovementAction(next.getTarget());
    }
    else{
        return new GraphMovementAction(next.getSource());
    }







}
}
