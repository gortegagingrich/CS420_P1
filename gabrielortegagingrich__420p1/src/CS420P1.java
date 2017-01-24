import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Gabriel on 2017/01/18.
 */
public class CS420P1 {
   public static void main(String[] args) throws NoEmptyTileException {
      int[][] board;

      try {
         File file = new File("SamplePuzzles.txt");
         Scanner scan = new Scanner(file);
         String str;
         Solver solve;
         Node n;

         int i;
         int count = 0;

         while (scan.hasNext()) {
            str = scan.nextLine();

            if (str.length() != 9) {
               continue;
            }

            System.out.printf("Puzzle: %s\n", str);

            i = 0;
            board = new int[3][3];

            for (char c : str.toCharArray()) {
               board[i / 3][i++ % 3] = c - '0';
            }

            // need to rewrite isSolvable() because it currently does not work
            if (Solver.isSolvable(board)) {
               solve = new H1Solver(board);
               System.out.printf("H1 expanded nodes: ");
               n = solve.solve();

               solve = new H2Solver(board);
               System.out.printf("H2 expanded nodes: ");
               n = solve.solve();
               if (n != null) {
                  System.out.printf("depth: %d\n\n", n.getPath().length() / 10);
               } else {
                  System.out.println();
               }
            } else {
               System.out.println("Not solvable\n\n");
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static String generateRandomPuzzle() {
      String str = "012345678";
      ArrayList<Character> list = new ArrayList<>();

      for (char c : str.toCharArray()) {
         list.add(c);
      }

      Collections.shuffle(list);

      str = "";

      for (char c : list) {
         str += c;
      }

      return str;
   }

   private static String generateRandomPuzzle(int depth) {
      String out = "", str = "";
      Random rand = new Random();
      int[][] board = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
      int x = 0;
      int y = 0;
      int index;

      boolean[] possibleMoves = {true, false, false, true}; // right, up, left, down

      for (int i = 0; i <= depth; i++) {
         while (true) {
            if (possibleMoves[(index = rand.nextInt(4))]) {
               break;
            }
         }

         switch (index) {
            case 0:
               // swap with right
               swap(board, x + 1, y, x, y);
               x++;
               break;
            case 1:
               // swap with up
               swap(board, x, y - 1, x, y);
               y--;
               break;
            case 2:
               // swap with left
               swap(board, x - 1, y, x, y);
               x--;
               break;
            case 3:
               swap(board, x, y + 1, x, y);
               y++;
         }

         possibleMoves[0] = (x < 2);
         possibleMoves[1] = (y > 0);
         possibleMoves[2] = (x > 0);
         possibleMoves[3] = (y < 2);
      }

      for (int i = 0; i < 9; i++) {
         out += Integer.toString(board[i / 3][i % 3]);
      }

      return out;
   }

   private static void swap(int[][] list, int x1, int y1, int x2, int y2) {
      int temp = list[x1][y1];
      list[x1][y1] = list[x2][y2];
      list[x2][y2] = temp;
   }
}
