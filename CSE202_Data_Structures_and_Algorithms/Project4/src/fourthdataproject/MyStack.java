package fourthdataproject;

public class MyStack {

   Node top;
   int N;

   public MyStack() {
      top = null;
      N = 0;
   }

   boolean isEmpty() {
      return top == null;
   }

   void push(Node newNode) {
      newNode.next = top;
      top = newNode;
   }

   public Node pop() {
      Node e = top;
      if (!isEmpty()) {
         top = top.next;
      }
      return e;
   }
}