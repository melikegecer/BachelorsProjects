package des;

import java.util.Arrays;

public class DES {

    private static int[][] key;
    private static int[][] plainText;
    private static int[][] keyList;

    static int[][] encrypt(int[][] inputKey, int[][] inputText) {
        key = inputKey;
        plainText = inputText;

        // cipher text will be 64 bits
        int[][] cipherText = new int[8][8];

        // firstly, i should generate keys
        keyList = KeySchedule.generateKeys(key);

        // then, i should apply the IP1 on the plain text
        plainText = IP1(plainText);

        // i need left and right 32 bits of the plain text
        int[] leftText = getLeftText(plainText);
        int[] rightText = getRightText(plainText);

        // in this loop i run my algorithm 16 times
        for (int i = 0; i < 16; i++) {
            // i need to keep right text s value before change
            int[] rightTextTemp = Arrays.copyOf(rightText, rightText.length);

            // i get fiestel function by using right text and key from key list
            int[] fiestel = FiestelFunction.getFiestelResult(rightText, keyList[i]);

            // i xor left previous key with fiestel and it becomes new right text
            rightText = xoring(leftText, fiestel);

            leftText = rightTextTemp;
        }

        // i should unite left text and right text as cipher text
        cipherText = uniteTexts(leftText, rightText);

        // lastly, i should apply Inverse Initial Permutation
        cipherText = IIP(cipherText);

        return cipherText;
    }

