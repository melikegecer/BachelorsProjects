package des;

public class KeySchedule {

    // this method called to get subkeys which will be used in DES
    static int[][] generateKeys(int[][] key64Bit) {
        // at the end i will have 16 keys, each one is 48bit
        int[][] keyList = new int[16][48];

        // in each turn shifting is done different times
        // therefore i am keeping each turns shifting number in an array
        int[] shiftList = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

        // i need to generate 56 bit key from 64 bit key 
        int[][] key56Bit = PC1(key64Bit);

        int[] leftKey = getLeftKey(key56Bit);
        int[] rightKey = getRightKey(key56Bit);

        for (int i = 0; i < 16; i++) {
            leftKey = shiftKey(leftKey, shiftList[i]);
            rightKey = shiftKey(rightKey, shiftList[i]);
            keyList[i] = PC2(leftKey, rightKey);
        }

        return keyList;
    }

    // this method performs the Permutation Choice 1 operations
    private static int[][] PC1(int[][] key64Bit) {
        int[][] key56Bit = new int[8][7];

        key56Bit[0][0] = key64Bit[7][0]; // 57. bit of the input key
        key56Bit[0][1] = key64Bit[6][0]; // 49. bit of the input key
        key56Bit[0][2] = key64Bit[5][0]; // 41. bit of the input key
        key56Bit[0][3] = key64Bit[4][0]; // 33. bit of the input key
        key56Bit[0][4] = key64Bit[3][0]; // 25. bit of the input key
        key56Bit[0][5] = key64Bit[2][0]; // 17. bit of the input key
        key56Bit[0][6] = key64Bit[1][0]; //  9. bit of the input key

        key56Bit[1][0] = key64Bit[0][0]; //  1. bit of the input key
        key56Bit[1][1] = key64Bit[7][1]; // 58. bit of the input key
        key56Bit[1][2] = key64Bit[6][1]; // 50. bit of the input key
        key56Bit[1][3] = key64Bit[5][1]; // 42. bit of the input key
        key56Bit[1][4] = key64Bit[4][1]; // 34. bit of the input key
        key56Bit[1][5] = key64Bit[3][1]; // 26. bit of the input key
        key56Bit[1][6] = key64Bit[2][1]; // 18. bit of the input key

        key56Bit[2][0] = key64Bit[1][1]; // 10. bit of the input key
        key56Bit[2][1] = key64Bit[0][1]; //  2. bit of the input key
        key56Bit[2][2] = key64Bit[7][2]; // 59. bit of the input key
        key56Bit[2][3] = key64Bit[6][2]; // 51. bit of the input key
        key56Bit[2][4] = key64Bit[5][2]; // 43. bit of the input key
        key56Bit[2][5] = key64Bit[4][2]; // 35. bit of the input key
        key56Bit[2][6] = key64Bit[3][2]; // 27. bit of the input key

        key56Bit[3][0] = key64Bit[2][2]; // 19. bit of the input key
        key56Bit[3][1] = key64Bit[1][2]; // 11. bit of the input key
        key56Bit[3][2] = key64Bit[0][2]; //  3. bit of the input key
        key56Bit[3][3] = key64Bit[7][3]; // 60. bit of the input key
        key56Bit[3][4] = key64Bit[6][3]; // 52. bit of the input key
        key56Bit[3][5] = key64Bit[5][3]; // 44. bit of the input key
        key56Bit[3][6] = key64Bit[4][3]; // 36. bit of the input key

        key56Bit[4][0] = key64Bit[7][6]; // 63. bit of the input key
        key56Bit[4][1] = key64Bit[6][6]; // 55. bit of the input key
        key56Bit[4][2] = key64Bit[5][6]; // 47. bit of the input key
        key56Bit[4][3] = key64Bit[4][6]; // 39. bit of the input key
        key56Bit[4][4] = key64Bit[3][6]; // 31. bit of the input key
        key56Bit[4][5] = key64Bit[2][6]; // 23. bit of the input key
        key56Bit[4][6] = key64Bit[1][6]; // 15. bit of the input key

        key56Bit[5][0] = key64Bit[0][6]; //  7. bit of the input key
        key56Bit[5][1] = key64Bit[7][5]; // 62. bit of the input key
        key56Bit[5][2] = key64Bit[6][5]; // 54. bit of the input key
        key56Bit[5][3] = key64Bit[5][5]; // 46. bit of the input key
        key56Bit[5][4] = key64Bit[4][5]; // 38. bit of the input key
        key56Bit[5][5] = key64Bit[3][5]; // 30. bit of the input key
        key56Bit[5][6] = key64Bit[2][5]; // 22. bit of the input key

        key56Bit[6][0] = key64Bit[1][5]; // 14th bit of the input key
        key56Bit[6][1] = key64Bit[0][5]; //  6th bit of the input key
        key56Bit[6][2] = key64Bit[7][4]; // 61th bit of the input key
        key56Bit[6][3] = key64Bit[6][4]; // 53th bit of the input key
        key56Bit[6][4] = key64Bit[5][4]; // 45th bit of the input key
        key56Bit[6][5] = key64Bit[4][4]; // 37th bit of the input key
        key56Bit[6][6] = key64Bit[3][4]; // 29th bit of the input key

        key56Bit[7][0] = key64Bit[2][4]; // 21th bit of the input key
        key56Bit[7][1] = key64Bit[1][4]; // 13th bit of the input key
        key56Bit[7][2] = key64Bit[0][4]; //  5th bit of the input key
        key56Bit[7][3] = key64Bit[3][3]; // 28th bit of the input key
        key56Bit[7][4] = key64Bit[2][3]; // 20th bit of the input key
        key56Bit[7][5] = key64Bit[1][3]; // 12th bit of the input key
        key56Bit[7][6] = key64Bit[0][3]; //  4th bit of the input key

        return key56Bit;
    }

