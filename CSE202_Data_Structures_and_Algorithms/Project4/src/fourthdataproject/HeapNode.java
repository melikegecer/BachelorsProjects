package fourthdataproject;

public class HeapNode {

   City from;
   City to;
   double weight;

   public HeapNode(City from, City to, double weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
   }
}