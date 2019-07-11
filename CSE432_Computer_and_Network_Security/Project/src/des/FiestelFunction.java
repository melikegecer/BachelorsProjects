package des;

public class FiestelFunction {

    //<editor-fold defaultstate="collapsed" desc=" S-Boxes ">
    // 1st s-box
    private static final int[][] s1 = {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};

    // 2nd s-box
    private static final int[][] s2 = {
        {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
        {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
        {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
        {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};

    // 3rd s-box
    private static final int[][] s3 = {
        {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
        {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
        {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
        {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};

    // 4th s-box
    private static final int[][] s4 = {
        {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
        {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
        {10, 6, 9, 0, 12, 11, 7, 12, 15, 1, 3, 14, 5, 2, 8, 4},
        {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};

    // 5th s-box
    private static final int[][] s5 = {
        {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
        {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
        {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
        {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};

    // 6th s-box
    private static final int[][] s6 = {
        {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
        {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
        {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
        {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};

    // 7th s-box
    private static final int[][] s7 = {
        {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
        {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
        {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
        {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};

    // 8th s-box
    private static final int[][] s8 = {
        {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
        {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
        {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
        {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};

    //</editor-fold>
    private static final int[][][] sboxes = {s1, s2, s3, s4, s5, s6, s7, s8};

    // this method returns answer of fiestel function
    static int[] getFiestelResult(int[] rightText, int[] key) {
        int[] fiestel = new int[32];
        int[][] outputsOfSboxes = new int[8][4];

        // i should expand 32 bit to 48 bit
        rightText = expandText(rightText);

        // i should xor text with key
        int[] xored = xor(rightText, key);
        int j = 0;

        for (int i = 0; i < 48; i = i + 6) {
            // i am putting 6 bits into an array
            int[] inputBits = {
                xored[i], xored[i + 1], xored[i + 2],
                xored[i + 3], xored[i + 4], xored[i + 5]};

            // i am sending  bits to sbox method
            outputsOfSboxes[j] = sboxing(j, inputBits);
            j++;
        }

        // i should unite all 8 outputs into one array
        fiestel = uniteAllOutputs(outputsOfSboxes);

        // i should apply Permutation Function
        fiestel = P(fiestel);

        return fiestel;
    }

    private static int[] P(int[] fiestel) {
        int[] result = new int[32];

        result[0] = fiestel[15];  // 16. of fiestel
        result[1] = fiestel[6];   //  7. of fiestel
        result[2] = fiestel[19];  // 20. of fiestel
        result[3] = fiestel[20];  // 21. of fiestel
        result[4] = fiestel[28];  // 29. of fiestel
        result[5] = fiestel[11];  // 12. of fiestel
        result[6] = fiestel[27];  // 28. of fiestel
        result[7] = fiestel[16];  // 17. of fiestel

        result[8] = fiestel[0];   // 1. of fiestel
        result[9] = fiestel[14];  // 15. of fiestel
        result[10] = fiestel[22]; // 23. of fiestel
        result[11] = fiestel[25]; // 26. of fiestel
        result[12] = fiestel[4];  // 5. of fiestel
        result[13] = fiestel[17]; // 18. of fiestel
        result[14] = fiestel[30]; // 31. of fiestel
        result[15] = fiestel[9];  // 10. of fiestel

        result[16] = fiestel[1];  // 2. of fiestel
        result[17] = fiestel[7];  // 8. of fiestel
        result[18] = fiestel[23]; // 24. of fiestel
        result[19] = fiestel[13]; // 14. of fiestel
        result[20] = fiestel[31]; // 32. of fiestel
        result[21] = fiestel[26]; // 27. of fiestel
        result[22] = fiestel[2];  // 3. of fiestel
        result[23] = fiestel[8];  // 9. of fiestel

        result[24] = fiestel[18]; // 19. of fiestel
        result[25] = fiestel[12]; // 13. of fiestel
        result[26] = fiestel[29]; // 30. of fiestel
        result[27] = fiestel[5];  // 6. of fiestel
        result[28] = fiestel[21]; // 22. of fiestel
        result[29] = fiestel[10]; // 11. of fiestel
        result[30] = fiestel[3];  // 4. of fiestel
        result[31] = fiestel[24]; // 25. of fiestel

        return result;
    }

    // this method unites all outputs of sboxes into one array
    private static int[] uniteAllOutputs(int[][] outputsOfSboxes) {
        int[] united = new int[32];
        int k = 0;

        for (int i = 0; i < outputsOfSboxes.length; i++) {
            for (int j = 0; j < outputsOfSboxes[0].length; j++) {
                united[k] = outputsOfSboxes[i][j];
                k++;
            }
        }
        return united;
    }

    // this method executes s-box operations
    private static int[] sboxing(int i, int[] inputBits) {
        int[] output = new int[4];

        // first bit and last bit represents row
        int row = inputBits[0] * 2 + inputBits[5];

        // bits which are between first bit and last bit represent column
        int column = inputBits[1] * 8 + inputBits[2] * 4 + inputBits[3] * 2 + inputBits[4];

        // by using row and column i get sbox result
        int value = sboxes[i][row][column];

        //<editor-fold defaultstate="collapsed" desc=" operations for getting value as bits">
        int count = 3;
        while (0 < value) {
            output[count] = value % 2;
            value /= 2;
            count--;
        }
        //</editor-fold>

        return output;
    }

    // this method xores text with key
    private static int[] xor(int[] rightText, int[] key) {
        int[] xored = new int[48];

        for (int i = 0; i < xored.length; i++) {
            xored[i] = rightText[i] ^ key[i];
        }

        return xored;
    }

    // this method expands text from 32 bit to 48 bit
    private static int[] expandText(int[] rightText) {
        int[] expanded = new int[48];

        expanded[0] = rightText[31];  // 32. element of right text
        expanded[1] = rightText[0];   //  1. element of right text
        expanded[2] = rightText[1];   //  2. element of right text
        expanded[3] = rightText[2];   //  3. element of right text
        expanded[4] = rightText[3];   //  4. element of right text
        expanded[5] = rightText[4];   //  5. element of right text

        expanded[6] = rightText[3];   //  4. element of right text
        expanded[7] = rightText[4];   //  5. element of right text
        expanded[8] = rightText[5];   //  6. element of right text
        expanded[9] = rightText[6];   //  7. element of right text
        expanded[10] = rightText[7];  //  8. element of right text
        expanded[11] = rightText[8];  //  9. element of right text

        expanded[12] = rightText[7];  //  8. element of right text
        expanded[13] = rightText[8];  //  9. element of right text
        expanded[14] = rightText[9];  // 10. element of right text
        expanded[15] = rightText[10]; // 11. element of right text
        expanded[16] = rightText[11]; // 12. element of right text
        expanded[17] = rightText[12]; // 13. element of right text

        expanded[18] = rightText[11]; // 12. element of right text
        expanded[19] = rightText[12]; // 13. element of right text
        expanded[20] = rightText[13]; // 14. element of right text
        expanded[21] = rightText[14]; // 15. element of right text
        expanded[22] = rightText[15]; // 16. element of right text
        expanded[23] = rightText[16]; // 17. element of right text

        expanded[24] = rightText[15]; // 16. element of right text
        expanded[25] = rightText[16]; // 17. element of right text
        expanded[26] = rightText[17]; // 18. element of right text
        expanded[27] = rightText[18]; // 19. element of right text
        expanded[28] = rightText[19]; // 20. element of right text
        expanded[29] = rightText[20]; // 21. element of right text

        expanded[30] = rightText[19]; // 20. element of right text
        expanded[31] = rightText[20]; // 21. element of right text
        expanded[32] = rightText[21]; // 22. element of right text
        expanded[33] = rightText[22]; // 23. element of right text
        expanded[34] = rightText[23]; // 24. element of right text
        expanded[35] = rightText[24]; // 25. element of right text

        expanded[36] = rightText[23]; // 24. element of right text
        expanded[37] = rightText[24]; // 25. element of right text
        expanded[38] = rightText[25]; // 26. element of right text
        expanded[39] = rightText[26]; // 27. element of right text
        expanded[40] = rightText[27]; // 28. element of right text
        expanded[41] = rightText[28]; // 29. element of right text

        expanded[42] = rightText[27]; // 28. element of right text
        expanded[43] = rightText[28]; // 29. element of right text
        expanded[44] = rightText[29]; // 30. element of right text
        expanded[45] = rightText[30]; // 31. element of right text
        expanded[46] = rightText[31]; // 32. element of right text
        expanded[47] = rightText[0];  //  1. element of right text

        return expanded;
    }

}
