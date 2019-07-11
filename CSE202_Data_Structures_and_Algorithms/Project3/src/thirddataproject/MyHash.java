package thirddataproject;

public class MyHash {
   
   private MyLinkedList[] neg;
   private MyLinkedList[] pos;
   private int N = 1000;

   protected MyHash() {
      neg = new MyLinkedList[N];
      pos = new MyLinkedList[N];
      
      for(int i = 0; i<N; i++) {
         neg[i] = new MyLinkedList();
         pos[i] = new MyLinkedList();
      }
   }

   public void insert(Element e) {
      int hash = e.hashValue;
      if (hash < 0) {
         int index = (hash * -1) % N;
         neg[index].insertLast(e);
      } else {
         int index = hash % N;
         pos[index].insertLast(e);
      }
   }

   public boolean search(Element e) {
      int hash = e.hashValue;
      if (hash < 0) {
         int index = (hash * -1) % N;
         if (neg[index].search(e) != null) {
            return true;
         } else {
            return false;
         }
      } else {
         int index = hash % N;
         if (pos[index].search(e) != null) {
            return true;
         } else {
            return false;
         }
      }
   }

   public void delete(Element e) {
      int hash = e.hashValue;
      if(hash<0) {
         int index = (hash * -1) % N;
         neg[index].delete(e);
      } else {
         int index = hash % N;
         pos[index].delete(e);
      }
   }
}