    // this method performs division of 56 bit key
    private static int[] getLeftKey(int[][] key56Bit) {
        int[] leftKey = new int[28];

        int rowCount = 0;
        int columnCount = 0;

        // first 4 row of the 56 bit key array will be taken as left key
        for (int i = 0; i < 28; i++) {
            leftKey[i] = key56Bit[rowCount][columnCount];
            columnCount++;

            if (columnCount >= 7) {
                columnCount = 0;
                rowCount++;
            }
        }

        return leftKey;
    }

    // this method performs division of 56 bit key
    private static int[] getRightKey(int[][] key56Bit) {
        int[] rightKey = new int[28];

        int rowCount = 4;
        int columnCount = 0;

        // last 4 row of the 56 bit key array will be taken as right key
        for (int i = 0; i < 28; i++) {
            rightKey[i] = key56Bit[rowCount][columnCount];
            columnCount++;

            if (columnCount >= 7) {
                columnCount = 0;
                rowCount++;
            }
        }

        return rightKey;
    }

    // this method performs shifting of a given key
    private static int[] shiftKey(int[] key, int shiftNumber) {
        // i need temporary array to keep values of the array-s first and-or second elements
        // the size of the array will be our shift number
        int[] tmp = new int[shiftNumber];

        // i saved the values to my temporary array
        for (int i = 0; i < shiftNumber; i++) {
            tmp[i] = key[i];
        }

        // i shift the values in the array
        for (int i = 0; i < key.length - shiftNumber; i++) {
            key[i] = key[i + shiftNumber];
        }

        // i put the value-s in the temporary array to their places
        if (shiftNumber == 1) {
            key[key.length - 1] = tmp[0];
        } else if (shiftNumber == 2) {
            key[key.length - 2] = tmp[0];
            key[key.length - 1] = tmp[1];
        }

        return key;
    }

