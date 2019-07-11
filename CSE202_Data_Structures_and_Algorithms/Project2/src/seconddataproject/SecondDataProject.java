package seconddataproject;

public class SecondDataProject {

   public static void main(String[] args) {
      String s = "((5-4)/(8+6))*(7-1)";
      boolean checked = ParanthesisController.checkIt(s);

      if (checked) {
         ArithmeticExpressionEvaluator aee1 = new ArithmeticExpressionEvaluator(s);
         aee1.convertToPostFix();
      } else {
         System.out.println("Sorry...");
      }
      
      ArithmeticExpressionEvaluator aee2 = new ArithmeticExpressionEvaluator("54-86+/71-*");
      aee2.convertToTree();
      
      ArithmeticExpressionEvaluator aee3 = new ArithmeticExpressionEvaluator("54-86+/71-*");
      aee3.evaluate();
   }
}
