package za.co.wethinkcode.mastermind;

import java.io.InputStream;
import java.util.Scanner;

public class Player {
    private final Scanner inputScanner;
    private int guesses = 12;


    public Player(){
        this.inputScanner = new Scanner(System.in);
    }

    public Player(InputStream inputStream){
        this.inputScanner = new Scanner(inputStream);
    }


    public int getGuessesLeft(){
        return guesses;
    }


    public void minusGuess() {
        guesses -= 1;
    }


    public boolean isDigits(String currentGuess) {
        char[] chars = currentGuess.toCharArray();
        for (char i : chars) {
            if ( !Character.isDigit(i) ) {
                return false;
            }
        }
        return true;
    }


    public boolean isRightLength(String currentGuess) {
        return currentGuess.length() == 4;
    }


    public boolean isRightRange(String currentGuess) {
        char[] chars = currentGuess.toCharArray();

        for (char i : chars) {
            int num = Integer.parseInt(String.valueOf(i));
            if ( num < 1 || num > 8 ) {
                return false;
            }
        }
        return true;
    }


    public String getGuess(){
        System.out.print("Input 4 digit code:");
        String currentGuess = this.inputScanner.nextLine();

        while ( !isDigits(currentGuess) || !isRightLength(currentGuess) || !isRightRange(currentGuess) || currentGuess.equals("") ){
            System.out.println("\nPlease enter exactly 4 digits (each from 1 to 8).");
            System.out.print("Input 4 digit code: ");
            currentGuess = this.inputScanner.nextLine();
        }

        guesses -= 1;

        return currentGuess;
    }
}
