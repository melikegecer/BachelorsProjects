package againstbirds;

public abstract class Item {

   private Location2D location;

   protected Item(int x, int y) {
      location = new Location2D(x, y);
   }

   public Location2D getLocation() {
      return location;
   }
}
