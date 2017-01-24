import java.util.Comparator;

class NodeComparator implements Comparator<Node> {

   @Override
   public int compare(Node n1, Node n2) {
      return (n1.g + n1.h) - (n2.g + n2.h);
   }
}
