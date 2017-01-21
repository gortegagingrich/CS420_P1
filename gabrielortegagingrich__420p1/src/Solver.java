import java.util.HashMap;

/**
 * Created by Gabriel on 2017/01/18.
 */
public abstract class Solver {
   private HashMap<String, Node> frontier, visited;

   public Solver(int[][] board) throws NoEmptyTileException {
      int[] empty = null;

      for (int i = 0; i < board.length; i++) {
         empty = new int[]{i / 3, i % 3};
      }

      if (empty == null) {
         throw (new NoEmptyTileException());
      }

      String tmp = "";
      for (int[] i : board) {
         for (int j : i) {
            tmp = String.format("%s%d", tmp, j);
         }
      }

      frontier = new HashMap<>();
      Node n = new Node(null, tmp, h(board), 0);
      frontier.put(n.getState(), n);

      visited = new HashMap<>();
   }

   public static boolean isSolvable(int[][] board) {
      int inversionCount = 0;
      int left, right;

      // for each tile as left index
      for (left = 0; left < 8; left++) {
         // if the left index is not 0
         if (board[left / 3][left % 3] != 0) {
            // for each following tile as right index
            for (right = left + 1; right < 8; right++) {
               // if the right index is not 0 and left index > right index
               if (board[right / 3][right % 3] != 0 && board[left / 3][left % 3] > board[right / 3][right % 3]) {
                  // increment inversion count
                  inversionCount++;
               }
            }
         }
      }

      // if inversion count is even, return true
      // else return false
      return (inversionCount % 2 == 0);
   }

   /**
    * @return
    */
   public Node solve() {
      Node end = null;

      System.out.println("Solve has not been implemented yet");
      for (Node n : frontier.values()) {
         System.out.printf("H(initial): %s\n", n.h);
      }

      return end;
   }

   abstract public int h(int[][] board);
}
