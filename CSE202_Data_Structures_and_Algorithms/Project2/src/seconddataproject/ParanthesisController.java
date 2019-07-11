package seconddataproject;

public class ParanthesisController {

   private static MyStack stack = new MyStack();

   public ParanthesisController() {
   }

   public static boolean checkIt(String s) {
      boolean balanced = true;
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         char a;

         switch (c) {
            case '(':
               stack.push(new Node(c));
               break;
            case ')':
               if (stack.isEmpty()) {
                  balanced = false;
               } else {
                  a = (char) (stack.pop().data);
               }
               break;
         }
      }

      if (!stack.isEmpty()) {
         balanced = false;
      }

      if (!balanced) {
         System.out.println("Are you sure that your paranthesis are balanced?");
      } else {
         System.out.println("Let's solve it !!");
      }

      return balanced;
   }
}
