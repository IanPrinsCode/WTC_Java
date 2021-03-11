package za.co.wethinkcode.mastermind;

import java.util.Random;

public class CodeGenerator {
    private final Random random;

    public CodeGenerator(){
        this.random = new Random();
    }

    public CodeGenerator(Random random){
        this.random = random;
    }


    public String generateCode(){
        int[] secretCode = new int[4];
        for (int i = 0; i < 4; i++) {
            int randomInteger = this.random.nextInt(8) + 1;
            secretCode[i] = randomInteger;
        }

        StringBuffer strbuffer = new StringBuffer();
        for (int num : secretCode) {
            strbuffer.append(num);
        }

        System.out.println("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.");

        String code = strbuffer.toString();
        return code;
    }
}
