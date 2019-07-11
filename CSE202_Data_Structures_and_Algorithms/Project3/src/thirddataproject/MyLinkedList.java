package thirddataproject;

public class MyLinkedList {

   Element first, last;

   public MyLinkedList() {
      first = null;
      last = null;
   }

   public void insertLast(Element e) {
      if (first == null) {
         first = e;
         last = e;
      } else {
         last.next = e;
         last = e;
      }
   }

   public Element search(Element e) {
      Element tmp = first;
      while (tmp != null) {
         if (tmp.equals(e)) {
            return tmp;
         }
         tmp = tmp.next;
      }
      return null;
   }

   public void delete(Element e) {
      Element prev = null, tmp = first;
      if (tmp.equals(e)) {
         first = first.next;
      }
      while (tmp != null) {
         prev = tmp;
         tmp = tmp.next;
         if (tmp.equals(e)) {
            prev.next = tmp.next;
            break;
         }
      }
   }

   public void printMe() {
      Element tmp = first;
      while (tmp != null) {
         System.out.print(tmp.data + " ");
         tmp = tmp.next;
      }
   }
}
