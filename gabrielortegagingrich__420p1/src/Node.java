/**
 * Created by gabriel on 1/19/17.
 */
public class Node {
   int h, g;
   private String[] prevStates;
   private String state;

   public Node(Node prev, String state, int h, int g) {
      if (prev == null) {
         prevStates = new String[] {};
      } else {
         prevStates = new String[prev.prevStates.length + 1];

         int i = 0;

         while (i < prevStates.length-1) {
            prevStates[i] = prev.prevStates[i++];
         }

         prevStates[i] = prev.state;
      }

      this.state = state;

      this.h = h;
      this.g = g;
   }

   public String getState() {
      return state;
   }

   public int[][] getBoard() {
      int[][] board = new int[3][3];

      for (int i = 0; i < 9; i++) {
         board[i / 3][i % 3] = state.charAt(i) - '0';
      }

      return board;
   }

   public String getPath() {
      String str = "";

      for (String state : prevStates) {
         str = String.format("%s\n%s", str, state);
      }

      return str;
   }

   public int getDepth() {
      return prevStates.length;
   }
}
