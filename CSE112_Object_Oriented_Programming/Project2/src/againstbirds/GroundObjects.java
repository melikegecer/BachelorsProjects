package againstbirds;

public abstract class GroundObjects extends Item implements Shootable {

   protected int shotToDestroy;
   
   protected GroundObjects() {
      super(((int) (Math.random() * Location2D.getDimX())), (Location2D.getDimY()-1));
   }
}
