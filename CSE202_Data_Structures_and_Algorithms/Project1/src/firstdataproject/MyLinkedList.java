package firstdataproject;

public class MyLinkedList {

   Node first, last;

   public MyLinkedList() {
      this.first = null;
      this.last = null;
   }

   public void insertFirst(int number, int power) {
      Node newNode = new Node(number, power);
      if (first == null) {
         first = newNode;
         last = newNode;
      } else {
         newNode.next = first;
         first = newNode;
      }
   }

   public void insertLast(int number, int power) {
      Node newNode = new Node(number, power);
      if (last == null) {
         last = newNode;
         first = newNode;
      } else {
         last.next = newNode;
         last = newNode;
      }
   }
   
   public int length() {
      Node tmp = this.first;
      int count = 0;
      while(tmp != null) {
         count++;
         tmp = tmp.next;
      }
      return count;
   }
}