import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class HumanAgent {
public Action processNextAction(Perception perception){
    Set<Object> neighbors=perception.getGraph().edgesOf(perception.getVertex());
    System.out.println("The neighbors are:");
    Iterator it=neighbors.iterator();
    Object[] neighborsarr=neighbors.toArray();

    for(int i=0;i<neighbors.size();++i){
        System.out.println(""+i+") "+neighborsarr[i].toString());
    }
    System.out.println("Enter index of next node.");
    Scanner sc=new Scanner(System.in);
    int index=sc.nextInt();
    while(index>=neighbors.size()){
        System.out.println("Out of bounds, select again:");
        index=sc.nextInt();
    }



}
}
