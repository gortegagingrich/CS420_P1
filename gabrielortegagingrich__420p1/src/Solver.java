import java.util.HashMap;

/**
 * Created by Gabriel on 2017/01/18.
 */
public abstract class Solver {
   protected int[] board[], empty;
   private HashMap<String, Node> frontier, visited;

   public Solver(int[][] board) throws NoEmptyTileException {
      this.board = board;

      for (int i = 0; i < board.length; i++) {
         empty = new int[]{i/3, i%3};
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

      for (int i = 0; i < 8; i++) {
         if (board[i / 3][i % 3] == 0) {
            i++;
         }

         left = board[i / 3][i % 3];

         if (board[(i + 1) / 3][(i + 1) % 3] == 0) {
            i++;
         }

         if (i > 7) {
            break;
         }

         right = board[(i + 1) / 3][(i + 1) % 3];

         if (left > right) {
            if (inversionCount == 0) {
               // inversionCount++;
            }
            inversionCount++;
         }
      }

      return (inversionCount%2 == 0);
   }

   /**
    * @return
    */
   public Node solve() {
      Node min = null, nextNode = null;
      int out = 0;
      int x, y;

      while (!frontier.isEmpty()) {
         // find node with minimum h in frontier
         min = null;
         nextNode = null;

         for (Node node : frontier.values()) {
            if (min == null || node.h < min.h) {
               min = node;
            }
         }

         // remove node with minimum h from frontier and add to visited
         frontier.remove(min.getState(), min);
         visited.put(min.getState(), min);

         // h == 0 -> the puzzle is solved
         if (min.h == 0) {
            break;
         }

         // find H for each of minimum node's possible successors
         // find position of 0
         int i = min.getState().indexOf("0");
         int[][] board;
         int tmp;
         String state;

         x = i / 3;
         y = i % 3;

         // check move left
         if (x > 0) {
            board = min.getBoard();
            tmp = board[x - 1][y];
            board[x - 1][y] = board[x][y];
            board[x][y] = tmp;

            state = "";

            for (int[] row : board) {
               for (int j : row) {
                  state = String.format("%s%d", state, j);
               }
            }

            if (!visited.containsKey(state)) {
               nextNode = new Node(min, state, h(board), min.g + 1);
            }
         }

         // check move down
         if (y < 2) {
            board = min.getBoard();
            tmp = board[x][y + 1];
            board[x][y + 1] = board[x][y];
            board[x][y] = tmp;

            state = "";

            for (int[] row : board) {
               for (int j : row) {
                  state = String.format("%s%d", state, j);
               }
            }

            if (!visited.containsKey(state)) {
               if ((nextNode != null && h(board) < nextNode.h) || nextNode == null) {
                  nextNode = new Node(min, state, h(board), min.g + 1);
               }
            }
         }

         // check move right
         if (x < 2) {
            board = min.getBoard();
            tmp = board[x + 1][y];
            board[x + 1][y] = board[x][y];
            board[x][y] = tmp;

            state = "";

            for (int[] row : board) {
               for (int j : row) {
                  state = String.format("%s%d", state, j);
               }
            }

            if (!visited.containsKey(state)) {
               if ((nextNode != null && h(board) < nextNode.h) || nextNode == null) {
                  nextNode = new Node(min, state, h(board), min.g + 1);
               }
            }
         }

         // check move up
         if (y > 0) {
            board = min.getBoard();
            tmp = board[x][y - 1];
            board[x][y - 1] = board[x][y];
            board[x][y] = tmp;

            state = "";

            for (int[] row : board) {
               for (int j : row) {
                  state = String.format("%s%d", state, j);
               }
            }

            if (!visited.containsKey(state)) {
               if ((nextNode != null && h(board) < nextNode.h) || nextNode == null) {
                  nextNode = new Node(min, state, h(board), 0);
               }
            }
         }

         // add minimum to frontier with incremented g
         if (nextNode != null) {
            frontier.put(nextNode.getState(), nextNode);
         }
      }

      return min;
   }

   abstract public int h();

   abstract public int h(int[][] board);
}
