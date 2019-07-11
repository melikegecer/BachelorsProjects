package againstbirds;

import java.util.ArrayList;

public class Aircraft extends FlyingObjects {

   private int healthLevel;
   private int numberOfBullets;
   private int cureKit;
   private boolean infected;

   public Aircraft() {
      super(((Location2D.getDimX() / 2) - 1), (Location2D.getDimY() - 2));
      this.healthLevel = 1000;
      this.numberOfBullets = 10;
      this.cureKit = 0;
      this.infected = false;
   }

   public void move(char c) {
      switch (c) {
         case 'L':
         case 'l':
            this.getLocation().setX(this.getLocation().getX() - 1);
            break;
         case 'R':
         case 'r':
            this.getLocation().setX(this.getLocation().getX() + 1);
            break;
         default:
            System.out.println("This should not be printed.");
            break;
      }

      if (this.getLocation().getX() <= 0) {
         System.out.println("You reached the left border.");
         this.getLocation().setX(0);
      } else if (this.getLocation().getX() >= Location2D.getDimX()) {
         System.out.println("You reached the right border.");
         this.getLocation().setX(Location2D.getDimX() - 1);
      }
   }

   public void throwBullet(char choose, ArrayList<Item> itemList) {
      Item nearest = null;
      int max = -1;
      for (Item item : itemList) {
         if (item instanceof Shootable) {
            if (item.getLocation().getX() == this.getLocation().getX()) {
               if (choose == 'U' || choose == 'u') {
                  if (item instanceof FlyingObjects) {
                     //you need to get the first biggest positionY
                     if (item.getLocation().getY() > max) {
                        max = item.getLocation().getY();
                        System.out.println("YOU HIT THE " + item.getClass().getSimpleName());
                        nearest = item;
                     } else {
//                        System.out.println("HERE.");
                     }
                  } else {
                     //not a flying object
                  }
               } else if (choose == 'D' || choose == 'd') {
                  if (item instanceof GroundObjects) {
                     //you need to get the first biggest positionY
                     if (item.getLocation().getY() > max) {
                        max = item.getLocation().getY();
                        System.out.println("YOU HIT THE " + item.getClass().getSimpleName());
                        nearest = item;
                     } else {
//                        System.out.println("HERE.");
                     }
                  } else {
                     //not a ground object
                  }
               } else if (choose == 'S' || choose == 's') {
                  //skipped
                  System.out.println("You skipped that shoot.");
                  break;
               } else {
                  //any char except s,u,d
                  break;
               }
            } else {
               //to shoot an object, positonX needs to be same with aircrafts positionX
            }
         } else {
            //not a shootable
         }
      }
      //what happened after the shoot
      if (nearest instanceof Shootable) {
         ((Shootable) nearest).shooted();
         this.setNumberOfBullets(this.getNumberOfBullets() - 1);
      } else {
         //it is not shootable
      }

      ArrayList<Item> destroyList = new ArrayList<>();
      for (Item item : itemList) {
         if (item instanceof Shootable) {
            if (((Shootable) item).toDestroy()) {
               aircraftDestroyedAnObject(item);
               destroyList.add(item);
//                  System.out.println("YOU DESTROYED THE " + item.getClass().getSimpleName());
            }
         }
      }

      for (int i = 0; i < destroyList.size(); i++) {
         itemList.remove(destroyList.get(i));
      }
   }

   private void aircraftDestroyedAnObject(Item item) {
      if (item instanceof Chicken) {
         this.setHealthLevel(this.getHealthLevel() + 20);
      } else if (item instanceof Egg) {
         this.setHealthLevel(this.getHealthLevel() + 20);
      } else if (item instanceof Duck) {
         this.setHealthLevel(this.getHealthLevel() * 4);
      } else if (item instanceof BirdNest) {
      } else if (item instanceof Armory) {
         Armory a = (Armory) item;
         this.setNumberOfBullets(this.getNumberOfBullets() + a.getNumberOfBullets());
      } else if (item instanceof Pharmacy) {
         Pharmacy p = (Pharmacy) item;
         this.setCureKit(this.getCureKit() + p.getNumberOfCureKits());
      }
   }

   public void collusion(ArrayList<Item> itemList) {
      int healthConstant = 0;
      ArrayList<Item> collusionList = new ArrayList<>();
      for (Item item : itemList) {
         if (item instanceof Enemies) {
            healthConstant = checkCollusion(item, collusionList);
         } else {
//               System.out.println("This is not an enemy.");
         }
      }
      this.setHealthLevel(this.getHealthLevel() - healthConstant);

      //delete enemy from itemList
      for (int i = 0; i < collusionList.size(); i++) {
         itemList.remove(collusionList.get(i));
      }
   }

   private int checkCollusion(Item enemy, ArrayList<Item> cList) {
      if (this.getLocation().equals(enemy.getLocation())) {
         cList.add(enemy);
         if (enemy instanceof Infectable) {
            if (((Infectable) enemy).infected()) {
               this.setInfected(true);
               if (this.getCureKit() > 0) {
                  this.setInfected(false);
                  this.setCureKit(this.getCureKit() - 1);
                  return 0;
               } else {
                  System.out.println("You got infection and you do not have cure kit.");
               }
               return 10;
            } else {
               return 0;
            }
         } else {
            return 20;
         }
      } else {
//         System.out.println("There is no collusion.");
      }
      return 0;
   }

   @Override
   public String toString() {
      return "Your report:\nHealth Level: " + this.healthLevel + 
              "\nNumber Of Bullets:" + this.numberOfBullets + 
              "\nNumber Of Cure Kits: " + this.cureKit;
   }

   public int getHealthLevel() {
      return healthLevel;
   }

   public void setHealthLevel(int healthLevel) {
      this.healthLevel = healthLevel;
   }

   public int getNumberOfBullets() {
      return numberOfBullets;
   }

   public void setNumberOfBullets(int numberOfBullets) {
      this.numberOfBullets = numberOfBullets;
   }

   public int getCureKit() {
      return cureKit;
   }

   public void setCureKit(int cureKit) {
      this.cureKit = cureKit;
   }

   public boolean isInfected() {
      return infected;
   }

   public void setInfected(boolean infected) {
      this.infected = infected;
   }
}
