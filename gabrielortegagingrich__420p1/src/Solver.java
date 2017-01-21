import java.util.Arrays;
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
      Node current = null;
      Node next;
      int index;

      // frontier and visited are already created

      // while frontier is not empty
      solve:
      while (!frontier.isEmpty()) {
         // make sure current starts null
         current = null;

         // find least
         for (Node n : frontier.values()) {
            if (current == null || n.h + n.g < current.h + current.g) {
               current = n;
            }

            if (current.h == 0) {
               break solve;
            }
         }

         frontier.remove(current.getState());
         visited.put(current.getState(), current);

         // if current node's h is 0, the puzzle is complete


         // find index of 0
         index = current.getState().indexOf("0");
         next = null;

         // calculate h(n`) for each possible action
         int[][] boardCurrent = null, boardNext;
         int temp;

         // check h for switching to right
         if (index / 3 < 2) { // if there is a node to the right of 0
            boardCurrent = Arrays.copyOf(current.getBoard(), 9);
            temp = boardCurrent[index / 3][index % 3];
            boardCurrent[index / 3][index % 3] = boardCurrent[index / 3 + 1][index % 3];
            boardCurrent[index / 3 + 1][index % 3] = temp;

            next = new Node(current, generateState(boardCurrent), h(boardCurrent), current.g + 1);

            if (!visited.containsKey(next.getState()) && !frontier.containsKey(next.getState())) {
               frontier.put(next.getState(), next);
            }
         }

         // check for switching to left
         if (index / 3 > 0) {
            boardCurrent = Arrays.copyOf(current.getBoard(), 9);
            temp = boardCurrent[index / 3][index % 3];
            boardCurrent[index / 3][index % 3] = boardCurrent[index / 3 - 1][index % 3];
            boardCurrent[index / 3 - 1][index % 3] = temp;

            next = new Node(current, generateState(boardCurrent), h(boardCurrent), current.g + 1);

            if (!visited.containsKey(next.getState()) && !frontier.containsKey(next.getState())) {
               frontier.put(next.getState(), next);
            }
         }

         // check for switching up
         if (index % 3 > 0) {
            boardCurrent = Arrays.copyOf(current.getBoard(), 9);
            temp = boardCurrent[index / 3][index % 3];
            boardCurrent[index / 3][index % 3] = boardCurrent[index / 3][index % 3 - 1];
            boardCurrent[index / 3][index % 3 - 1] = temp;

            next = new Node(current, generateState(boardCurrent), h(boardCurrent), current.g + 1);

            if (!visited.containsKey(next.getState()) && !frontier.containsKey(next.getState())) {
               frontier.put(next.getState(), next);
            }
         }

         // check for switching down
         if (index % 3 < 2) {
            boardCurrent = Arrays.copyOf(current.getBoard(), 9);
            temp = boardCurrent[index / 3][index % 3];
            boardCurrent[index / 3][index % 3] = boardCurrent[index / 3][index % 3 + 1];
            boardCurrent[index / 3][index % 3 + 1] = temp;

            next = new Node(current, generateState(boardCurrent), h(boardCurrent), current.g + 1);

            if (!visited.containsKey(next.getState()) && !frontier.containsKey(next.getState())) {
               frontier.put(next.getState(), next);
            }
         }
      }

      return current;
   }

   private String generateState(int[][] board) {
      String out = "";

      for (int i = 0; i < 9; i++) {
         out = String.format("%s%d", out, board[i / 3][i % 3]);
      }

      return out;
   }

   abstract public int h(int[][] board);
}
