package againstbirds;

import java.util.ArrayList;

public class GameDisplay {

   private static char[][] displayMe;

   private GameDisplay() {
   }

   private static void setDisplay(ArrayList<Item> all) {
      char[][] displayable;
      displayable = new char[Location2D.getDimY()][Location2D.getDimX()];
      for (int i = 0; i < displayable.length; i++) {
         for (int j = 0; j < displayable[i].length; j++) {
            displayable[i][j] = ' ';
         }
      }
      for (int i = all.size()-1; i >= 0; i--) {
         int x = all.get(i).getLocation().getX();
         int y = all.get(i).getLocation().getY();

         if (all.get(i) instanceof Chicken) {
            displayable[y][x] = 'c';
         } else if (all.get(i) instanceof Egg) {
            displayable[y][x] = 'e';
         } else if (all.get(i) instanceof Duck) {
            displayable[y][x] = 'd';
         } else if (all.get(i) instanceof BirdNest) {
            displayable[y][x] = 'b';
         } else if (all.get(i) instanceof Pharmacy) {
            displayable[y][x] = 'p';
         } else if (all.get(i) instanceof Armory) {
            displayable[y][x] = 'a';
         } else if (all.get(i) instanceof Aircraft) {
            displayable[y][x] = 'A';
         }
      }
      addStars(displayable);
      printAll();
   }

   private static void addStars(char[][] A) {
      displayMe = new char[Location2D.getDimY() + 2][Location2D.getDimX() + 2];
      for (int i = 0; i < displayMe.length; i++) {
         displayMe[i][0] = '*';
         displayMe[i][displayMe[i].length - 1] = '*';
      }
      for (int i = 0; i < displayMe[0].length; i++) {
         displayMe[0][i] = '*';
         displayMe[displayMe.length - 1][i] = '*';
      }
      for (int i = 0; i < A.length; i++) {
         for (int j = 0; j < A[i].length; j++) {
            displayMe[i + 1][j + 1] = A[i][j];
         }
      }
   }

   private static void printAll() {
      for (int i = 0; i < displayMe.length; i++) {
         for (int j = 0; j < displayMe[i].length; j++) {
            System.out.print(displayMe[i][j]);
         }
         System.out.println();
      }
   }

   public static void Display(ArrayList<Item> all) {
      setDisplay(all);
   }
}