import java.io.File;
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

         while (scan.hasNext() && (str = scan.nextLine()) != null) {
            if (str.length() == 9) {
               System.out.printf("Puzzle: %s\n", str);

               i = 0;
               board = new int[3][3];

               for (char c : str.toCharArray()) {
                  board[i / 3][i++ % 3] = c - '0';
               }

               if (Solver.isSolvable(board)) {
                  solve = new H1Solver(board);
                  n = solve.solve();
                  if (n != null) {
                     System.out.printf("H1 depth: %d\n", n.getPath().length() / 10);
                     System.out.printf("Path:%s\n%s\n\n", n.getPath(), n.getState());
                  }

                  solve = new H2Solver(board);
                  n = solve.solve();
                  if (n != null) {
                     System.out.printf("H2 depth: %d\n", n.getPath().length() / 10);
                     System.out.printf("Path:%s\n%s\n\n\n", n.getPath(), n.getState());
                  } else {
                     System.out.println();
                  }
               } else {
                  System.out.println("Not solvable\n\n");
               }
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
