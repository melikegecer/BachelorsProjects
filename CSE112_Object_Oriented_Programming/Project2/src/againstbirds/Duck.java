package againstbirds;

public class Duck extends Birds {

   public Duck() {
      this.shotToDestroy = 4;
   }

   @Override
   public void shooted() {
      System.out.print("Duck: From: " + this.getShotToDestroy() + " to: ");
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
