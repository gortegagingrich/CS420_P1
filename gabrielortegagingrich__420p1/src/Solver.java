/**
 * Created by Gabriel on 2017/01/18.
 */
public abstract class Solver {
   protected int[] board[], empty;

   public Solver(int[][] board) throws NoEmptyTileException {
      this.board = board;

      for (int i = 0; i < board.length; i++) {
         empty = new int[]{i/3, i%3};
      }

      if (empty == null) {
         throw (new NoEmptyTileException());
      }
   }

   public static boolean isSolvable(int[][] board) {
      int inversionCount = 0;

      for (int i = 0; i < 8; i++) {
         if (board[i/3][i%3] > board[(i+1)/3][(i+1)%3]) {
            inversionCount++;
         }
      }

      return (inversionCount%2 == 0);
   }

   /**
    * @return
    */
   abstract public int solve();
}
