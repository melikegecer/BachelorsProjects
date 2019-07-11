package seconddataproject;

public class TreeNode {

   char data;
   TreeNode left;
   TreeNode right;

   public TreeNode(char data) {
      this.data = data;
      left = null;
      right = null;
   }

   public void postorder() {
      if (left != null) {
         left.postorder();
      }
      if (right != null) {
         right.postorder();
      }
      System.out.print(data);
   }
}