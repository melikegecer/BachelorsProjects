package fourthdataproject;

public class City {

   private String name;
   private String color;
   private int id;
   private static int count = 0;

   public City(String name) {
      this.name = name;
      this.id = count;
      count++;
   }

   public int getId() {
      return id;
   }   

   @Override
   public String toString() {
      return name + color;
   }

   @Override
   public boolean equals(Object obj) {
      return super.equals(obj);
   }

   public String getColor() {
      return color;
   }

   public String getName() {
      return name;
   }

   public void setColor(String color) {
      this.color = color;
   }
}
