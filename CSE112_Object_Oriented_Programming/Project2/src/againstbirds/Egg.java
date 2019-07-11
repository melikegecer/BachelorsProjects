package againstbirds;

public class Egg extends Enemies implements Infectable {

   private boolean infected;

   public Egg(boolean infected) {
      //starts from the top
      this.infected = infected;
      if (this.infected) {
         this.shotToDestroy = 2;
      } else {
         this.shotToDestroy = 1;
      }
   }

   @Override
   public boolean infected() {
      return this.infected;
   }

   @Override
   public void shooted() {
      System.out.print("egg: From: " + this.getShotToDestroy() + " to: ");
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
