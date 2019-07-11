package againstbirds;

import java.util.Random;

public abstract class Enemies extends FlyingObjects implements Shootable {

   protected int shotToDestroy;
   
   protected Enemies() {
      super(((int) (Math.random() * Location2D.getDimX())), 0);
   }

   public void move() {
      this.getLocation().setY(this.getLocation().getY() + 1);

      Random random = new Random();
      if (random.nextBoolean()) {
         this.getLocation().setX(this.getLocation().getX() - 1);
      } else {
         this.getLocation().setX(this.getLocation().getX() + 1);
      }

      if (this.getLocation().getX() < 0) {
         this.getLocation().setX(0);
      } else if (this.getLocation().getX() >= Location2D.getDimX()) {
         this.getLocation().setX(Location2D.getDimX() - 1);
      } else if (this.getLocation().getY() >= (Location2D.getDimY() - 2)) {
         this.getLocation().setY(Location2D.getDimY() - 2);
      }
   }
}
