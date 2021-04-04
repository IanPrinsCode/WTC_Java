//package za.co.wethinkcode.toyrobot;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CommandsTest {
//    private final PrintStream standardOut = System.out;
//    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//
//    @Test
//    void testSprint() {
//        System.setOut(new PrintStream(outputStreamCaptor));
//
//        Robot tony = new Robot("tony");
//        Command sprint = new SprintCommand("5");
//        sprint.execute(tony);
//        assertEquals("[0,5] TONY> Moved forward by 5 steps.\n" +
//                "[0,9 TONY> Moved forward by 4 steps.\n" +
//                "[0,12] TONY> Moved forward by 3 steps.\n" +
//                "[0,14] TONY> Moved forward by 2 steps.\n" +
//                "[0,15] TONY> Moved forward by 1 steps.\n", outputStreamCaptor.toString()
//                .trim());
//    }
//}
