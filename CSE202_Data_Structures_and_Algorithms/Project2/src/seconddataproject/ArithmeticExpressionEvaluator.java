package seconddataproject;

public class ArithmeticExpressionEvaluator {

   private String expression;

   public ArithmeticExpressionEvaluator(String expression) {
      this.expression = expression;
   }

   public void convertToPostFix() {
      String s = expression;
      MyStack stack = new MyStack();
      String postFix = "";

      for (int i = 0; i < s.length(); i++) {
         if (isItNumber(s.charAt(i))) {
            postFix += s.charAt(i);
         } else if (isItOperation(s.charAt(i))) {
            Node op = new Node(s.charAt(i));
            while ((!stack.isEmpty()) && (stack.top.data != '(')) {
               if (stack.top.precedence < op.precedence) {
                  postFix += stack.pop().data;
               } else {
                  break;
               }
            }
            stack.push(new Node(s.charAt(i)));
         } else if (s.charAt(i) == ')') {
            while ((!stack.isEmpty()) && (stack.top.data != '(')) {
               postFix += stack.pop().data;
            }
            if (!stack.isEmpty()) {
               stack.pop();
            }
         } else if (s.charAt(i) == '(') {
            stack.push(new Node(s.charAt(i)));
         } else {
            System.out.println("???");
         }
      }
      postFix += stack.pop().data;
      System.out.println("postfix: " + postFix);
   }

   public void convertToTree() {
      String s = "";
      for (int i = 0; i < expression.length(); i++) {
         s = expression.charAt(i) + s;
      }

      BTree b = new BTree();
      for (int i = 0; i < s.length(); i++) {
         b.insert(new TreeNode(s.charAt(i)));
      }

//      System.out.print("post: ");
      b.print();
//      System.out.println("");
   }

   public void evaluate() {
      String s = expression;
      MyStack stack = new MyStack();

      for (int i = 0; i < s.length(); i++) {
         if (isItNumber(s.charAt(i))) {
            stack.push(new Node(s.charAt(i) - 48));
         }
         if (isItOperation(s.charAt(i))) {
            char op = s.charAt(i);
            Node n2 = stack.pop();
            Node n1 = stack.pop();

            if (n1 != null && n2 != null) {
               if (op == '*') {
                  double d = n1.operand * n2.operand;
                  stack.push(new Node(d));
               } else if (op == '/') {
                  double d = n1.operand / n2.operand;
                  stack.push(new Node(d));
               } else if (op == '+') {
                  double d = n1.operand + n2.operand;
                  stack.push(new Node(d));
               } else if (op == '-') {
                  double d = n1.operand - n2.operand;
                  stack.push(new Node(d));
               }
            }
         }
      }
      System.out.println("result is: " + stack.top.operand);
   }

   private boolean isItOperation(char c) {
      if (c == '*' || c == '/' || c == '+' || c == '-') {
         return true;
      } else {
         return false;
      }
   }

   private boolean isItNumber(char c) {
      if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
         return true;
      } else {
         return false;
      }
   }
}
