package me.loule;

// Import Scanner
import java.util.Scanner;

// Import Arrays
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // Clear console
        System.out.print("\033[H\033[2J");

        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        /////////////////////////////////
        // Creation of a Hangman Game //
        ///////////////////////////////

        // Welcome Message
        System.out.println("💁‍ Bienvenue dans le jeu du pendu !");
        System.out.println("👨‍💻 Quel mot voudrais-tu faire deviner ?"); // Ask for a word
        System.out.print(">> ");

        // Get the word to guess
        String word = scanner.nextLine().toUpperCase();
        String wordSplit[] = new String[word.length()];
        String wordFound[] = new String[word.length()];

        // CharAt word variable and set this on wordSplit variable
        for (int i = 0; i < word.length(); i++) {
            wordSplit[i] = word.charAt(i) + "";
        }

        String faults[] = {};

        // Clear console
        System.out.print("\033[H\033[2J");

        // Create a new game
        displayHangman(word, wordSplit, wordFound, faults);

        // Game loop
        while (true) {
            // Ask for a letter
            System.out.println("🔤 Quelle lettre voudrais-tu essayer ?");
            System.out.print(">> ");

            // Get the letter
            String letter = scanner.nextLine().toUpperCase();

            // Check if the letter is already in the word
            if (Arrays.asList(faults).contains(letter)) {
                System.out.println("🤔 Cette lettre a déjà été essayée !");
            } else {
                // Check if the letter is in the word
                if (Arrays.asList(wordSplit).contains(letter)) {
                    // If the letter is in the word, we replace the letter in the wordFound variable
                    for (int i = 0; i < word.length(); i++) {
                        if (wordSplit[i].equals(letter)) {
                            wordFound[i] = letter;
                        }
                    }
                } else {
                    // If the letter is not in the word, we add it to the faults array
                    faults = Arrays.copyOf(faults, faults.length + 1);
                    faults[faults.length - 1] = letter;
                }

                // Clear console
                System.out.print("\033[H\033[2J");

                // Display the game
                displayHangman(word, wordSplit, wordFound, faults);

                // Check if the game is finished
                 if (Arrays.asList(wordFound).contains(null)) {
                    // If the game is not finished, we continue
                    if(faults.length == 6) {
                        System.out.println("🤔 Tu as perdu ! Le mot était " + word + " !");
                        break;
                    }
                 } else {
                    // If the game is finished, we display the result
                     System.out.println("🎉 Bravo, tu as gagné !");
                     break;
                 }
            }
        }
    }

    // Display the hangman
    public static void displayHangman(String word, String[] wordSplit, String[] wordFound, String[] faults) {
        // Display the hangman
        if(faults.length == 0) {
            System.out.println("+---+\n|   |\n    |\n    |\n    |\n    |\n=========");
        } else if(faults.length == 1) {
            System.out.println("+---+\n|   |\nO   |\n    |\n    |\n    |\n=========");
        } else if(faults.length == 2) {
            System.out.println("+---+\n|   |\nO   |\n|   |\n    |\n    |\n=========");
        } else if(faults.length == 3) {
            System.out.println("+---+\n|   |\nO   |\n/|  |\n    |\n    |\n=========");
        } else if(faults.length == 4) {
            System.out.println("+---+\n|   |\nO   |\n/|\\ |\n    |\n    |\n=========");
        } else if(faults.length == 5) {
            System.out.println("+---+\n|   |\nO   |\n/|\\ |\n/   |\n    |\n=========");
        } else if(faults.length == 6) {
            System.out.println("+---+\n|   |\nO   |\n/|\\ |\n/ \\ |\n    |\n=========");
        }

        // Display the word
        System.out.println(word.length());
        for(int i = 0; i < word.length(); i++) {
            String wordWasFound = null;

            for(int j = 0; j < word.length(); j++) {
                if(wordSplit[i].equals(wordFound[j])) {
                    wordWasFound = wordFound[j] + " ";
                }
            }

            if(wordWasFound == null) {
                System.out.print("_ ");
            } else {
                System.out.print(wordWasFound);
            }
        }
        System.out.println("\n");
    }
}
