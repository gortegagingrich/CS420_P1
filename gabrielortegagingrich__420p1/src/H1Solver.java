/**
 * Created by Gabriel on 2017/01/18.
 */
public class H1Solver extends Solver {
   private int sumH;

   public H1Solver(int[][] board) throws NoEmptyTileException {
      super(board);

      sumH = 0;
   }

   private int h1() {
      int out = 0;

      for (int i = 0; i < 9; i++) {
         if (board[i/3][i%3] != i) {
            out++;
         }
      }

      return out;
   }

   @Override
   public int solve() {
      return h1();
   }
}
