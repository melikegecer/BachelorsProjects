package fourthdataproject;

public class Heap {

   HeapNode[] array;
   int count;

   public Heap(int N) {
      array = new HeapNode[N];
      count = 0;
   }

   boolean isEmpty() {
      return count == 0;
   }

   public HeapNode deleteMin() {
      HeapNode tmp;
      tmp = array[0];
      array[0] = array[count - 1];
      bubbleDown(0);
      count--;
      return tmp;
   }

   private void bubbleDown(int idx) {
      int left = 2 * idx + 1;
      int right = 2 * idx + 2;
      while ((left < count && array[idx].weight > array[left].weight) || (right < count && array[idx].weight > array[right].weight)) {
         if (right >= count || array[left].weight < array[right].weight) {
            swapNode(idx, left);
            idx = left;
         } else {
            swapNode(idx, right);
            idx = right;
         }
         left = 2 * idx + 1;
         right = 2 * idx + 2;
      }
   }

   private void swapNode(int fromIdx, int toIdx) {
      HeapNode tmp = array[fromIdx];
      array[fromIdx] = array[toIdx];
      array[toIdx] = tmp;
   }

   public void insert(HeapNode node) {
      array[count] = node;
      bubbleUp(count);
      count++;
   }

   private void bubbleUp(int idx) {
      int parent = (idx - 1) / 2; //
      while (parent >= 0 && array[parent].weight > array[idx].weight) {
         swapNode(parent, idx);
         idx = parent;
         parent = (idx - 1) / 2;
      }
   }

   public int search(HeapNode n) {
      for (int i = 0; i < count; i++) {
         if (array[i].weight == n.weight && array[i].from.getId() == n.from.getId() && array[i].to.getId() == n.to.getId()) {
            return i;
         }
      }
      return -1;
   }

   public void update(int idx, HeapNode newValue) {
      HeapNode oldVal = array[idx];
      array[idx] = newValue;
      if (oldVal.weight > newValue.weight) {
         bubbleDown(idx);
      } else {
         bubbleUp(idx);
      }
   }
}
