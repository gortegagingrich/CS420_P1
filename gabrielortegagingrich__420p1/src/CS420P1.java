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

         while (count++ < 50) {
            if ((str = scan.nextLine()) != null) {
               System.out.println(str);

               if (str.length() == 9) {
                  i = 0;
                  board = new int[3][3];

                  for (char c : str.toCharArray()) {
                     board[i / 3][i++ % 3] = c - '0';
                  }

                  if (true || Solver.isSolvable(board)) {
                     solve = new H1Solver(board);
                     n = solve.solve();
                     if (n != null) {
                        System.out.printf("H1 depth: %d", n.getPath().length() / 10);
                        System.out.printf("%s\n%s\n", n.getPath(), n.getState());
                     }

                     solve = new H2Solver(board);
                     System.out.printf("H2(n): %d\n\n", solve.solve().getPath().length() / 10);
                  } else {
                     System.out.println("Not solvable\n");
                  }
               }
            } else {
               System.out.println("\n" + str);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
