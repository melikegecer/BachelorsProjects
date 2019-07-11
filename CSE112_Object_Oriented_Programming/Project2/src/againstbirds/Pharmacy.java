package againstbirds;

public class Pharmacy extends GroundObjects {

   private int numberOfCureKits;

   public Pharmacy() {
      this.numberOfCureKits = (int) (Math.random() * 20 + 10);
      this.shotToDestroy = this.numberOfCureKits / 8;
   }

   @Override
   public void shooted() {
      System.out.print("pharmacy: From: " + this.getShotToDestroy() + " to: ");
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

   public int getNumberOfCureKits() {
      return numberOfCureKits;
   }

   public int getShotToDestroy() {
      return shotToDestroy;
   }

   public void setShotToDestroy(int shotToDestroy) {
      this.shotToDestroy = shotToDestroy;
   }
}
