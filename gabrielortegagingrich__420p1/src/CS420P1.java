/**
 * Created by Gabriel on 2017/01/18.
 */
public class CS420P1 {
   public static void main(String[] args) throws NoEmptyTileException {
      Solver solver = new H2Solver(new int[][] {{3,1,0},{4,5,2},{6,7,8}});
      System.out.printf("H(n): %d\n\n",solver.solve());

      solver = new H1Solver(new int[][] {{3,1,0},{4,5,2},{6,7,8}});
      System.out.printf("H(n): %d\n\n",solver.solve());
   }
}
