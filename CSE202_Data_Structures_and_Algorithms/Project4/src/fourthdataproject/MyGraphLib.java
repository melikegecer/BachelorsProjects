package fourthdataproject;

import java.util.ArrayList;
import java.util.Collections;

public class MyGraphLib {

   private double[][] map;
   private boolean[] visited;
   private int size;
   private City[] cityList;
   private ArrayList<String> colors;

   public MyGraphLib(int N) {
      this.size = N;
      colors = new ArrayList<>();
      colors.add("c1");
      colors.add("c2");
      colors.add("c3");
      colors.add("c4");
      colors.add("c5");
      colors.add("c6");
      colors.add("c7");
      colors.add("c8");
      colors.add("c9");
      colors.add("c10");
      colors.add("c11");
      colors.add("c12");
      colors.add("c13");
      colors.add("c14");
      colors.add("c15");
      colors.add("c16");
      colors.add("c17");
      colors.add("c18");
      colors.add("c19");
      colors.add("c20");

      map = new double[size][size];
      for (int i = 0; i < map.length; i++) {
         for (int j = 0; j < map[0].length; j++) {
            map[i][j] = 0.0;
         }
      }

      visited = new boolean[size];
      for (int i = 0; i < visited.length; i++) {
         visited[i] = false;
      }

      cityList = new City[N];
   }

   public int vertexCount() {
      int count = 0;
      for (int i = 0; i < cityList.length; i++) {
         if (cityList[i] != null) {
            count++;
         }
      }
      return count;
   }

   public City findCity(String name) {
      for (int i = 0; i < cityList.length; i++) {
         if (name.equals(cityList[i].getName())) {
            return cityList[i];
         }
      }
      return null;
   }

   public City[] getVertices() {
      City[] c = new City[vertexCount()];

      int i = 0;
      for (int j = 0; j < cityList.length; j++) {
         if (cityList[j] != null) {
            c[i] = cityList[j];
            i++;
         }
      }
      return c;
   }

   public void addVertex(City x) {
      cityList[x.getId()] = x;
   }

   public void removeVertex(City x) {
      cityList[x.getId()] = null;
   }

   public int edgeCount() {
      int count = 0;
      for (int i = 0; i < map.length; i++) {
         for (int j = 0; j < map[0].length; j++) {
            if (map[i][j] != 0.0) {
               count++;
            }
         }
      }
      return 0;
   }

   public boolean isVertex(City x) {
      if (findCity(x.getName()) != null) {
         return true;
      }
      return false;
   }

   public int degree(City x) {
      int countRow = 0;
      int countColumn = 0;

      for (int i = 0; i < map.length; i++) {
         if (map[x.getId()][i] != 0.0) {
            countRow++;
         }
      }

      for (int i = 0; i < map[0].length; i++) {
         if (map[i][x.getId()] != 0.0) {
            countColumn++;
         }
      }
      return countColumn + countRow;
   }

   public City[] getNeighbors(City x) {
      int count = 0;
      for (int i = 0; i < map[x.getId()].length; i++) {
         if (map[x.getId()][i] != 0.0) {
            count++;
         }
      }

      City[] cc = new City[count];
      for (int i = 0; i < cc.length; i++) {
         if (map[x.getId()][i] != 0.0) {
            count++;
         }
      }

      return cityList;
   }

   public void addEdge(City from, City to, double weight) {
      map[from.getId()][to.getId()] = weight;
   }

   public void removeEdge(City from, City to) {
      map[from.getId()][to.getId()] = 0.0;
   }

   public boolean isEdge(City from, City to) {
      if (map[from.getId()][to.getId()] != 0.0) {
         return true;
      }
      return false;
   }

   public double weight(City from, City to) {
      return map[from.getId()][to.getId()];
   }

   public void printBFS(City first) {
      City initial = findCity(first.getName());
      if (initial == null) {
         System.out.println("i dont have this city");
         return;
      }

      visited[initial.getId()] = true;
      MyQueue q = new MyQueue();
      q.enqueue(new Node(initial));

      while (!q.isEmpty()) {
         Node w = q.dequeue();
         System.out.println(w.data.getName());
         for (int i = 0; i < map.length; i++) {
            if (!visited[i] && map[w.data.getId()][i] != 0.0) {
               q.enqueue(new Node(cityList[i]));
               visited[i] = true;
            }
         }
      }
   }

   public void printDFS(City first) {
      City initial = findCity(first.getName());
      if (initial == null) {
         System.out.println("i dont have this city");
         return;
      }

      visited[initial.getId()] = true;
      System.out.println(initial.getName());
      for (int i = 0; i < map[initial.getId()].length; i++) {
         if (!visited[i] && map[initial.getId()][i] != 0.0) {
            printDFS(cityList[i]);
         }
      }
   }

   private int minDistance(double[] distance) {
      double min = Double.MAX_VALUE;
      int minIndex = -1;

      for (int i = 0; i < size; i++) {
         if (distance[i] <= min) {
            min = distance[i];
            minIndex = i;
         }
      }

      return minIndex;
   }

   public void dijkstra(City first) {
      long start = System.currentTimeMillis();
      City initial = findCity(first.getName());
      if (initial == null) {
         System.out.println("i dont have this city");
         return;
      }

      double[] distance = new double[size];
      for (int i = 0; i < size; i++) {
         distance[i] = Double.MAX_VALUE;
      }
      distance[initial.getId()] = 0;

      for (int count = 0; count < size - 1; count++) {
         int u = minDistance(distance);

         for (int i = 0; i < size; i++) {
            boolean edge = isEdge(findCity(cityList[u].getName()), findCity(cityList[i].getName()));
            if (edge && distance[u] + map[u][i] < distance[i]) {
               distance[i] = distance[u] + map[u][i];
            }
         }
      }

      for (int i = 0; i < size; i++) {
         System.out.println("from " + initial.getName() + " to " + cityList[i].getName() + " distance is " + distance[i]);
      }

      long end = System.currentTimeMillis();
      long x = end - start;
      System.out.println(x + " milisecond");
   }

