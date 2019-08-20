package recognition;
import java.util.*;

public class Main {
    private static final int LAYER_1_SIZE = 15;
    private static final int LAYER_2_SIZE = 10;  // the output of the network

    public static void main(String[] args) {
        // init the neural network
        int[][] neuralNetwork = new int[2][];
        neuralNetwork[0] = new int[LAYER_1_SIZE];
        neuralNetwork[1] = new int[LAYER_2_SIZE];
        int[][] weights = new int[LAYER_2_SIZE][LAYER_1_SIZE + 1]; //add 1 for bias

        initNNWeights(weights);
        // input the image to recognize
        System.out.println("Input Grid:");
        Scanner scan = new Scanner(System.in);
        for (int row = 0; row < 5; row++) {
            String s = scan.nextLine();
            for (int col = 0; col < 3; col++) {
                if (s.charAt(col) == 'X') {
                    neuralNetwork[0][row * 3 + col] = 1;
                }
                else {
                    neuralNetwork[0][row * 3 + col] = 0;
                }
            }
        }
        // run neural network
        int res = calcNN(neuralNetwork, weights);
        // print the result
        System.out.println("This number is " + res);
    }

    static private void initNNWeights(int[][] weights) {
        /* 0 :
           X X X
           X _ X
           X _ X
           X _ X
           X X X
         */
        weights[0][0]  =  1; weights[0][1]  =  1; weights[0][2]  =  1;
        weights[0][3]  =  1; weights[0][4]  = -1; weights[0][5]  =  1;
        weights[0][6]  =  1; weights[0][7]  = -1; weights[0][8]  =  1;
        weights[0][9]  =  1; weights[0][10] = -1; weights[0][11] =  1;
        weights[0][12] =  1; weights[0][13] =  1; weights[0][14] =  1;
        weights[0][15] = -1;   // bias

        /* 1:
           _ X _
           _ X _
           _ X _
           _ X _
           _ X _
         */
        weights[1][0]  = -1; weights[1][1]  =  1; weights[1][2]  = -1;
        weights[1][3]  = -1; weights[1][4]  =  1; weights[1][5]  = -1;
        weights[1][6]  = -1; weights[1][7]  =  1; weights[1][8]  = -1;
        weights[1][9]  = -1; weights[1][10] =  1; weights[1][11] = -1;
        weights[1][12] = -1; weights[1][13] =  1; weights[1][14] = -1;
        weights[1][15] = 6;   // bias

        /* 2:
           X X X
           _ _ X
           X X X
           X _ _
           X X X
         */
        weights[2][0]  =  1; weights[2][1]  =  1; weights[2][2]  =  1;
        weights[2][3]  = -1; weights[2][4]  = -1; weights[2][5]  =  1;
        weights[2][6]  =  1; weights[2][7]  =  1; weights[2][8]  =  1;
        weights[2][9]  =  1; weights[2][10] = -1; weights[2][11] = -1;
        weights[2][12] =  1; weights[2][13] =  1; weights[2][14] =  1;
        weights[2][15] = 0;   // bias
        
        /* 3:
           X X X
           _ _ X
           X X X
           _ _ X
           X X X
         */
        weights[3][0]  =  1; weights[3][1]  =  1; weights[3][2]  =  1;
        weights[3][3]  = -1; weights[3][4]  = -1; weights[3][5]  =  1;
        weights[3][6]  =  1; weights[3][7]  =  1; weights[3][8]  =  1;
        weights[3][9]  = -1; weights[3][10] = -1; weights[3][11] =  1;
        weights[3][12] =  1; weights[3][13] =  1; weights[3][14] =  1;
        weights[3][15] = 0;   // bias
        
        /* 4:
           X _ X
           X _ X
           X X X
           _ _ X
           _ _ X
         */
        weights[4][0]  =  1; weights[4][1]  = -1; weights[4][2]  =  1;
        weights[4][3]  =  1; weights[4][4]  = -1; weights[4][5]  =  1;
        weights[4][6]  =  1; weights[4][7]  =  1; weights[4][8]  =  1;
        weights[4][9]  = -1; weights[4][10] = -1; weights[4][11] =  1;
        weights[4][12] = -1; weights[4][13] = -1; weights[4][14] =  1;
        weights[4][15] = 2;   // bias
        
        /* 5:
           X X X
           X _ _
           X X X
           _ _ X
           X X X
         */
        weights[5][0]  =  1; weights[5][1]  =  1; weights[5][2]  =  1;
        weights[5][3]  =  1; weights[5][4]  = -1; weights[5][5]  = -1;
        weights[5][6]  =  1; weights[5][7]  =  1; weights[5][8]  =  1;
        weights[5][9]  = -1; weights[5][10] = -1; weights[5][11] =  1;
        weights[5][12] =  1; weights[5][13] =  1; weights[5][14] =  1;
        weights[5][15] = 0;   // bias
        
        /* 6:
           X X X
           X _ _
           X X X
           X _ X
           X X X
         */
        weights[6][0]  =  1; weights[6][1]  =  1; weights[6][2]  =  1;
        weights[6][3]  =  1; weights[6][4]  = -1; weights[6][5]  = -1;
        weights[6][6]  =  1; weights[6][7]  =  1; weights[6][8]  =  1;
        weights[6][9]  =  1; weights[6][10] = -1; weights[6][11] =  1;
        weights[6][12] =  1; weights[6][13] =  1; weights[6][14] =  1;
        weights[6][15] = -1;   // bias
        
        /* 7 :
           X X X
           _ _ X
           _ _ X
           _ _ X
           _ _ X
         */
        weights[7][0]  =  1; weights[7][1]  =  1; weights[7][2]  =  1;
        weights[7][3]  = -1; weights[7][4]  = -1; weights[7][5]  =  1;
        weights[7][6]  = -1; weights[7][7]  = -1; weights[7][8]  =  1;
        weights[7][9]  = -1; weights[7][10] = -1; weights[7][11] =  1;
        weights[7][12] = -1; weights[7][13] = -1; weights[7][14] =  1;
        weights[7][15] = 4;   // bias
        
        /* 8 :
           X X X
           X _ X
           X X X
           X _ X
           X X X
         */
        weights[8][0]  =  1; weights[8][1]  =  1; weights[8][2]  =  1;
        weights[8][3]  =  1; weights[8][4]  = -1; weights[8][5]  =  1;
        weights[8][6]  =  1; weights[8][7]  =  1; weights[8][8]  =  1;
        weights[8][9]  =  1; weights[8][10] = -1; weights[8][11] =  1;
        weights[8][12] =  1; weights[8][13] =  1; weights[8][14] =  1;
        weights[8][15] = -2;   // bias
        
        /* 9 :
           X X X
           X _ X
           X X X
           _ _ X
           X X X
         */
        weights[9][0]  =  1; weights[9][1]  =  1; weights[9][2]  =  1;
        weights[9][3]  =  1; weights[9][4]  = -1; weights[9][5]  =  1;
        weights[9][6]  =  1; weights[9][7]  =  1; weights[9][8]  =  1;
        weights[9][9]  = -1; weights[9][10] = -1; weights[9][11] =  1;
        weights[9][12] =  1; weights[9][13] =  1; weights[9][14] =  1;
        weights[9][15] = -1;   // bias
    }
    static private int calcNN(int[][] nn, int[][] weights)
    {
        // calculate the output neurons
        for (int i = 0; i <= 9; i++) {
            nn[1][i] = 0;
            for (int inpNeu = 0; inpNeu < 15; inpNeu++) {
                nn[1][i] += nn[0][inpNeu] * weights[i][inpNeu];
            }
            // add bias
            nn[1][i] += weights[i][15];
        }

        // find the max value
        int maxInd = 0;
        for (int i = 1; i < 10; i++) {
            if (nn[1][i] > nn[1][maxInd]) {
                maxInd = i;
            }
        }

        return maxInd;
    }
}
