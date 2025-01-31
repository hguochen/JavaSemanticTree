package advancedTypes;

import java.util.Arrays;

public class ArrayDemo {
    public static void main(String[] args) {
        // we can declare a one dimensional array like this
        int[] months = new int[12];
        months[0] = 1;
        months[1] = 2;
        months[2] = 3;
        System.out.println(months[0]);
        System.out.println(months[1]);
        System.out.println(months[2]);

        // we can also predefine an array
        int[] days = {1,2,3,4,5};
        System.out.println(days[2]);

        // we can declare a two dimensional array as such
        int[][] twoD = new int[4][5];
        int i, j, k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 5; j++) {
                twoD[i][j] = k;
                k++;
            }
        }

        for (i = 0; i < 4; i++) {
            for(j = 0; j < 5; j++) {
                System.out.print(twoD[i][j] + " ");
            }
            System.out.println();
        }

        // we can also declare a two dimensional array of predefined values
        int[][] predefinedTwoD = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        for (int l = 0; l < predefinedTwoD.length; l++) {
            for (int m = 0; m < predefinedTwoD[0].length; m++) {
                System.out.print(predefinedTwoD[l][m] + " ");
            }
            System.out.println();

        }
    }
}
