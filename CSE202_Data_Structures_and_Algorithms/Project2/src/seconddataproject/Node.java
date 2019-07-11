package seconddataproject;

public class Node {

   char operator;
   double operand;
   char data;
   Node next;
   int precedence;

   public Node(double data) {
      this.operand = data;
      this.data = (char) data;
   }

   public Node(char data) {
      this.operator = data;
      this.data = data;

      switch (data) {
         case '(':
            this.precedence = 1;
            break;
         case ')':
            this.precedence = 2;
            break;
         case '+':
            this.precedence = 3;
            break;
         case '-':
            this.precedence = 3;
            break;
         case '*':
            this.precedence = 4;
            break;
         case '/':
            this.precedence = 4;
            break;
      }
   }
}
