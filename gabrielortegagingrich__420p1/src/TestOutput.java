import java.util.HashMap;

/**
 * Created by gabriel on 1/24/17.
 */
public class TestOutput {
   // depth, num expanded, frontier size, path, final state
   private static final boolean[] options = {true, false, false, true, true, false};

   private static final String[] lines = {"Depth: %d\n", "Nodes Expanded: %d\n", "Frontier Size: %d\n",
                                          "Total Nodes: %d\n", "Path:\n%s\n", "Final State: %s\n"};

   private int[]    ints;
   private String[] strings;


   public TestOutput(HashMap<String, Node> frontier, HashMap<String, Node> expanded, Node result) {
      ints = new int[]{result.getDepth(), expanded.size(), frontier.size(), expanded.size() + frontier.size()};
      strings = new String[]{result.getPath(), result.getState()};
   }

   public String toString() {
      String out = "";

      for (int i = 0; i < 6; i++) {
         if (options[i]) {
            if (i >= 4) {
               out += String.format(lines[i], strings[(i) % 2]);
            } else {
               out += String.format(lines[i], ints[i % 4]);
            }
         }
      }

      return out;
   }

   public String toCSV() {
      // depth,cost
      return String.format("%d,%d\n", ints[0], ints[3]);
   }
}
