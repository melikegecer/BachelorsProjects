package testpack;

import fifthadvancedproject.Calculator;
import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

   public CalculatorTest() {
   }

   @Test
   public void addTest() {
      Calculator c = new Calculator();
      double res = c.add(3.4, 4.0);
      double ans = 7.4;
      System.out.println("add");
      System.out.println("r: " + res);
      System.out.println("a: " + ans);
      assertTrue(res == ans);
   }

   @Test
   public void subtractTest(){
      Calculator c = new Calculator();
      double res = c.subtract(4.0, 3.2);
      double ans = 0.8;
      System.out.println("substract");
      System.out.println("r:" + res);
      System.out.println("a:" + ans);
      assertEquals(res, ans, 0.0001);
   }
   
   @Test
   public void multiplyTest(){
      Calculator c = new Calculator();
      double res = c.multiply(4.0, 1.2);
      double ans = 4.8;
      System.out.println("multiply");
      System.out.println("r:" + res);
      System.out.println("a:" + ans);
      assertTrue(res==ans);
   }
   
   @Test (expected = ArithmeticException.class)
   public void divideTest(){
      Calculator c = new Calculator();
      
      System.out.println("divide");
      double res1 = c.divide(4, 2);
      double ans1 = 2;
      System.out.println("r:" + res1);
      System.out.println("a:" + ans1);
      assertTrue(res1==ans1);
      
      
      System.out.println("divide by 0");
      double res2 = c.divide(4, 0);
      double ans2 = 8;
      System.out.println("r:" + res2);
      System.out.println("a:" + ans2);
      assertTrue(res2==ans2);
   }
}
