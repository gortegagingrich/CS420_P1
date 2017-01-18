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

         int i;

         while (scan.hasNext()) {
            if ((str = scan.nextLine()) != null) {
               System.out.println(str);

               if (str.length() == 9) {
                  i = 0;
                  board = new int[3][3];
                  for (char c : str.toCharArray()) {
                     board[i / 3][i++ % 3] = c - '0';
                  }

                  solve = new H1Solver(board);
                  System.out.printf("H1(n): %d\n", solve.solve());

                  solve = new H2Solver(board);
                  System.out.printf("H2(n): %d\n\n", solve.solve());
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
