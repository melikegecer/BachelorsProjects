package againstbirds;

public class Location2D {

   private int x;
   private int y;
   private static int dimX = -1;
   private static int dimY = -1;

   protected Location2D(int x, int y) {
      this.x = x;
      this.y = y;
   }

   public static void setDimXY(int dimX, int dimY) {
      if (Location2D.dimX == -1 && Location2D.dimY == -1) {
         Location2D.dimX = dimX;
         Location2D.dimY = dimY;
      }
   }

   public static int getDimX() {
      return dimX;
   }

   public static int getDimY() {
      return dimY;
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public boolean equals(Location2D location) {
      if (x == location.getX() && y == location.getY()) {
         return true;
      }
      return false;
   }
}
