package thirddataproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ThirdDataProject {

   private static Scanner input = new Scanner(System.in);
   private static ArrayList<String> aList = new ArrayList<>();
   private static MyHash hash = new MyHash();

   public static void main(String[] args) {
      System.out.println("Note: Before running please be careful with the Turkish letters, Scanner Class has some problem with them ;)");
      System.out.println("Note: Do not forget to load the dictionary before doing any operation :)");
      mainMenu();
   }

   private static void mainMenu() {

      System.out.println("-------------------------------");
      System.out.println("What would you like to do?");
      System.out.println("1 to Load the dictionary");
      System.out.println("2 to Search a word");
      System.out.println("3 to Insert a word");
      System.out.println("4 to Delete a word");
      System.out.println("5 to Check the entry");
      System.out.println("6 to Suggestions");
      System.out.println("0 to Quit");

      int choice = input.nextInt();

      System.out.println("-------------------------------");

      switch (choice) {
         case 1:
            loadDictionary();
            break;
         case 2:
            searchWord();
            break;
         case 3:
            insertWord();
            break;
         case 4:
            deleteWord();
            break;
         case 5:
            checkEntry();
            break;
         case 6:
            suggestMe();
            break;
         case 0:
            System.out.println("Bye bye :)");
            System.exit(0);
         default:
      }
      mainMenu();
   }

   private static void loadDictionary() {
      File f = new File("dictionaryForProject3.txt");
      try {
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String x = s.nextLine();
            aList.add(x);
            hash.insert(new Element(Cleaner.cleanString(x)));
         }
         Collections.sort(aList);
      } catch (FileNotFoundException ex) {
         System.out.println("I think I do not have this file, do I?");
      }
      System.out.println("Loading is done.");
   }

   private static void searchWord() {
      System.out.println("Enter something to search:");
      String word = input.next();
      String s = Cleaner.cleanString(word);
      System.out.println("Searching for ... " + s);
      Element e = new Element(s);
      System.out.println("DID I FOUND IT " + hash.search(e));
   }

   private static void insertWord() {
      System.out.println("Enter something to insert:");
      String word = input.next();
      System.out.println("Inserting...");
      Element e = new Element(word);
      hash.insert(e);
   }

   private static void deleteWord() {
      System.out.println("Enter something to delete:");
      String word = input.next();
      System.out.println("Deleting...");
      Element e = new Element(word);
      hash.delete(e);
   }

   private static void checkEntry() {
      System.out.println("big-test.txt is going to be checked:");

      System.out.println("Checking...");

      File f = new File("big-test.txt");
      System.out.println("The words which are written wrong: ");
      try {
         Scanner s = new Scanner(f);
         while (s.hasNextLine()) {
            String x = Cleaner.cleanString(s.nextLine());
            Element e = new Element(x);
            System.out.println(x);
         }
      } catch (FileNotFoundException ex) {
         System.out.println("I think I do not have this file, do I?");
      }
      System.out.println("Checking is done.");
   }

   private static void suggestMe() {
      System.out.println("Enter a word to get suggestions: ");
      String word = input.next();
      if (hash.search(new Element(word))) {
         System.out.println("It is true.");
      } else {
         System.out.println("That is not true but, here is some options...");
         ArrayList<String> aList = Suggester.suggestMe(hash, word);
         System.out.println(aList.toString());
      }
   }
}
