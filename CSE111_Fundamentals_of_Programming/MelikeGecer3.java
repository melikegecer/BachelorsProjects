import java.util.Scanner;

public class MelikeGecer3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        outputln("Welcome to the Word Game.");
        outputln("What is your name?");
        String n = input.nextLine();
        String name = n.toUpperCase();
        
        int maxScore = 0;
        int hintedScore = 0;
        int finalScore = 0;
        int totalScore = 0;
        
        for(int i=0; i<5; i++) {
            showMenu(name);
            int categoryNo = input.nextInt();
            String[] currentDictionary = getDictionary(categoryNo);
            String choosedWord = chooseWord(currentDictionary);
            String shuffledWord = shuffleWord(choosedWord);
            
            maxScore = maxScore(shuffledWord, choosedWord);
            
            outputln("Question: " + shuffledWord + " Question Score: " + (maxScore*100) + " Your answer: ");
            
            hintedScore = hintedScore(choosedWord, maxScore);
            
            finalScore = maxScore - hintedScore;
            
            totalScore += finalScore;
            
            
            if(finalScore == 0) {
                outputln("Sorry, you got all the hints");
            } else {
                outputln("CORRECT!! Your total score is " + (totalScore*100));
            }
        }
        
        totalScore *= 100;
        outputln("Congratulations your score is: " + totalScore + "");
        
        showScores(name, totalScore);
    }
    
    public static void showMenu(String name) {
        outputln(name + " please choose a category:");
        outputln("1. Animals");
        outputln("2. Colors");
        outputln("3. Cities");
        outputln("4. Quit");
    }
    
    public static void showScores(String name, int totalScore) {
        outputln("Score Table:");
        String[] names = {"LEYLA", "MECNUN", "ERDAL" };
        int[] scores = {5000, 2500, 1250};
        
        String maxName = "";
        int max = 0;
        
        for(int i=scores.length-1; i>=0; i++) {
            if(scores[i] <= totalScore) {
                scores[i] = totalScore;
                names[i] = name;
                break;
            }
        }
        
        for(int i=0; i<scores.length; i++) {
            outputln(names[i] + " " + scores[i]);
        }
    }
    
    public static void outputln(String str) {
        System.out.println(str);
    }
    
    public static void output(String str) {
        System.out.print(str);
    }
    
    public static String[] getDictionary(int categoryNo) {
        String[] animals = {"ELEPHANT", "ROOSTER", "PANDA", "MOUSE", "HORSE", "SNAKE", "BUTTERFLY", "ZEBRA", "MONKEY", "DONKEY"};
        String[] colours = {"RED", "MAGENTA", "YELLOW", "GRAY", "GREEN", "MAROON", "BLACK", "BROWN", "BLUE", "PURPLE"};
        String[] cities = {"ANKARA", "BURSA", "ANTALYA", "KONYA", "SAMSUN", "YOZGAT", "ERZURUM", "ADANA", "BOLU", "ORDU"};
        
        if(categoryNo == 1) {
            return animals;
        } else if(categoryNo == 2) {
            return colours;
        } else if(categoryNo == 3) {
            return cities;
        } else if(categoryNo == 4) {
            System.exit(0);
            outputln("Bye Bye! :)");
        } else {
            outputln("There is no category like " + categoryNo + ".");
            System.exit(0);
        }
        return null;
    }
    
    public static String chooseWord(String[] currentDictionary) {
        int indexOfWord = (int) (Math.random() * currentDictionary.length);
        
        String choosedWord = currentDictionary[indexOfWord];
        return choosedWord;
    }
    
    public static String shuffleWord(String choosedWord) {
        String shuffledWord = "";
        
        while(choosedWord.length() != 0) {
            int indexOfChar = (int) (Math.random() * choosedWord.length());
            
            shuffledWord += choosedWord.charAt(indexOfChar);
            choosedWord = eraseChar(choosedWord, indexOfChar);
        }
        
        return shuffledWord;
    }
    
    public static String eraseChar(String choosedWord, int indexOfChar) {
        String erased = "";
        
        for(int i=0; i<choosedWord.length(); i++) {
            if(i<indexOfChar || i>indexOfChar) {
                erased += choosedWord.charAt(i);
            }
        }
        
        return erased;
    }
    
    public static int maxScore(String shuffledWord, String choosedWord) {
        int maxScore = 0;
        for(int i=0; i<choosedWord.length(); i++) {
            if(choosedWord.charAt(i) != shuffledWord.charAt(i)) {
                maxScore++;
            }
        }
        return maxScore;
    }
    
    public static int hintedScore(String choosedWord, int maxScore) {
        Scanner input = new Scanner(System.in);
        String written = input.nextLine();
        String writtenWord = written.toUpperCase();
        
        int counter = 0;
        
        while( !choosedWord.equals(writtenWord) ) {
            counter++;
            writtenWord = giveMeHint(choosedWord, writtenWord);
            outputln(" Question Score: " + ((maxScore-counter)*100) + " Your answer: ");
            
            if(choosedWord.equals(writtenWord)) {
                break;
            } else {
                String written1 = input.nextLine();
                writtenWord = written1.toUpperCase();
            }
        }
        
        return counter;
    }
    
    public static String giveMeHint(String choosedWord, String writtenWord1) {
        char change1 = '\0';
        char change2 = '\0';
        
        String writtenWord = writtenWord1.toUpperCase();
        
        int index1 = 0;
        int index2 = 0;
        
        String hintGived = "";
        
        for(int i=0; i<choosedWord.length(); i++) {
            if(choosedWord.charAt(i) != writtenWord.charAt(i)) {
                change1 = choosedWord.charAt(i);
                change2 = writtenWord.charAt(i);
                index1 = whereIsTheChange1(writtenWord, change1);
                index2 = whereIsTheChange2(writtenWord, change2);
                break;
            }
        }
        
        for(int i=0; i<writtenWord.length(); i++) {
            if(i == index1) {
                hintGived += change2;
            } else if(i == index2) {
                hintGived += change1;
            } else {
                hintGived += writtenWord.charAt(i);
            }
        }
        
        writtenWord = hintGived;
        output("Question: " + hintGived);
        
        return writtenWord;
    }
    
    public static int whereIsTheChange1(String writtenWord1, char change1) {
        int index = 0;
        
        String writtenWord = writtenWord1.toUpperCase();
        
        for(int i=0; i<writtenWord.length(); i++) {
            if(writtenWord.charAt(i) == change1) {
                index = i;
            }
        }
        
        return index;
    }
    
    public static int whereIsTheChange2(String writtenWord1, char change2) {
        int index = 0;
        
        String writtenWord = writtenWord1.toUpperCase();
        
        for(int i=0; i<writtenWord.length(); i++) {
            if(writtenWord.charAt(i) == change2) {
                index = i;
            }
        }
        
        return index;
    }
}