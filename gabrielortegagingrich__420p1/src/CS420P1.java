import java.util.Random;
import java.util.Scanner;

/**
 * Created by Gabriel on 2017/01/18.
 */
public class CS420P1 {
   public static void main(String[] args) throws NoEmptyTileException {
      Scanner in = new Scanner(System.in);

      main:
      for (; ; ) {
         System.out.print("Chose an option:\n(1) Solve random puzzle\n(2) Enter your own puzzle\n(3) Exit\n> ");

         switch (in.nextLine()) {
            case "1":
               testRandom();
               break;
            case "2":
               testInput();
               break;
            case "3":
               break main;
            default:
               System.out.println("Invalid input\n");
               break;
         }
      }
   }


   private static void testRandom() throws NoEmptyTileException {
      String str = generateRandomPuzzle(50);
      Solver solver;
      int i = 0;
      int[][] list = new int[3][3];

      for (char c : str.toCharArray()) {
         list[i / 3][i++ % 3] = c - '0';
      }

      System.out.println("Puzzle: ");
      for (i = 0; i < 9; ) {
         System.out.print(list[i / 3][i++ % 3]);

         if (i % 3 == 0) {
            System.out.println();
         }
      }

      solver = new H1Solver(list);
      System.out.printf("\nH1:\n%s", solver.solve().toString());

      solver = new H2Solver(list);
      System.out.printf("H2:\n%s\n\n", solver.solve().toString());
   }

   private static void testInput() throws NoEmptyTileException {
      Solver solver;
      String str;
      int[][] board = new int[3][3];
      int i = 0;
      Scanner scan = new Scanner(System.in);

      for (; ; ) {
         System.out.print("Enter a puzzle in one line (012345678): ");

         str = scan.nextLine();

         for (char c : str.toCharArray()) {
            board[i / 3][i++ % 3] = c - '0';
         }

         if (Solver.isSolvable(board)) {
            break;
         }

         System.out.println("Puzzle entered not solvable.");
      }

      solver = new H1Solver(board);

      System.out.println("Puzzle: ");
      for (i = 0; i < 9; ) {
         System.out.print(str.charAt(i++));

         if (i % 3 == 0) {
            System.out.println();
         }
      }

      System.out.printf("\nH1:\n%s\n", solver.solve().toString());

      solver = new H2Solver(board);
      System.out.printf("H2:\n%s\n\n", solver.solve().toString());
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