    // this method performs the Permutation Choice 2 operations
    private static int[] PC2(int[] leftKey, int[] rightKey) {
        int[] subkey = new int[48];

        // when applying PC2 operation there should be an array 
        // which is composed of left and right key
        // instead of joining left key array and right key
        // i prefered to choosing an element from right key or left key
        // if it is bigger than 28, i choose from the right key
        // if it is less than 28, i choose from the left key
        // for instance; if i have to choose 34, i choose right key-s 5th element 
        subkey[0] = leftKey[13]; // 14. of composed array (13. of left key)
        subkey[1] = leftKey[16]; //17. of composed array (16. of left key)
        subkey[2] = leftKey[10]; //11. of composed array (10. of left key)
        subkey[3] = leftKey[23]; //24. of composed array (23. of left key)
        subkey[4] = leftKey[0]; //1. of composed array (0. of left key)
        subkey[5] = leftKey[4]; // 5. of composed array (4. of left key)
        subkey[6] = leftKey[2]; // 3. of composed array (2. of left key)
        subkey[7] = leftKey[27]; // 28. of composed array (27. of left key)
        subkey[8] = leftKey[14]; // 15. of composed array (14. of left key)
        subkey[9] = leftKey[5]; // 6. of composed array (5. of left key)
        subkey[10] = leftKey[20]; // 21. of composed array (20. of left key)
        subkey[11] = leftKey[9]; // 10. of composed array (9. of left key)
        subkey[12] = leftKey[22]; // 23. of composed array (22. of left key)
        subkey[13] = leftKey[18]; // 19. of composed array (18. of left key)
        subkey[14] = leftKey[11]; // 12. of composed array (11. of left key)
        subkey[15] = leftKey[3]; // 4. of composed array (3. of left key)
        subkey[16] = leftKey[25]; // 26. of composed array (25. of left key)
        subkey[17] = leftKey[7]; // 8. of composed array (7. of left key)
        subkey[18] = leftKey[15]; // 16. of composed array (15. of left key)
        subkey[19] = leftKey[6]; // 7. of composed array (6. of left key)
        subkey[20] = leftKey[26]; // 27. of composed array (26. of left key)
        subkey[21] = leftKey[19]; // 20. of composed array (19. of left key)
        subkey[22] = leftKey[12]; // 13. of composed array (12. of left key)
        subkey[23] = leftKey[1]; // 2. of composed array (1. of left key)
        subkey[24] = rightKey[12]; // 41. of composed array (12. of right key)
        subkey[25] = rightKey[23]; // 52. of composed array (23. of right key)
        subkey[26] = rightKey[2]; // 31. of composed array (2. of right key)
        subkey[27] = rightKey[8]; // 37. of composed array (8. of right key)
        subkey[28] = rightKey[18]; // 47. of composed array (18. of right key)
        subkey[29] = rightKey[26]; // 55. of composed array (26. of right key)
        subkey[30] = rightKey[1]; // 30. of composed array (1. of right key)
        subkey[31] = rightKey[11]; // 40. of composed array (11. of right key)
        subkey[32] = rightKey[22]; // 51. of composed array (22. of right key)
        subkey[33] = rightKey[16]; // 45. of composed array (16. of right key)
        subkey[34] = rightKey[4]; // 33. of composed array (4. of right key)
        subkey[35] = rightKey[19]; // 48. of composed array (19. of right key)
        subkey[36] = rightKey[15]; // 44. of composed array (15. of right key)
        subkey[37] = rightKey[20]; // 49. of composed array (20. of right key)
        subkey[38] = rightKey[10]; // 39. of composed array (10. of right key)
        subkey[39] = rightKey[27]; // 56. of composed array (27. of right key)
        subkey[40] = rightKey[5]; // 34. of composed array (5. of right key)
        subkey[41] = rightKey[24]; // 53. of composed array (24. of right key)
        subkey[42] = rightKey[17]; // 46. of composed array (17. of right key)
        subkey[43] = rightKey[13]; // 42. of composed array (13. of right key)
        subkey[44] = rightKey[21]; // 50. of composed array (21. of right key)
        subkey[45] = rightKey[7]; // 36. of composed array (7. of right key)
        subkey[46] = rightKey[0]; // 29. of composed array (0. of right key)
        subkey[47] = rightKey[3]; // 32. of composed array (3. of right key)

        return subkey;
    }
}
