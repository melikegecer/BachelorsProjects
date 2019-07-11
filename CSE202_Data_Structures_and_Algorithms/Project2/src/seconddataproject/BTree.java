package seconddataproject;

public class BTree {

   TreeNode root;

   public BTree() {
      root = null;
   }

   public void insert(TreeNode node) {
      TreeNode tmp = root;
      TreeNode parent = null;
      
      while(tmp!=null) {
         parent = tmp;
         if(tmp.left != null) {
            tmp = tmp.left;
         } else {
            tmp = tmp.right;
         }
      }
      
      if(parent == null) {
         root = node;
      } else {
         if(parent.left != null) {
            parent.left = node;
         } else {
            parent.right = node;
         }
      }
   }

   public void print() {
      root.postorder();
   }
}
