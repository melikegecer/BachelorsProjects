package againstbirds;

public class BirdNest extends GroundObjects {

   public BirdNest() {
      this.shotToDestroy = 10;
   }

   @Override
   public void shooted() {
      System.out.print("birdnest: From: " + this.getShotToDestroy() + " to: ");
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
