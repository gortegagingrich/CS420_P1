/**
 * Created by gabriel on 1/19/17.
 */
public class Node {
   private String[] prevStates;
   private String state;
   int h, g;

   public Node(Node prev, String state, int h, int g) {
      if (prev == null) {
         prevStates = new String[] {};
      } else {
         prevStates = new String[prev.prevStates.length + 1];

         int i = 0;

         while (i < prevStates.length-1) {
            prevStates[i] = prev.prevStates[i++];
         }

         prevStates[i] = prev.state;
      }
   }
}