    // this method performs division of plain text
    private static int[] getLeftText(int[][] text) {
        int[] leftText = new int[32];

        int k = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < text[0].length; j++) {
                leftText[k] = text[i][j];
                k++;
            }
        }

        return leftText;
    }

    // this method performs division of plain text
    private static int[] getRightText(int[][] text) {
        int[] rightText = new int[32];

        int k = 0;

        for (int i = 4; i < text.length; i++) {
            for (int j = 0; j < text[0].length; j++) {
                rightText[k] = text[i][j];
                k++;
            }
        }

        return rightText;
    }

    // this method performs Initial Permutation 1 operations
    private static int[][] IP1(int[][] plainText) {
        int[][] result = new int[8][8];

        result[0][0] = plainText[7][1]; // 58. bit of plain text
        result[0][1] = plainText[6][1]; // 50. bit of plain text
        result[0][2] = plainText[5][1]; // 42. bit of plain text
        result[0][3] = plainText[4][1]; // 34. bit of plain text
        result[0][4] = plainText[3][1]; // 26. bit of plain text
        result[0][5] = plainText[2][1]; // 18. bit of plain text
        result[0][6] = plainText[1][1]; // 10. bit of plain text
        result[0][7] = plainText[0][1]; //  2. bit of plain text

        result[1][0] = plainText[7][3]; // 60. bit of plain text
        result[1][1] = plainText[6][3]; // 52. bit of plain text
        result[1][2] = plainText[5][3]; // 44. bit of plain text
        result[1][3] = plainText[4][3]; // 36. bit of plain text
        result[1][4] = plainText[3][3]; // 28. bit of plain text
        result[1][5] = plainText[2][3]; // 20. bit of plain text
        result[1][6] = plainText[1][3]; // 12. bit of plain text
        result[1][7] = plainText[0][3]; //  4. bit of plain text

        result[2][0] = plainText[7][5]; // 62. bit of plain text
        result[2][1] = plainText[6][5]; // 54. bit of plain text
        result[2][2] = plainText[5][5]; // 46. bit of plain text
        result[2][3] = plainText[4][5]; // 38. bit of plain text
        result[2][4] = plainText[3][5]; // 30. bit of plain text
        result[2][5] = plainText[2][5]; // 22. bit of plain text
        result[2][6] = plainText[1][5]; // 14. bit of plain text
        result[2][7] = plainText[0][5]; //  6. bit of plain text

        result[3][0] = plainText[7][7]; // 64. bit of plain text
        result[3][1] = plainText[6][7]; // 56. bit of plain text
        result[3][2] = plainText[5][7]; // 48. bit of plain text
        result[3][3] = plainText[4][7]; // 40. bit of plain text
        result[3][4] = plainText[3][7]; // 32. bit of plain text
        result[3][5] = plainText[2][7]; // 24. bit of plain text
        result[3][6] = plainText[1][7]; // 16. bit of plain text
        result[3][7] = plainText[0][7]; //  8. bit of plain text

        result[4][0] = plainText[7][0]; // 57. bit of plain text
        result[4][1] = plainText[6][0]; // 49. bit of plain text
        result[4][2] = plainText[5][0]; // 41. bit of plain text
        result[4][3] = plainText[4][0]; // 33. bit of plain text
        result[4][4] = plainText[3][0]; // 25. bit of plain text
        result[4][5] = plainText[2][0]; // 17. bit of plain text
        result[4][6] = plainText[1][0]; //  9. bit of plain text
        result[4][7] = plainText[0][0]; //  1. bit of plain text

        result[5][0] = plainText[7][2]; // 59. bit of plain text
        result[5][1] = plainText[6][2]; // 51. bit of plain text
        result[5][2] = plainText[5][2]; // 43. bit of plain text
        result[5][3] = plainText[4][2]; // 35. bit of plain text
        result[5][4] = plainText[3][2]; // 27. bit of plain text
        result[5][5] = plainText[2][2]; // 19. bit of plain text
        result[5][6] = plainText[1][2]; // 11. bit of plain text
        result[5][7] = plainText[0][2]; //  3. bit of plain text

        result[6][0] = plainText[7][4]; // 61. bit of plain text
        result[6][1] = plainText[6][4]; // 53. bit of plain text
        result[6][2] = plainText[5][4]; // 45. bit of plain text
        result[6][3] = plainText[4][4]; // 37. bit of plain text
        result[6][4] = plainText[3][4]; // 29. bit of plain text
        result[6][5] = plainText[2][4]; // 21. bit of plain text
        result[6][6] = plainText[1][4]; // 13. bit of plain text
        result[6][7] = plainText[0][4]; //  5. bit of plain text

        result[7][0] = plainText[7][6]; // 63. bit of plain text
        result[7][1] = plainText[6][6]; // 55. bit of plain text
        result[7][2] = plainText[5][6]; // 47. bit of plain text
        result[7][3] = plainText[4][6]; // 39. bit of plain text
        result[7][4] = plainText[3][6]; // 31. bit of plain text
        result[7][5] = plainText[2][6]; // 23. bit of plain text
        result[7][6] = plainText[1][6]; // 15. bit of plain text
        result[7][7] = plainText[0][6]; //  7. bit of plain text

        return result;
    }

    private static int[] xoring(int[] leftTextPrev, int[] fiestel) {
        int[] xored = new int[fiestel.length];

        for (int i = 0; i < xored.length; i++) {
            xored[i] = leftTextPrev[i] ^ fiestel[i];
        }

        return xored;
    }

    // this method unites left text and right text
    private static int[][] uniteTexts(int[] leftText, int[] rightText) {
        int[][] united = new int[8][8];

        united[4] = Arrays.copyOfRange(leftText, 0, 8);
        united[5] = Arrays.copyOfRange(leftText, 8, 16);
        united[6] = Arrays.copyOfRange(leftText, 16, 24);
        united[7] = Arrays.copyOfRange(leftText, 24, 32);
        united[0] = Arrays.copyOfRange(rightText, 0, 8);
        united[1] = Arrays.copyOfRange(rightText, 8, 16);
        united[2] = Arrays.copyOfRange(rightText, 16, 24);
        united[3] = Arrays.copyOfRange(rightText, 24, 32);
        
        return united;
    }

    // this method applies Inverse Initial Permutation
    private static int[][] IIP(int[][] cipherText) {
        int[][] result = new int[8][8];

        result[0][0] = cipherText[4][7];  // 40 of cipher text
        result[0][1] = cipherText[0][7];  //  8 of cipher text
        result[0][2] = cipherText[5][7];  // 48 of cipher text
        result[0][3] = cipherText[1][7];  // 16 of cipher text
        result[0][4] = cipherText[6][7];  // 56 of cipher text
        result[0][5] = cipherText[2][7];  // 24 of cipher text
        result[0][6] = cipherText[7][7];  // 64 of cipher text
        result[0][7] = cipherText[3][7];  // 32 of cipher text

        result[1][0] = cipherText[4][6];  // 39 of cipher text
        result[1][1] = cipherText[0][6];  //  7 of cipher text
        result[1][2] = cipherText[5][6];  // 47 of cipher text
        result[1][3] = cipherText[1][6];  // 15 of cipher text
        result[1][4] = cipherText[6][6];  // 55 of cipher text
        result[1][5] = cipherText[2][6];  // 23 of cipher text
        result[1][6] = cipherText[7][6];  // 63 of cipher text
        result[1][7] = cipherText[3][6];  // 31 of cipher text

        result[2][0] = cipherText[4][5];  // 38 of cipher text
        result[2][1] = cipherText[0][5];  //  6 of cipher text
        result[2][2] = cipherText[5][5];  // 46 of cipher text
        result[2][3] = cipherText[1][5];  // 14 of cipher text
        result[2][4] = cipherText[6][5];  // 54 of cipher text
        result[2][5] = cipherText[2][5];  // 22 of cipher text
        result[2][6] = cipherText[7][5];  // 62 of cipher text
        result[2][7] = cipherText[3][5];  // 30 of cipher text

        result[3][0] = cipherText[4][4];  // 37 of cipher text
        result[3][1] = cipherText[0][4];  //  5 of cipher text
        result[3][2] = cipherText[5][4];  // 45 of cipher text
        result[3][3] = cipherText[1][4];  // 13 of cipher text
        result[3][4] = cipherText[6][4];  // 53 of cipher text
        result[3][5] = cipherText[2][4];  // 21 of cipher text
        result[3][6] = cipherText[7][4];  // 61 of cipher text
        result[3][7] = cipherText[3][4];  // 29 of cipher text

        result[4][0] = cipherText[4][3];  // 36 of cipher text
        result[4][1] = cipherText[0][3];  //  4 of cipher text
        result[4][2] = cipherText[5][3];  // 44 of cipher text
        result[4][3] = cipherText[1][3];  // 12 of cipher text
        result[4][4] = cipherText[6][3];  // 52 of cipher text
        result[4][5] = cipherText[2][3];  // 20 of cipher text
        result[4][6] = cipherText[7][3];  // 60 of cipher text
        result[4][7] = cipherText[3][3];  // 28 of cipher text

        result[5][0] = cipherText[4][2];  // 35 of cipher text
        result[5][1] = cipherText[0][2];  //  3 of cipher text
        result[5][2] = cipherText[5][2];  // 43 of cipher text
        result[5][3] = cipherText[1][2];  // 11 of cipher text
        result[5][4] = cipherText[6][2];  // 51 of cipher text
        result[5][5] = cipherText[2][2];  // 19 of cipher text
        result[5][6] = cipherText[7][2];  // 59 of cipher text
        result[5][7] = cipherText[3][2];  // 27 of cipher text

        result[6][0] = cipherText[4][1];  // 34 of cipher text
        result[6][1] = cipherText[0][1];  //  2 of cipher text
        result[6][2] = cipherText[5][1];  // 42 of cipher text
        result[6][3] = cipherText[1][1];  // 10 of cipher text
        result[6][4] = cipherText[6][1];  // 50 of cipher text
        result[6][5] = cipherText[2][1];  // 18 of cipher text
        result[6][6] = cipherText[7][1];  // 58 of cipher text
        result[6][7] = cipherText[3][1];  // 26 of cipher text

        result[7][0] = cipherText[4][0];  // 33 of cipher text
        result[7][1] = cipherText[0][0];  //  1 of cipher text
        result[7][2] = cipherText[5][0];  // 41 of cipher text
        result[7][3] = cipherText[1][0];  //  9 of cipher text
        result[7][4] = cipherText[6][0];  // 49 of cipher text
        result[7][5] = cipherText[2][0];  // 17 of cipher text
        result[7][6] = cipherText[7][0];  // 57 of cipher text
        result[7][7] = cipherText[3][0];  // 25 of cipher text

        return result;
    }
}
