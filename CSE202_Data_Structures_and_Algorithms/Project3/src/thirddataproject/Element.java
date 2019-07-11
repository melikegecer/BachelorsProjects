package thirddataproject;

public class Element {

   String data;
   int hashValue;
   Element next;

   public Element(String data) {
      this.data = data;
      this.hashValue = data.hashCode();
      this.next = null;
   }
   
   public boolean equals(Object o) {
      if (o instanceof Element) {
         Element e = (Element) o;
         return data.equals(e.data);
      }
      return false;
   }

   public String toString() {
      return data.toString();
   }
}
