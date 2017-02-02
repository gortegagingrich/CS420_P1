/**
 * Created by gabriel on 2/2/17.
 */
public class VisitedSet {
   private SimpleNode root;
   private int visitedCount;

   public VisitedSet() {
      root = new SimpleNode();
      visitedCount = 0;
   }

   public boolean contains(String str) {
      return root.contains(str);
   }

   public void addState(String state) {
      if (!root.contains(state)) {
         root.addChild(state);
         visitedCount++;
      }
   }

   public int size() {
      return visitedCount;
   }

   private class SimpleNode {
      SimpleNode[] children;

      private SimpleNode() {
         children = new SimpleNode[9];
      }

      void addChild(String str) {
         int len = str.length() - 1;
         int index = str.charAt(0) - '0';

         if (str.length() == 1) {
            children[index] = new SimpleNode();
         } else {
            if (children[index] == null) {
               children[index] = new SimpleNode();
            }

            children[index].addChild(str.substring(1,str.length()));
         }
      }

      boolean contains(String str) {
         if (str.length() == 0) {
            return true;
         } else {
            if (children[str.charAt(0) - '0'] != null) {
               return children[str.charAt(0) - '0'].contains(str.substring(str.length() - 1));
            } else {
               return false;
            }
         }
      }
   }
}
