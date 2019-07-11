package fourthdataproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMyGraphLib {

   private static MyGraphLib myGraph;
   private static Scanner input = new Scanner(System.in);

   public static void main(String[] args) {

      String fileName = "";
      System.out.println("Enter first file name: ");
      fileName = input.next();
      myGraph = readGraphFile(fileName);
      menu();
   }

   private static void menu() {
      System.out.println("What do you want to do?");
      System.out.println("1 print DFS(Depth First Traversal)");
      System.out.println("2 print BFS(Breadth First Traversal)");
      System.out.println("3 Dijkstra");
      System.out.println("4 Bellman Ford");
      System.out.println("5 Minimum Spanning Tree");
      System.out.println("6 Has Hamiltonian Path ?");
      System.out.println("7 Has Eular Path ?");
      System.out.println("8 Number of Connected Components");
      System.out.println("9 Has bridge ?");
      System.out.println("10 Isomorphic ?");
      System.out.println("11 Paint the graph");
      System.out.println("0 Exit");

      int choice = input.nextInt();
      String s = "";
      City c;

      switch (choice) {
         case 1:
         case 2:
         case 3:
         case 4:
            System.out.println("Enter the inital's name: ");
            s = input.next();
            c = myGraph.findCity(s);
            if (c != null) {
               //control if exist, otherwise print message
               if (choice == 1) {
                  myGraph.printDFS(c);
               } else if (choice == 2) {
                  myGraph.printBFS(c);
               } else if (choice == 3) {
                  myGraph.dijkstra(c);
               } else if (choice == 4) {
                  myGraph.bellmanFord(c);
               }
            } else {
               System.out.println("I dont have this city");
            }
            break;
         case 5:
            MyGraphLib res = myGraph.findMinimumSpanningTree();
            break;
         case 6:
            System.out.println("Do i have Hamilton Path " + myGraph.hasHamiltonPath());
            break;
         case 7:
            System.out.println("Do i have Eular Path " + myGraph.hasEulerPath());
            break;
         case 8:
            int n = myGraph.findConnectedComponents();
            System.out.println("I have " + n + " number of components.");
            break;
         case 9:
            System.out.println("Do i have Bridge? ");
            System.out.println("Complexity is O(N^2).");
            System.out.println(myGraph.hasBridge());
            break;
         case 10:
            System.out.println("Enter the second graph's name to check if they are isomorphic: ");
            System.out.println("Complexity is O(N).");

            System.out.println("Second graph");
            String g = input.next();
            MyGraphLib graph2 = readGraphFile(g);
            System.out.println("graph2" + g);
            System.out.println(myGraph.isIsomorphic(graph2));
            break;
         case 11:
            System.out.println("Complexity is O(N^3)).");
            System.out.println(myGraph.paint());
            break;
         case 0:
            System.exit(0);
      }
   }

   private static MyGraphLib readGraphFile(String fileName) {
      File f = new File(fileName);
      MyGraphLib mg = null;
      try {
         Scanner s = new Scanner(f);
         int n = s.nextInt();
         mg = new MyGraphLib(n);

         String x = s.next();
         for (int i = 0; i < n; i++) {
            String vNames = s.next();
            mg.addVertex(new City(vNames));
         }

         String e = s.next();
         while (s.hasNext()) {
            String from = s.next();
            String to = s.next();
            String weight = s.next();
            double dweight = convertToDouble(weight);
            mg.addEdge(mg.findCity(from), mg.findCity(to), dweight);
         }
         s.close();
      } catch (FileNotFoundException e) {
         System.out.println("I do not have this file.");
      }
      return mg;
   }

   private static double convertToDouble(String s) {
      String r = "";
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) == ',') {
            r += ".";
         } else {
            r += s.charAt(i);
         }
      }
      return Double.valueOf(r);
   }
}
