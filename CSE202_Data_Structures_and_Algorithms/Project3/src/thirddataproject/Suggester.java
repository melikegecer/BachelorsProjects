package thirddataproject;

import java.util.ArrayList;

public class Suggester {

   private Suggester() {
   }

   public static ArrayList<String> suggestMe(MyHash hash, String word) {
      ArrayList<String> aList = new ArrayList<>();
      ArrayList<String> rList = new ArrayList<>();
      
      for (int i = 0; i < word.length(); i++) {
         aList.add(minusOne(word, i));
         aList.add(plusOne(word, i));
      }
      
      for(int i=0; i<aList.size(); i++) {
         Element e = new Element(aList.get(i));
         if(hash.search(e)) {
            rList.add(aList.get(i));
         }
      }
      
      return rList;
   }

   private static String minusOne(String word, int index) {
      String res = "";

      for (int i = 0; i < word.length(); i++) {
         if (i == index) {
            res += (char) (word.charAt(index) - 1);
         } else {
            res += word.charAt(i);
         }
      }

      return res;
   }

   private static String plusOne(String word, int index) {
      String res = "";

      for (int i = 0; i < word.length(); i++) {
         if (i == index) {
            res += (char) (word.charAt(index) + 1);
         } else {
            res += word.charAt(i);
         }
      }

      return res;
   }
}
