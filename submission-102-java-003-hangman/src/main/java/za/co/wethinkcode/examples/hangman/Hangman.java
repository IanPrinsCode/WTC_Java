package za.co.wethinkcode.examples.hangman;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// tag::hangman-class[]
public class Hangman {
    private static String selectedWord;

    public static void main(String[] args) throws IOException {
    // end::hangman-class[]
        Random random = new Random();
        Player player = new Player();
        int chances = player.getChances();

        System.out.println("Words file? [leave empty to use short_words.text]");
        String fileName = player.getWordsFile();

        List<String> words = Files.readAllLines(Path.of(fileName));                                     //<3>

        int randomIndex = random.nextInt(words.size());                                                 //<4>
        selectedWord = words.get(randomIndex).trim();                                                   //<5>

        randomIndex = random.nextInt(selectedWord.length() - 1);                                        //<6>

        String resultWord = "_".repeat(selectedWord.length());                                          //<7>

        String currentAnswer = fillInChar(resultWord,                                                   //<8>
                selectedWord.charAt(randomIndex));

        System.out.println("Guess the word: " + currentAnswer);

        while (!currentAnswer.equalsIgnoreCase(selectedWord)) {                                         //<9>
            String guess = player.getGuess();

            if (player.wantsToQuit()) {
                System.out.println("Bye!");
                break;
            }

            if (selectedWord.indexOf(guess.charAt(0)) >= 0
                    && currentAnswer.indexOf(guess.charAt(0)) < 0) {                                    //<12>
                currentAnswer = fillInChar(currentAnswer, guess.charAt(0));
                System.out.println(currentAnswer);
            } else {                                                                                    //<13>
                // tag::use-chances[]
                player.lostChance();
                chances = player.getChances();
                System.out.println("Wrong! Number of guesses left: " + chances);
                if (player.hasNoChances()) {
                    System.out.println("Sorry, you are out of guesses. The word was: " + selectedWord);
                    break;
                }
                // end::use-chances[]
            }
        }

        if (currentAnswer.equalsIgnoreCase(selectedWord)) {                                             //<14>
            System.out.println("That is correct! You escaped the noose .. this time.");
        }
    }

    private static String fillInChar(String answerWord, char guessedChar) {                             //<15>
        // tag::string-builder[]
        // tag::construct-string-builder[]
        StringBuilder result = new StringBuilder();
        // end::construct-string-builder[]
        for (int i = 0; i < selectedWord.length(); i++) {
            if (guessedChar == selectedWord.charAt(i)) {
                result.append(guessedChar);
            } else {
                result.append(answerWord.charAt(i));
            }
        }
        return result.toString();
        // end::string-builder[]
    }
}
