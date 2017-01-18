/**
 * Created by Gabriel on 2017/01/18.
 */
public class NoEmptyTileException extends Exception {
   public NoEmptyTileException() {
      super("Board has no empty tile.");
   }
}
