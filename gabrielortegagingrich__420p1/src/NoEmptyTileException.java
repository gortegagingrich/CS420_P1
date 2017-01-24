/**
 * Created by Gabriel on 2017/01/18.
 */
class NoEmptyTileException extends Exception {
   NoEmptyTileException() {
      super("Board has no empty tile.");
   }
}
