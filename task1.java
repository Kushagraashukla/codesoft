import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int totalScore = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // Range 1–100
            int attemptsLeft = 5;
            boolean hasGuessedCorrectly = false;

            totalRounds++;

            System.out.println("\nI have picked a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts. Good luck!");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess == numberToGuess) {
                    System.out.println("Correct! You guessed the number.");
                    hasGuessedCorrectly = true;
                    totalScore += 10; // 10 points for correct guess
                    break;
                } else if (guess < numberToGuess) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }

                attemptsLeft--;
                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Out of attempts! The correct number was: " + numberToGuess);
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over!");
        System.out.println("Total Rounds Played: " + totalRounds);
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}
