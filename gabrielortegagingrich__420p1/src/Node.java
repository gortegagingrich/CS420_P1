/**
 * Created by gabriel on 1/19/17.
 */
class Node {
   final         int      h;
   final         int      g;
   private final String[] prevStates;
   private final String   state;

   Node(Node prev, String state, int h, int g) {
      if (prev == null) {
         prevStates = new String[]{};
      } else {
         prevStates = new String[prev.prevStates.length + 1];

         int i = 0;

         while (i < prevStates.length - 1) {
            prevStates[i] = prev.prevStates[i++];
         }

         prevStates[i] = prev.state;
      }

      this.state = state;

      this.h = h;
      this.g = g;

   }

   String getState() {
      return state;
   }

   int[][] getBoard() {
      int[][] board = new int[3][3];

      for (int i = 0; i < 9; i++) {
         board[i / 3][i % 3] = state.charAt(i) - '0';
      }

      return board;
   }

   String getPath() {
      String str = "";

      for (String state : prevStates) {
         str = String.format("%s%s\n", str, state);
      }

      str = String.format("%s%s", str, state);

      return str;
   }

   int getDepth() {
      int out = prevStates.length;

      return out;
   }
}

