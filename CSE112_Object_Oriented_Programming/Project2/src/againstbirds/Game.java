package againstbirds;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

   private Scanner input;
   private Random random;
   private Aircraft airCraft;
   private int infectedChickens;
   private int chickens;
   private int ducks;
   private int numberOfBirdnest;
   private ArrayList<Item> itemList;
   private long startTime;
   private long endTime;

   public Game(int dimX, int dimY, int infectedChickens, int chickens, int ducks) {
      Location2D.setDimXY(dimX, dimY);
      this.input = new Scanner(System.in);
      this.random = new Random();
      this.airCraft = new Aircraft();
      this.infectedChickens = infectedChickens;
      this.chickens = chickens;
      this.ducks = ducks;
      this.numberOfBirdnest = 0;
      this.itemList = new ArrayList<>();
      this.startTime = System.currentTimeMillis();
      this.endTime = 0;
      itemList.add(airCraft);
   }

   public void run() {
      createAllItems();

      while (gameEnds()) {
         System.out.println(airCraft.toString());

         //display the game
         GameDisplay.Display(itemList);

         //ask user to move aircraft and move it
         airCraftMoveMenu();

         //display after move
         GameDisplay.Display(itemList);

         //all movable objects move
         ArrayList<Item> removeList = new ArrayList<>();
         for (Item item : itemList) {
            if (item instanceof Enemies) {
               ((Enemies) item).move();
               //if reaches (dimY-1) (the ground) destroy it
               if (item.getLocation().getY() == (Location2D.getDimY())) {
                  removeList.add(item);
               } else {
//                  System.out.println("HERE?");
               }
            } else {
//               System.out.println("I am not enemy which moves.");
            }
         }

         //remove items which reached the ground
         for (int i = 0; i < removeList.size(); i++) {
            itemList.remove(removeList.get(i));
         }

         //collusion
         airCraft.collusion(itemList);

         //display after collusion
         GameDisplay.Display(itemList);

         //let chickens lay an egg randomly
         ArrayList<Egg> eggList = new ArrayList<>();
         for (Item item : itemList) {
            if (item instanceof Chicken) {
               if (random.nextBoolean()) {
                  //chicken lays an egg randomly
                  eggList.add(((Chicken) item).layAnEgg(itemList));
               }
            } else {
               //this is not a chicken
            }
         }

         //add the eggs from egglist to itemlist
         for (int i = 0; i < eggList.size(); i++) {
            itemList.add(eggList.get(i));
         }

         //ask user to shoot
         airCraftShootMenu();

         //show after shoot
         GameDisplay.Display(itemList);

         //flyingObjects and groundObjects rarely reappear
         if (((endTime - startTime) % 100 == 0) && (this.numberOfBirdnest > 0)) {
            createFlyingObjects();
         } else if (((endTime - startTime) % 200 == 0)) {
            createGroundObjects();
         }
         endTime = System.currentTimeMillis();

         countBirdNests();
      }
   }

   private void createAllItems() {
      createGroundObjects();
      createFlyingObjects();
   }

   private void createFlyingObjects() {
      Chicken chicken;
      for (int i = 0; i < this.chickens; i++) {
         chicken = new Chicken(false);
         itemList.add(chicken);
      }
      for (int i = 0; i < this.infectedChickens; i++) {
         chicken = new Chicken(true);
         itemList.add(chicken);
      }

      Duck duck;
      for (int i = 0; i < this.ducks; i++) {
         duck = new Duck();
         itemList.add(duck);
      }
   }

   private void createGroundObjects() {
      BirdNest birdnest = new BirdNest();
      itemList.add(birdnest);
      this.numberOfBirdnest++;

      Armory armory = new Armory();
      itemList.add(armory);

      Pharmacy pharmacy = new Pharmacy();
      itemList.add(pharmacy);
   }

   private void airCraftMoveMenu() {
      System.out.println("To move left  --- L/l");
      System.out.println("To move right --- R/r");
      String s = input.next();
      char move = s.charAt(0);
      System.out.println("Your choice: " + move);
      if (move == 'L' || move == 'l' || move == 'R' || move == 'r') {
         airCraft.move(move);
      } else {
         System.out.println("No choice such " + move + " try again.");
         airCraftMoveMenu();
      }
   }

   private void airCraftShootMenu() {
      System.out.println("To Shoot Up   --- U/u");
      System.out.println("To Shoot Down --- D/d");
      System.out.println("To Skip       --- S/s");
      String s = input.next();
      char shoot = s.charAt(0);
      if (shoot == 'U' || shoot == 'u' || shoot == 'D' || shoot == 'd' || shoot == 'S' || shoot == 's') {
         airCraft.throwBullet(shoot, itemList);
      } else {
         System.out.println("No choice such " + shoot + " try again.");
         airCraftShootMenu();
      }
   }

   private boolean gameEnds() {
      if (itemList.size() == 1) {
         System.out.println("YOU KILLED THEM ALL.\nCONGRATULATIONS.");
         return false;
      } else if (airCraft.getNumberOfBullets() == 0) {
         System.out.println("YOU LOST.\nYOU DO NOT HAVE ANY BULLET.\nGAME IS OVER.");
         return false;
      } else if (airCraft.getHealthLevel() == 0) {
         System.out.println("YOU LOST.\nYOU ARE DEAD.\nGAME IS OVER.");
         return false;
      }
      return true;
   }

   private void countBirdNests() {
      int count = 0;
      for (Item i : itemList) {
         if (i instanceof BirdNest) {
            count++;
         }
      }
      this.numberOfBirdnest = count;
   }
}
