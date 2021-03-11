package za.co.wethinkcode.mastermind;

public class Mastermind {
    private final String code;
    private final Player player;


    public Mastermind(CodeGenerator generator, Player player){
        this.code = generator.generateCode();
        this.player = player;
    }


    public Mastermind(){
        this(new CodeGenerator(), new Player());
    }


    public void doMatch(String guess, String answer){
        String[] answerList = answer.split("");
        String[] guessList = guess.split("");
        int corPosAndNum = 0;
        int corNum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (answerList[i].equals(guessList[j]) && i == j) {
                    corPosAndNum += 1;
                } else if (answerList[i].equals(guessList[j])) {
                    corNum += 1;
                }
            }
        }

        System.out.println("\nNumber of correct digits in correct place: " + corPosAndNum);
        System.out.println("Number of correct digits not in correct place: " + corNum);

        if ( player.getGuessesLeft() > 0 ){
            System.out.println("Turns left: " + player.getGuessesLeft());
        }
        else {
            System.out.println("No more turns left.");
        }
    }


    public void runGame(){
        String currentAnswer = code.toString();
        String currentGuess = player.getGuess();

        while ( !currentGuess.equals(currentAnswer) && player.getGuessesLeft() >= 0 ) {
            doMatch(currentGuess, currentAnswer);

            if ( player.getGuessesLeft() > 0 ){
                currentGuess = player.getGuess();
            }
            else {
                player.minusGuess();
            }
        }

        if ( currentGuess.equals(currentAnswer) ) {
            System.out.println("\nNumber of correct digits in correct place: 4" +
                    "\nNumber of correct digits not in correct place: 0" +
                    "\nCongratulations! You are a codebreaker!");
        }

        System.out.println("The code was: " + currentAnswer);
    }


    public static void main(String[] args){
        Mastermind game = new Mastermind();
        game.runGame();
    }
}
