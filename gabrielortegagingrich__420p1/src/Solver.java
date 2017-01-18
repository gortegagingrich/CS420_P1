/**
 * Created by Gabriel on 2017/01/18.
 */
public abstract class Solver {
   protected int[] board[], empty;

   public Solver(int[][] board) throws NoEmptyTileException {
      this.board = board;

      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[i].length; i++) {
            if (board[i][j] == 0) {
               empty = new int[]{i, j};
            }
         }
      }

      if (empty == null) {
         throw (new NoEmptyTileException());
      }
   }

   /**
    * @return
    */
   abstract public int solve();
}
