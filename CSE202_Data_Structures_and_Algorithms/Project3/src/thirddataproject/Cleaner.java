package thirddataproject;

public class Cleaner {

   private Cleaner() {
   }

   public static String cleanString(String s) {
      String result = "";
      s = s.toLowerCase();

      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c == 'ğ' || c == 'ü' || c == 'ş' || c == 'ı' || c == 'ö' || c == 'ç') {
            result += getChar(c);
         } else if (c >= 'a' && c <= 'z') {
            result += c;
         } else if (c == ' ') {
            result += c;
         }
      }

      return result;
   }

   private static char getChar(char c) {
      switch (c) {
         case 'ğ':
            c = 'g';
            break;
         case 'ü':
            c = 'u';
            break;
         case 'ş':
            c = 's';
            break;
         case 'ı':
            c = 'i';
            break;
         case 'ö':
            c = 'o';
            break;
         case 'ç':
            c = 'c';
            break;
      }
      return c;
   }
}
