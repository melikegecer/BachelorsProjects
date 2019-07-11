package againstbirds;

import java.util.ArrayList;

public class Chicken extends Birds implements Infectable {

   private boolean infected;

   public Chicken(boolean infected) {
      this.infected = infected;
      if (this.infected) {
         this.shotToDestroy = 2;
      } else {
         this.shotToDestroy = 1;
      }
   }

   public Egg layAnEgg(ArrayList<Item> itemList) {
      Egg egg;
      if (this.infected) {
         egg = new Egg(infected);
      } else {
         egg = new Egg(infected);
      }
      return egg;
   }

   @Override
   public boolean infected() {
      return this.infected;
   }

   @Override
   public void shooted() {
      System.out.print("chicken: From: " + this.getShotToDestroy() + " to: ");
      this.setShotToDestroy(this.getShotToDestroy() - 1);
      System.out.println(this.getShotToDestroy());
   }

   @Override
   public boolean toDestroy() {
      if (this.getShotToDestroy() == 0) {
         return true;
      } else {
         return false;
      }
   }

   public int getShotToDestroy() {
      return shotToDestroy;
   }

   public void setShotToDestroy(int shotToDestroy) {
      this.shotToDestroy = shotToDestroy;
   }
}
