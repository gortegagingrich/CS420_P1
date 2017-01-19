import java.util.Arrays;

/**
 * Created by gabriel on 1/19/17.
 */
public class NodeSet {
   private Node min;
   private Node[] frontier;

   public NodeSet() {
      frontier = new Node[0];
   }

   public NodeSet(Node n) {
      min = n;
      frontier = new Node[] {n};
   }

   public void addNode(Node node) {
      frontier = Arrays.copyOf(frontier,frontier.length+1);
      frontier[frontier.length-1] = node;

      if (min == null || node.g < min.g) {
         min = node;
      }
   }
   public void removeNode(Node n) {
      for (int i = 0; i < frontier.length; i++) {

      }
   }
}
