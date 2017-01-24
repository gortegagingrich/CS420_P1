import java.util.HashMap;

/**
 * Created by gabriel on 1/24/17.
 */
public class TestOutput {
   // depth, num expanded, frontier size, path, final state
   private static boolean[] options = {true, true, true, false, true};

   private static final String[] lines = {"Depth: %d\n", "Nodes Expanded: %d\n", "Frontier Size: %d\n", "Path:\n%s\n", "Final State: %s\n"};

   private int[] ints;
   private String[] strs;


   public TestOutput(HashMap<String, Node> frontier, HashMap<String, Node> expanded, Node result) {
      ints = new int[] {result.getDepth(), expanded.size(), frontier.size()};
      strs = new String[] {result.getPath(), result.getState()};
   }

   public String toString() {
      String out = "";

      for (int i = 0; i < 5; i++) {
         if (options[i]) {
            if (i >= 3) {
               out += String.format(lines[i], strs[(i+1) % 2]);
            } else {
               out += String.format(lines[i], ints[i % 3]);
            }
         }
      }

      return out;
   }
}
