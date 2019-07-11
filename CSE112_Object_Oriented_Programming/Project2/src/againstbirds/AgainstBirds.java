package againstbirds;

import java.util.Scanner;

public class AgainstBirds {

   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);
      
      System.out.println("Enter the game size of X: ");
      int dimX = input.nextInt();

      System.out.println("Enter the game size of Y: ");
      int dimY = input.nextInt();

      System.out.println("Enter the number of infected chickens");
      int infectedChickens = input.nextInt();

      System.out.println("Enter the number of chickens");
      int chickens = input.nextInt();

      System.out.println("Enter the number of ducks");
      int ducks = input.nextInt();

      System.out.println("I am setting your game");
      Game myGame = new Game(dimX, dimY, infectedChickens, chickens, ducks);
      myGame.run();
   }
}