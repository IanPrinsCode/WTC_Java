package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    public String greeting = "What do you want to name your robot?\n"
            + "Hello Kiddo!\n"
            + "[0,0] TONY> Ready\n";

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testOff() {
        InputStream input = new ByteArrayInputStream("TONY\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Shutting down...".trim();
        assertEquals(expected, actual);
    }

    @Test
    void testHelp() {
        InputStream input = new ByteArrayInputStream("TONY\nhelp\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> I can understand these commands:\n"
                + "OFF  - Shut down robot\n"
                + "HELP - provide information about commands\n"
                + "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n"
                + "BACK - move back by specified number of steps, e.g. 'BACK 10'\n"
                + "LEFT - turn left\n"
                + "RIGHT - turn right\n"
                + "SPRINT - does a sprint\n"
                + "REPLAY - replay all movement commands\n"
                + "MAZERUN - solves maze to reach a border\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Shutting down...".trim();
        assertEquals(expected, actual);
    }

    @Test
    void testInvalidCommand() {
        InputStream input = new ByteArrayInputStream("TONY\ninvalid\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Sorry, I did not understand 'invalid'.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testForwardCorrect() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testForwardPastLimit() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 210\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Sorry, I cannot go outside my safe zone.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testBackCorrect() {
        InputStream input = new ByteArrayInputStream("TONY\nback 50\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,-50] TONY> Moved back by 50 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-50] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testRight() {
        InputStream input = new ByteArrayInputStream("TONY\nright\nforward 10\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Turned right.\n"
                + "TONY> What must I do next?\n"
                + "[10,0] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[10,0] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testLeft() {
        InputStream input = new ByteArrayInputStream("TONY\nleft\nforward 10\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Turned left.\n"
                + "TONY> What must I do next?\n"
                + "[-10,0] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[-10,0] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testSprint() {
        InputStream input = new ByteArrayInputStream("TONY\nsprint 5\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,5] TONY> Moved forward by 5 steps.\n"
                + "[0,9] TONY> Moved forward by 4 steps.\n"
                + "[0,12] TONY> Moved forward by 3 steps.\n"
                + "[0,14] TONY> Moved forward by 2 steps.\n"
                + "[0,15] TONY> Moved forward by 1 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,15] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayNoArg() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nreplay\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,40] TONY> Moved forward by 10 steps.\n"
                + "[0,60] TONY> Moved forward by 20 steps.\n"
                + "[0,60] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,60] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayNoArgReversed() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nreplay reversed\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,50] TONY> Moved forward by 20 steps.\n"
                + "[0,60] TONY> Moved forward by 10 steps.\n"
                + "[0,60] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,60] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayLast2() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nback 30\nreplay 2\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Moved back by 30 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,20] TONY> Moved forward by 20 steps.\n"
                + "[0,-10] TONY> Moved back by 30 steps.\n"
                + "[0,-10] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,-10] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayLast2Reversed() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nback 30\nreplay reversed 2\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Moved back by 30 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-30] TONY> Moved back by 30 steps.\n"
                + "[0,-10] TONY> Moved forward by 20 steps.\n"
                + "[0,-10] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,-10] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayRange() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nback 30\nback 40\nreplay 4-2\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Moved back by 30 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-40] TONY> Moved back by 40 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-30] TONY> Moved forward by 10 steps.\n"
                + "[0,-10] TONY> Moved forward by 20 steps.\n"
                + "[0,-10] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,-10] TONY> Shutting down...";
        assertEquals(expected, actual);
    }

    @Test
    void testReplayRangeReversed() {
        InputStream input = new ByteArrayInputStream("TONY\nforward 10\nforward 20\nback 30\nback 40\nreplay reversed 4-2\noff\n".getBytes());
        System.setIn(input);
        Play.main(new String[]{"text", "EmptyMaze"});
        String actual = outputStreamCaptor.toString().trim();
        String expected = greeting
                + "TONY> What must I do next?\n"
                + "[0,10] TONY> Moved forward by 10 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,30] TONY> Moved forward by 20 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,0] TONY> Moved back by 30 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-40] TONY> Moved back by 40 steps.\n"
                + "TONY> What must I do next?\n"
                + "[0,-20] TONY> Moved forward by 20 steps.\n"
                + "[0,-10] TONY> Moved forward by 10 steps.\n"
                + "[0,-10] TONY> replayed 2 commands.\n"
                + "TONY> What must I do next?\n"
                + "[0,-10] TONY> Shutting down...";
        assertEquals(expected, actual);
    }
}
