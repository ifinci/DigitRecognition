package recognition;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork("digitNN.ser");
        boolean end = false;

        do {
            // show a menu:
            System.out.println("1. Learn the network");
            System.out.println("2. Guess a number");
            System.out.print("Your choice: ");

            Scanner scan = new Scanner(System.in);
            int sel = scan.nextInt();
            if (sel == 1) {
                System.out.println("Learning...");
                nn.learn();
                System.out.println("Done! Saved to the file.");
            }
            else if (sel == 2) {
                end = true;
                guessNumber(nn);
            }
        } while (!end);

    }

    private static void guessNumber(NeuralNetwork nn)
    {
        int inpSize = nn.getInputSize();
        int[] input = new int[inpSize];
        // get the users's input digit converted into the array
        getUserDigit(input);

        int guess = nn.run(input);
        System.out.println("This number is " + guess);
    }

    private static void getUserDigit(int[] input) {
        Scanner s = new Scanner(System.in);
        System.out.println("Input grid:");

        for (int row = 0; row < 5; row++) {
            String str = s.nextLine();
            for (int col = 0; col < 3; col++) {
                if ('X' == str.charAt(col)) {
                    input[3 * row + col] = 1;
                }
                else {
                    input[3 * row + col] = -1;
                }
            }
        }
    }

}
