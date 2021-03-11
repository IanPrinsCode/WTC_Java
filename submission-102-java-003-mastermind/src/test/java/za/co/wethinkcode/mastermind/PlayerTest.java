package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void testIsDigits() {
        Player player = new Player();

        assertTrue(player.isDigits("1234"));
        assertFalse(player.isDigits("chars"));
    }

    @Test
    void testGuessCorrect() {
        Player player = new Player(new ByteArrayInputStream("1324\n".getBytes()));
        String guess = player.getGuess();

        assertEquals("1324", guess);
    }

    @Test
    void testIsRightRange() {
        Player player = new Player();

        assertTrue(player.isRightRange("1468"));
        assertFalse(player.isRightRange("0234"));
        assertFalse(player.isRightRange("2349"));
    }

    @Test
    void testIsRightLength() {
        Player player = new Player();

        assertTrue(player.isRightLength("1234"));
        assertFalse(player.isRightLength("12345"));
    }

    @Test
    void testGetGuessShort() {
        Player player = new Player(new ByteArrayInputStream("12\n1234\n".getBytes()));
        String guess = player.getGuess();

        assertEquals("1234", guess);
        assertEquals(
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void testGetGuessLong() {
        Player player = new Player(new ByteArrayInputStream("12345\n1234\n".getBytes()));
        String guess = player.getGuess();

        assertEquals("1234", guess);
        assertEquals(
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void testGetGuessInvalid() {
        Player player = new Player(new ByteArrayInputStream("123f\n1239\n0123\n1234\n".getBytes()));
        String guess = player.getGuess();

        assertEquals("1234", guess);
        assertEquals(
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code: \n" +
                        "Please enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code: \n" +
                "Please enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:",
                outputStreamCaptor.toString().trim());
    }
}