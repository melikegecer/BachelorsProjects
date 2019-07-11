package fourthdataproject;

public class MyQueue {

   Node first;
   Node last;

   public MyQueue() {
      first = null;
      last = null;
   }

   boolean isEmpty() {
      return first == null;
   }

   public void enqueue(Node node) {
      if (!isEmpty()) {
         last.next = node;
      } else {
         first = node;
      }
      last = node;
   }

   public Node dequeue() {
      Node result;
      result = first;
      if (!isEmpty()) {
         first = first.next;
         if (first == null) {
            last = null;
         }
      }
      return result;
   }
}