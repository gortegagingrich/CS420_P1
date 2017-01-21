/**
 * Created by gabriel on 1/18/17.
 */
public class H2Solver extends Solver {
    private int sumH;

    public H2Solver(int[][] board) throws NoEmptyTileException {
        super(board);
    }

    /**
     * Manhattan Distance
     *
     * @param board
     * @return
     */
    public int h(int[][] board) {
        int out = 0;
        int expectedX, expectedY, distance;

        for (int i = 0; i < 9; i++) {
            if (board[i / 3][i % 3] != 0) {
                expectedX = board[i / 3][i % 3] / 3;
                expectedY = board[i / 3][i % 3] % 3;

                distance = Math.abs(expectedX - i / 3);
                distance += Math.abs(expectedY - i % 3);
                out += distance;
            }
        }

        return out;
    }
}