   public void bellmanFord(City first) {
      long start = System.currentTimeMillis();

      City initial = findCity(first.getName());
      if (initial == null) {
         System.out.println("i dont have this city");
         return;
      }

      double[] distance = new double[size];
      double[] before = new double[size];

      for (int i = 0; i < size; i++) {
         distance[i] = Double.MAX_VALUE;
         before[i] = -1.0;
      }

      distance[initial.getId()] = 0.0;

      for (int i = 0; i < size; i++) {
         for (int j = 0; j < size; j++) {
            double x = map[i][j] + distance[i];
            if (distance[j] > x) {
               distance[j] = x;
               before[j] = i * 1.0;
            }
         }
      }

      for (int i = 0; i < size; i++) {
         if (i == initial.getId()) {
         } else {
            System.out.println("From " + initial.getName() + " to " + cityList[i].getName() + " is " + distance[i]);
         }
      }

      long end = System.currentTimeMillis();
      long x = end - start;
      System.out.println(x + " milisecond.");
   }

   public MyGraphLib findMinimumSpanningTree() {
      MyGraphLib res = new MyGraphLib(size);
      return res;
   }

   public boolean hasHamiltonPath() {
      //n vertices
      int n = size;
      if (n < 3) {
         return false;
      }
      //all vertices must have degree>=2
      for (int i = 0; i < n; i++) {
         if (degree(cityList[i]) < (n / 2)) {
            return false;
         }
      }
      return true;
   }

   public boolean hasEulerPath() {
      //n vertices
      int n = size;
      //only 2 nodes can be odd
      int odd = 0;
      for (int i = 0; i < n; i++) {
         if (degree(cityList[i]) % 2 == 1) {
            odd++;
         }
      }

      if (odd != 2) {
         return false;
      }

      return false;
   }

   public int findConnectedComponents() {
      int count = 0;

      for (int i = 0; i < visited.length; i++) {
         visited[i] = false;
      }

      for (int i = 0; i < visited.length; i++) {
         if (!visited[i]) {
            visited[i] = true;
            DFS(cityList[i]);
            count++;
         }
      }

      return count;
   }

   private void DFS(City initial) {
      visited[initial.getId()] = true;
      for (int i = 0; i < map[initial.getId()].length; i++) {
         if (!visited[i] && map[initial.getId()][i] != 0.0) {
            DFS(cityList[i]);
         }
      }
   }

   public boolean hasBridge() {
      boolean result = false;
      for (int i = 0; i < map.length; i++) {
         for (int j = 0; j < map[0].length; j++) {
            City from = cityList[i];
            City to = cityList[j];
            double weight = weight(from, to);

            removeEdge(from, to);
            int c = findConnectedComponents();
//            System.out.println(c);
            if (c > 1) {
               result = true;
//               System.out.println("from: " + from.getName() + " to: " + to.getName());
            }
            addEdge(from, to, weight);
         }
      }
      return result;
   }

   public boolean isIsomorphic(MyGraphLib second) {
      if ((this.edgeCount() != second.edgeCount()) && (this.vertexCount() != second.vertexCount())) {
         return false;
      } else {

         ArrayList<Integer> a1 = new ArrayList<>();
         for (int i = 0; i < this.cityList.length; i++) {
            a1.add(this.degree(this.cityList[i]));
         }

         ArrayList<Integer> a2 = new ArrayList<>();
         for (int i = 0; i < second.cityList.length; i++) {
            a2.add(second.degree(second.cityList[i]));
         }

         Collections.sort(a1);
         Collections.sort(a2);

         for (int i = 0; i < a1.size(); i++) {
            if (a1.get(i) != a2.get(i)) {
               return false;
            }
         }
         return true;
      }
   }

   public int paint() {
      int count = 0;

      MyQueue q = new MyQueue();
      q.enqueue(new Node(cityList[0]));

      while (!q.isEmpty()) {
         City c = q.dequeue().data;
         c.setColor(colors.get((int) (Math.random() * colors.size())));
         City[] list = getNeighbors(c);
         for (int i = 0; i < list.length; i++) {
            q.enqueue(new Node(list[i]));
            while (!c.getColor().equals(list[i].getColor())) {
               list[i].setColor(colors.get((int) (Math.random() * colors.size())));
            }
         }
      }

      for (int i = 0; i < cityList.length; i++) {
         for (int j = 0; j < cityList.length; j++) {
            if (cityList[i].getColor().equals(cityList[j].getColor())) {
               
            }
         }
      }

      return count;
   }

   public boolean isDirected() {
      for (int i = 0; i < map.length; i++) {
         for (int j = 0; j < map[0].length; j++) {
            if (map[i][j] != 0.0 && map[j][i] != 0.0) {
               return false;
            }
         }
      }
      return true;
   }

   public void printMyGraph() {
      System.out.print("\t");
      for (int i = 0; i < cityList.length; i++) {
         System.out.print(cityList[i].getName() + "\t");
      }
      System.out.println("");

      for (int i = 0; i < map.length; i++) {
         System.out.print(cityList[i].getName() + "\t");
         for (int j = 0; j < map[0].length; j++) {
            System.out.print(map[i][j] + "\t");
         }
         System.out.println("");
      }
   }
}
