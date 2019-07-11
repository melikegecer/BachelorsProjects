package firstdataproject;

public class FirstDataProject {

   public static void main(String[] args) {
      String s = "1234";
      String s2 = "523";

      MyBigInteger mb = new MyBigInteger(s);
      MyBigInteger mb2 = new MyBigInteger(s2);

      System.out.println("mb1 is : " + mb.toString());
      System.out.println("mb2 is : " + mb2.toString());

      System.out.println("\n-----ADDING-----");
      System.out.println("mb1 + mb2 = " + mb.add(mb2).toString());

      System.out.println("\n-----SUBSTRACTING-----");
      if (s.length() > s2.length()) {
         System.out.println("mb1-mb2 = " + mb.subtract(mb2).toString());
      } else if (s.length() < s2.length()) {
         System.out.println("The answer is going to be minus, I can not answer that.");
      } else {
         if (s.charAt(0) > s2.charAt(0)) {
            System.out.println("mb1-mb2 = " + mb.subtract(mb2).toString());
         } else {
            System.out.println("The answer is going to be minus, I can not answer that.");
         }
      }

      System.out.println("\n-----MULTIPLY-----");
      System.out.println("mb1 * mb2 = " + mb.multiply(mb2).toString());

      System.out.println("\n-----EXPONENT-----");
      System.out.println("mb1 ^ mb2 = " + mb.exponent(mb2).toString());
   }
}
