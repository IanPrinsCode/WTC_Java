package za.co.wethinkcode.fizzbuzz;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class FizzBuzz {
    public static String checkNumber(int number) {
        boolean divisibleBy3 = number % 3 == 0;
        boolean divisibleBy5 = number % 5 == 0;

        if ( divisibleBy3 && divisibleBy5 ) {
            return "FizzBuzz";
        }
        else if ( divisibleBy3 ){
            return "Fizz";
        }
        else if ( divisibleBy5 ){
            return "Buzz";
        }
        return String.valueOf(number);
    }

    public static String countTo(int number) {
        String[] numbers = new String[number];

        for (int i = 1; i < number+1; i++) {
            numbers[i-1] = checkNumber(i);
        }

        return Arrays.toString(numbers);
    }
}
