package seconddataproject;

public class MyStack {

   Node top;

   public MyStack() {
      top = null;
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

   public MyStack copyMe() {
      MyStack result = new MyStack();
      MyStack tmp = new MyStack();

      while (!this.isEmpty()) {
         tmp.push(this.pop());
      }

      while (!tmp.isEmpty()) {
         char c = tmp.pop().data;
         result.push(new Node(c));
         this.push(new Node(c));
      }

      return result;
   }

   public String printThis() {
      String s = "";

      MyStack copy = this.copyMe();
      while (!copy.isEmpty()) {
         s = copy.pop().data + s;
      }

      return s;
   }

   public int searchByChar(char c) {
      int count = 0;

      MyStack copy = this.copyMe();

      while (!copy.isEmpty()) {
         Node tmp = copy.pop();

         if (tmp.data == c) {
            return count;
         }
         count++;
      }

      return -1;
   }

   public Node getANode(int x) {
      MyStack copy = this.copyMe();
      int count = 0;

      while (!copy.isEmpty()) {
         Node tmp = copy.pop();

         if (count == x) {
            return tmp;
         }
         count++;
      }

      return null;
   }

   public int length() {
      int count = 0;

      MyStack copy = this.copyMe();

      while (!copy.isEmpty()) {
         Node tmp = copy.pop();
         count++;
      }

      return count;
   }

   public void eraseThis(Node n) {
      MyStack tmp = new MyStack();

      while (!this.isEmpty()) {
         Node x = this.pop();
         if (x.data != n.data) {
            tmp.push(x);
         } else {
            //erased here
         }
      }

      while (!tmp.isEmpty()) {
         char c = tmp.pop().data;
         this.push(new Node(c));
      }
   }

   public MyStack reverseMe() {
      MyStack copy = this.copyMe();
      MyStack result = new MyStack();

      while (!copy.isEmpty()) {
         result.push(copy.pop());
      }
      return result;
   }

   public void pushbetween(Node val1, Node val2, Node node) {
      MyStack m = new MyStack();

      while (!this.isEmpty()) {
         Node tmp = this.pop();
         m.push(tmp);
         if((val1.data == tmp.data) && (this.pop().data == val2.data)) {
            m.push(val1);
            m.push(node);
            m.push(val2);
            break;
         } 
      }
      
      while(!m.isEmpty()) {
         this.push(m.pop());
      }
   }
}
