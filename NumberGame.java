import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minNum = 1;
        int maxNum = 100;
        int score = 0;

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      Welcome to the Number Guessing      ║");
        System.out.println("║                 Game!                    ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("Enter the number of attempts you want: ");
        int maxAttempts = scanner.nextInt();

        while (true) {
            int targetNumber = random.nextInt(maxNum - minNum + 1) + minNum;
            System.out.println("\nI'm thinking of a number between " + minNum + " and " + maxNum + "...");
            System.out.println("Can you guess it?");

            int attempts = 0;
            while (attempts < maxAttempts) {
                System.out.print("Your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == targetNumber) {
                    System.out.println("\nCongratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low. Try again.");
                } else {
                    System.out.println("Too high. Try again.");
                }
            }

            if (attempts == maxAttempts) {
                System.out.println("\nSorry, you've used all your attempts. The correct number was " + targetNumber + ".");
            }

            System.out.println("Your current score: " + score);
            System.out.print("\nDo you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   Thanks for playing! Your final score   ║");
        System.out.println("║             is: " + score + "                        ║");
        System.out.println("╚══════════════════════════════════════════╝");
        scanner.close();
    }
}
