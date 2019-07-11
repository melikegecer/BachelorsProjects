package againstbirds;

public class Armory extends GroundObjects {

   private int numberOfBullets;

   public Armory() {
      this.numberOfBullets = (int) (Math.random() * 20 + 10);
      this.shotToDestroy = this.numberOfBullets / 10;
   }

   @Override
   public void shooted() {
      System.out.print("armory: From: " + this.getShotToDestroy() + " to: ");
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

   public int getNumberOfBullets() {
      return numberOfBullets;
   }

   public int getShotToDestroy() {
      return shotToDestroy;
   }

   public void setShotToDestroy(int shotToDestroy) {
      this.shotToDestroy = shotToDestroy;
   }
}
