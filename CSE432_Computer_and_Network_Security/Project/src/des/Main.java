package des;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    // i will keep plain text-s and key-s bits in an array
    // i choose to use 2D array
    // each row will be  a charachter
    // each column of a row will be bit number of that charachter
    private static int[][] plainText;
    private static int[][] key;

    public static void main(String[] args) throws UnsupportedEncodingException {
        // a simple user interaction to get the plaint text and key
        Scanner input = new Scanner(System.in);

        String plainTextString;
        String keyString;

        // codes below this line is for simple menu control
        System.out.println("Welcome to DES Encryption !");
        System.out.println("Do not FORGET !");
        System.out.println("Plain Texts and key should be 8 character long, each cost 8 bit.");
        System.out.println("(There should not be Turkish letters, they cost more). ");

        while (true) {
            System.out.println("");
            System.out.println("");
            System.out.println("Please, enter your plain text which will be encrypted.");
            plainTextString = input.nextLine();
            System.out.println("");

            System.out.println("Please, enter your key.");
            keyString = input.nextLine();
            System.out.println("");

            // if there are Turkish letters in key or in plain text 
            // i should convert them to their English equivalent letter
            plainTextString = convertTurkishLetters(plainTextString);

            // if plain text and key is 8 character long, i can encrypt it
            // otherwise user will be warned to enter 8 character long input
            if (plainTextString.length() == 8 || keyString.length() == 8) {
                break;
            } else {
                System.out.println("There is something wrong with plain text and key.");
                System.out.println("Press 1 to try again.");
                System.out.println("Press 2 to quit.");

                int choice = input.nextInt();
                if (choice != 1) {
                    return;
                }
            }
        }

        System.out.print("Result is: ");

        // when conditions are satisfied bits of plain text and key will be generated
        // then i will send it to the DES-s encrypt method to encrypt it
        createPlainTextArray(plainTextString);
        createKeyArray(keyString);
        int[][] cipherText = DES.encrypt(key, plainText);

//        String s = convertToString(cipherText);

        for (int i = 0; i < cipherText.length; i++) {
            for (int j = 0; j < cipherText[0].length; j++) {
                System.out.print(cipherText[i][j]);
            }
        }
        System.out.println("");

//        System.out.println(s);
    }

    private static String convertToString(int[][] cipherText) {
        String s = "";

        for (int i = 0; i < cipherText.length; i++) {
            for (int j = 0; j < cipherText[i].length; j++) {
                int x = (int) (cipherText[i][j] * Math.pow(2, 8 - j));
                s += (char) x;
            }
        }

        return s;
    }

    // this method converts characters of plain text to its bits
    private static void createPlainTextArray(String plainTextString) throws UnsupportedEncodingException {
        // firstly, i get the bytes of the given plain text
        byte[] byteArray = plainTextString.getBytes("UTF-8");
        plainText = new int[8][8];

        // i need to get bits of each bytes
        for (int i = 0; i < 8; i++) {
            String s = Integer.toBinaryString(byteArray[i]);

            // for some letters which has less than 8 bits
            // i need to expand them to be 8 bit
            // for instance; 010110 will be 00010110
            while (s.length() < 8) {
                s = "0" + s;
            }

            // then i will add bits to the array
            for (int j = 0; j < 8; j++) {
                plainText[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
    }

    // this method converts characters of key to its bits
    private static void createKeyArray(String keyString) throws UnsupportedEncodingException {
        // firstly, i get the bytes of the given key
        byte[] byteArray = keyString.getBytes("UTF-8");
        key = new int[8][8];

        // i need to get bits of each bytes
        for (int i = 0; i < 8; i++) {
            String s = Integer.toBinaryString(byteArray[i]);

            // for some letters which has less than 8 bits
            // i need to expand them to be 8 bit
            // for instance; 010110 will be 00010110
            while (s.length() < 8) {
                s = "0" + s;
            }

            // then i will add bits to the array
            for (int j = 0; j < 8; j++) {
                key[i][j] = Integer.parseInt(s.charAt(j) + "");
            }
        }
    }

    // converting Turkish letters to their English equivalent letters
    private static String convertTurkishLetters(String s) {
        s = s.replaceAll("ı", "i");
        s = s.replaceAll("ğ", "g");
        s = s.replaceAll("ü", "u");
        s = s.replaceAll("ş", "s");
        s = s.replaceAll("ö", "o");
        s = s.replaceAll("ç", "c");
        s = s.replaceAll("Ğ", "G");
        s = s.replaceAll("Ü", "U");
        s = s.replaceAll("İ", "I");
        s = s.replaceAll("Ş", "S");
        s = s.replaceAll("Ö", "O");
        s = s.replaceAll("Ç", "C");
        return s;
    }

}
