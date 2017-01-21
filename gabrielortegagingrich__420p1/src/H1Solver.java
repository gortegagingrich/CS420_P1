/**
 * Created by Gabriel on 2017/01/18.
 */
public class H1Solver extends Solver {

   public H1Solver(int[][] board) throws NoEmptyTileException {
      super(board);
   }

   /**
    * Number of incorrect tiles
    *
    * @param board
    * @return
    */
   public int h(int[][] board) {
      int out = 0;

      for (int i = 0; i < 9; i++) {
         if (board[i/3][i%3] != 0 && board[i/3][i%3] != i) {
            out++;
         }
      }

      return out;
   }
}
