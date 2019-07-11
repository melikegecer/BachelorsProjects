import java.util.Scanner;

public class MelikeGecer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int pcStartDice = (int) (Math.random() * 6 + 1);
        int playerStartDice = (int) (Math.random() * 6 + 1);
        
        int pcScore = 0;
        int playerScore = 0;
        
        String userName = "";
        userName = input.nextLine();
        
        if(playerStartDice > pcStartDice) {
            System.out.println(userName + " starts.");
            do {
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println(userName + " s score: " + playerScore);
                System.out.println("computers score: " + pcScore);
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                
                playerScore += playersTurn(playerScore, userName);
                pcScore += pcsTurn(pcScore);
                
                if((playerScore > 100) || (pcScore > 100)) {
                    break;
                }
            } while(true);
            
            theWinner(playerScore, pcScore, userName);
        } else if (pcStartDice > playerStartDice) {
            System.out.println("Computer starts.");
            do {
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println(userName + " s score: " + playerScore);
                System.out.println("computers score: " + pcScore);
                System.out.println("*-*-*-*-*-*-*-*-*-*-*-*");
                
                pcScore += pcsTurn(pcScore);
                playerScore += playersTurn(playerScore, userName);
                
                if((playerScore > 100) || (pcScore > 100)) {
                    break;
                }
            } while(true);
            
            theWinner(playerScore, pcScore, userName);
        } else {
            System.out.println("Start dices are equal.");
        }
    }
    
    public static int playersTurn(int playerScore, String userName) {
        Scanner input = new Scanner(System.in);
        
        String name = userName;
        
        String sYesNo = "";
        char yesNo;
        int pScore = 0; 
        
        int dice = 0;
        
        do {
            dice = (int) (Math.random() * 6 + 1);
            pScore += dice;
            
            if(dice == 1) {
                pScore = 0;
                System.out.println(name + " rolled 1 and " + name + " lost the turn.");
                break;
            }
            
            System.out.println(name + " rolled " + dice);
            
            System.out.println("Would you like to play again? Y/N ");
            sYesNo = input.next();
            yesNo = sYesNo.charAt(0);
            
            if(yesNo == 'N' || yesNo == 'n') {
                break;
            }
        } while(yesNo == 'Y' || yesNo == 'y');
        return pScore;
    }
    
    public static int pcsTurn(int pcScore) {
        int pScore = 0;
        
        int rollDice = pcScore / 10;
        rollDice = 10 - rollDice;
        
        int dice = 0;
        
        for(int i=0; i<rollDice; i++) {
            dice = (int) (Math.random() * 6 + 1);
            pScore += dice;
            
            if(dice == 1) {
                pScore = 0;
                System.out.println("Computer rolled 1 and computer lost its turn.");
                break;
            }
            
            System.out.println("Computer rolled " + dice);
        }
        return pScore;
    }
    
    public static void theWinner(int playerScore, int pcScore, String userName) {
        String name = userName;
        if(pcScore >= 100) {
            System.out.println("PC wins.  the score is: " + pcScore);
        } else if(playerScore >= 100) {
            System.out.println(name + " wins. the score is: " + playerScore);
        } else {
            System.out.println("No such possibilty");
        }
    }
}