package firstdataproject;

public class MyBigInteger implements Operations {

   private MyLinkedList list;

   public MyBigInteger() {
      this.list = new MyLinkedList();
   }

   public MyBigInteger(String s) {
      this();

      int length = s.length();

      for (int i = 0; i < length; i++) {
         int number = (int) s.charAt(i) - 48;
         int power = length - 1 - i;
         this.list.insertFirst(number, power);
      }
   }

   @Override
   public String toString() {
      String reverse = "";
      Node tmp = this.list.first;
      while (tmp != null) {
         reverse += tmp.number;
         tmp = tmp.next;
      }

      String normal = "";
      for (int i = reverse.length() - 1; i >= 0; i--) {
         normal += reverse.charAt(i);
      }

      return normal;
   }

   @Override
   public MyBigInteger add(MyBigInteger second) {
      MyBigInteger copyFirst = copyBigInteger(this);
      MyBigInteger copySecond = copyBigInteger(second);
      MyBigInteger result = new MyBigInteger();

      Node firstCurrent = copyFirst.list.first;
      Node secondCurrent = copySecond.list.first;
      
      if (copyFirst.list.length() > copySecond.list.length()) {
         copySecond = addToSecond(copyFirst, copySecond);
      } else if (copyFirst.list.length() < copySecond.list.length()) {
         copyFirst = addToFirst(copyFirst, copySecond);
      } else {
//         System.out.println("They have equal length.");
      }

      int number = 0;
      int power = 0;

      while (firstCurrent != null || secondCurrent != null) {

         if (firstCurrent == null) {
            number = secondCurrent.number;
            power = secondCurrent.power;
            result.list.insertLast(number, power);
            secondCurrent = secondCurrent.next;
         } else if (secondCurrent == null) {
            number = firstCurrent.number;
            power = firstCurrent.power;
            result.list.insertLast(number, power);
            firstCurrent = firstCurrent.next;
         } else {
            number = firstCurrent.number + secondCurrent.number;
            power = firstCurrent.power;

            if (number > 9) {
               int n = number % 10;
               result.list.insertLast(n, power);
               int x = number / 10;

               if (firstCurrent.next == null) {
                  copyFirst.list.insertLast(x, power + 1);
               } else {
                  firstCurrent.next.number += x;
               }
            } else {
               result.list.insertLast(number, power);
            }
            firstCurrent = firstCurrent.next;
            secondCurrent = secondCurrent.next;
         }
      }
      return result;
   }

   @Override
   public MyBigInteger subtract(MyBigInteger second) {
      MyBigInteger copyFirst = copyBigInteger(this);
      MyBigInteger copySecond = copyBigInteger(second);
      MyBigInteger result = new MyBigInteger();

      Node firstCurrent = copyFirst.list.first;
      Node secondCurrent = copySecond.list.first;

      if (copyFirst.list.length() > copySecond.list.length()) {
         copySecond = addToSecond(copyFirst, copySecond);
      } else if (copyFirst.list.length() < copySecond.list.length()) {
         copyFirst = addToFirst(copyFirst, copySecond);
      } else {
//         System.out.println("They have equal length.");
      }

      int number = 0;
      int power = 0;

      while (firstCurrent != null && secondCurrent != null) {
         if (firstCurrent == null) {
            System.out.println("first null");
         } else if (secondCurrent == null) {
            System.out.println("second null");
         } else {
            number = firstCurrent.number - secondCurrent.number;
            power = firstCurrent.power;
            if (number < 0) {
               number = (firstCurrent.number + 10) - secondCurrent.number;
               firstCurrent.next.number -= 1;
               result.list.insertLast(number, power);
            } else {
               result.list.insertLast(number, power);
            }
            firstCurrent = firstCurrent.next;
            secondCurrent = secondCurrent.next;
         }
      }
      return result;
   }

   @Override
   public MyBigInteger multiply(MyBigInteger second) {
      MyBigInteger copyFirst = copyBigInteger(this);
      MyBigInteger copySecond = copyBigInteger(second);
      MyBigInteger result = new MyBigInteger();
      
//      if (copyFirst.list.length() > copySecond.list.length()) {
//         copySecond = addToSecond(copyFirst, copySecond);
//      } else if (copyFirst.list.length() < copySecond.list.length()) {
//         copyFirst = addToFirst(copyFirst, copySecond);
//      } else {
////         System.out.println("They have equal length.");
//      }
      
      for (int i = 0; i < copyFirst.list.length() + copySecond.list.length(); i++) {
         result.list.insertFirst(0, i);
      }
      while (!isAllZero(copySecond)) {
         result = result.add(copyFirst);
         copySecond = copySecond.subtract(new MyBigInteger("1"));
      }
      return result;
   }

   @Override
   public MyBigInteger exponent(MyBigInteger second) {
      MyBigInteger copyFirst = copyBigInteger(this);
      MyBigInteger copySecond = copyBigInteger(second);
      MyBigInteger result = new MyBigInteger();
      
//      if (copyFirst.list.length() > copySecond.list.length()) {
//         copySecond = addToSecond(copyFirst, copySecond);
//      } else if (copyFirst.list.length() < copySecond.list.length()) {
//         copyFirst = addToFirst(copyFirst, copySecond);
//      } else {
////         System.out.println("They have equal length.");
//      }
      
      result.list.insertLast(1, 0);
      for (int i = 1; i < copyFirst.list.length() + copySecond.list.length(); i++) {
         result.list.insertLast(0, i);
      }
      while (!isAllZero(copySecond)) {
         result = result.multiply(copyFirst);
         copySecond = copySecond.subtract(new MyBigInteger("1"));
      }
      return result;
   }

   private MyBigInteger copyBigInteger(MyBigInteger bigInteger) {
      return new MyBigInteger(bigInteger.toString());
   }

   private MyBigInteger addToSecond(MyBigInteger first, MyBigInteger second) {
      Node tmp = second.list.first;
      for (int i = 0; i < first.list.length(); i++) {
         if (tmp != null) {
            tmp = tmp.next;
         } else {
            second.list.insertLast(0, i);
         }
      }
      return second;
   }

   private MyBigInteger addToFirst(MyBigInteger first, MyBigInteger second) {
      Node tmp = first.list.first;
      for (int i = 0; i < second.list.length(); i++) {
         if (tmp != null) {
            tmp = tmp.next;
         } else {
            first.list.insertLast(0, i);
         }
      }
      return first;
   }

   private boolean isAllZero(MyBigInteger big) {
      Node tmp = big.list.first;
      int count = 0;
      while (tmp != null) {
         if (tmp.number != 0) {
         } else {
            count++;
         }
         tmp = tmp.next;
      }
      return big.list.length() == count;
   }
}